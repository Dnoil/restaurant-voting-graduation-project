package com.github.Dnoil.restaurant_voting.web.json;

import com.github.Dnoil.restaurant_voting.model.Restaurant;
import com.github.Dnoil.restaurant_voting.web.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.Dnoil.restaurant_voting.data.RestaurantTestData.*;

@Slf4j
public class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(restaurant1);
        log.info(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(restaurant, restaurant1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(restaurants);
        log.info(json);
        List<Restaurant> actual = JsonUtil.readValues(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(actual, restaurants);
    }
}
