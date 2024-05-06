package com.github.dnoil.restaurant.voting.web.json;

import com.github.dnoil.restaurant.voting.data.RestaurantTestData;
import com.github.dnoil.restaurant.voting.model.Restaurant;
import com.github.dnoil.restaurant.voting.web.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(RestaurantTestData.restaurant1);
        log.info(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurant, RestaurantTestData.restaurant1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(RestaurantTestData.restaurants);
        log.info(json);
        List<Restaurant> actual = JsonUtil.readValues(json, Restaurant.class);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(actual, RestaurantTestData.restaurants);
    }
}
