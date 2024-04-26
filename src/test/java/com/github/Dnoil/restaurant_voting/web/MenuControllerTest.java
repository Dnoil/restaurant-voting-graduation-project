package com.github.Dnoil.restaurant_voting.web;

import com.github.Dnoil.restaurant_voting.data.MenuTestData;
import com.github.Dnoil.restaurant_voting.data.RestaurantTestData;
import com.github.Dnoil.restaurant_voting.error.NotFoundException;
import com.github.Dnoil.restaurant_voting.model.Menu;
import com.github.Dnoil.restaurant_voting.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.Dnoil.restaurant_voting.data.MenuTestData.*;
import static com.github.Dnoil.restaurant_voting.data.RestaurantTestData.RESTAURANT_ID;
import static com.github.Dnoil.restaurant_voting.data.UserTestData.ADMIN_MAIL;
import static com.github.Dnoil.restaurant_voting.data.UserTestData.USER1_MAIL;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerTest extends AbstractControllerTest {
    private static final String MENUS_URL = "/menus";

    @Test
    @WithUserDetails(value = USER1_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(MENUS_URL + "/restaurant?id=" + RESTAURANT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menu1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(MENUS_URL + "/" + MENU_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> menuService.get(RESTAURANT_ID));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Menu updated = getUpdated();
        perform(MockMvcRequestBuilders.put(MENUS_URL + "/" + MENU_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MENU_MATCHER.assertMatch(menuService.get(RESTAURANT_ID), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Restaurant newRestaurant = restaurantService.create(RestaurantTestData.getNew());
        Menu newMenu = MenuTestData.getNew(newRestaurant);
        ResultActions action = perform(MockMvcRequestBuilders.post(MENUS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)))
                .andExpect(status().isCreated());

        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newRestaurant.id()), newMenu);
    }
}
