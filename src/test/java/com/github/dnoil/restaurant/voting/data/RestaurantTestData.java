package com.github.dnoil.restaurant.voting.data;

import com.github.dnoil.restaurant.voting.MatcherFactory;
import com.github.dnoil.restaurant.voting.model.Restaurant;

import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");

    public static final int RESTAURANT_ID = 0;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "Cool Resto", "26 Cool Street");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1,"Cooler Resto", "28 Cool Street");

    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2);

    public static Restaurant getNew() {
        return new Restaurant(null, "New Cool Resto", "30 Cool Street");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Renovated Cool Resto", "26 Cool Street");
    }
}
