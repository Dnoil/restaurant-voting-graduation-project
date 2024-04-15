package com.github.Dnoil.restaurant_voting.web.json;

import com.github.Dnoil.restaurant_voting.model.Restaurant;
import com.github.Dnoil.restaurant_voting.web.JsonUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.Dnoil.restaurant_voting.data.RestaurantTestData.*;

public class JsonUtilTest {

    private static final Logger log = LoggerFactory.getLogger(JsonUtilTest.class);

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
