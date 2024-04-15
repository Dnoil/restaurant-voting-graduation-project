package com.github.Dnoil.restaurant_voting.web;

import com.github.Dnoil.restaurant_voting.data.MenuTestData;
import com.github.Dnoil.restaurant_voting.data.RestaurantTestData;
import com.github.Dnoil.restaurant_voting.model.Dish;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NoSuchElementException;

import static com.github.Dnoil.restaurant_voting.data.DishTestData.*;
import static com.github.Dnoil.restaurant_voting.data.MenuTestData.MENU_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DishControllerTest extends AbstractControllerTest {

    private static final String DISHES_URL = "/dishes";

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(DISHES_URL + "/menu?id=" + MENU_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dishes1));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(DISHES_URL + "/" + DISH_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1_1));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(DISHES_URL + "/" + DISH_ID))
                .andExpect(status().isNoContent());
        assertThrows(NoSuchElementException.class, () -> dishService.get(DISH_ID));
    }

    @Test
    void update() throws Exception {
        Dish updated = getUpdated();
        perform(MockMvcRequestBuilders.put(DISHES_URL + "/" + DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(dishService.get(DISH_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        restaurantService.createOrUpdate(RestaurantTestData.getNew());
        menuService.createOrUpdate(MenuTestData.getNew());
        Dish newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(DISHES_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andExpect(status().isCreated());

        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }
}
