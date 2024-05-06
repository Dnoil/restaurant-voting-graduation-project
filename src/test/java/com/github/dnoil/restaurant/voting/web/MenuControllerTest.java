package com.github.dnoil.restaurant.voting.web;

import com.github.dnoil.restaurant.voting.data.MenuTestData;
import com.github.dnoil.restaurant.voting.data.RestaurantTestData;
import com.github.dnoil.restaurant.voting.data.UserTestData;
import com.github.dnoil.restaurant.voting.error.NotFoundException;
import com.github.dnoil.restaurant.voting.model.Menu;
import com.github.dnoil.restaurant.voting.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.dnoil.restaurant.voting.data.MenuTestData.*;
import static com.github.dnoil.restaurant.voting.data.RestaurantTestData.RESTAURANT_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerTest extends AbstractControllerTest {
    private static final String MENUS_URL = "/api/admin/menus";

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(MENUS_URL + "/restaurant?id=" + RESTAURANT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_MATCHER.contentJson(menu1));
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(MENUS_URL + "/old/restaurant?id=" + RESTAURANT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MenuTestData.MENU_MATCHER.contentJson(allMenusOfFirstRestaurant));
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(MENUS_URL + "/" + MENU_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> menuService.getActual(RESTAURANT_ID));
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void update() throws Exception {
        Menu updated = MenuTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(MENUS_URL + "/" + MENU_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        MenuTestData.MENU_MATCHER.assertMatch(menuService.getActual(RESTAURANT_ID), updated);
    }

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Restaurant newRestaurant = restaurantService.create(RestaurantTestData.getNew());
        Menu newMenu = getNew(newRestaurant);
        ResultActions action = perform(MockMvcRequestBuilders.post(MENUS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)))
                .andExpect(status().isCreated());

        Menu created = MenuTestData.MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MenuTestData.MENU_MATCHER.assertMatch(created, newMenu);
        MenuTestData.MENU_MATCHER.assertMatch(menuService.getActual(newRestaurant.id()), newMenu);
    }
}
