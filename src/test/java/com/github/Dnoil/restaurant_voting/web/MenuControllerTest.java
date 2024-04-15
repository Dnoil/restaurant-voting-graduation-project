package com.github.Dnoil.restaurant_voting.web;

import com.github.Dnoil.restaurant_voting.data.RestaurantTestData;
import com.github.Dnoil.restaurant_voting.model.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NoSuchElementException;

import static com.github.Dnoil.restaurant_voting.data.MenuTestData.*;
import static com.github.Dnoil.restaurant_voting.data.RestaurantTestData.RESTAURANT_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerTest extends AbstractControllerTest {
    private static final String MENUS_URL = "/menus";

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(MENUS_URL + "/restaurant?id=" + RESTAURANT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menu1));
    }

    //TODO fix
    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(MENUS_URL + "/" + MENU_ID))
                .andExpect(status().isNoContent());
        assertThrows(NoSuchElementException.class, () -> menuService.get(RESTAURANT_ID));
    }

    @Test
    void update() throws Exception {
        Menu updated = getUpdated();
        perform(MockMvcRequestBuilders.put(MENUS_URL + "/" + MENU_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MENU_MATCHER.assertMatch(menuService.get(MENU_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        restaurantService.createOrUpdate(RestaurantTestData.getNew());
        Menu newMenu = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(MENUS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)))
                .andExpect(status().isCreated());

        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId), newMenu);
    }
}
