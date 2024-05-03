package com.github.Dnoil.restaurant.voting.data;

import com.github.Dnoil.restaurant.voting.MatcherFactory;
import com.github.Dnoil.restaurant.voting.model.Restaurant;

import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final int RESTAURANT_ID = 1;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "Cool Resto", "26 Cool Street");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1,"Cooler Resto", "28 Cool Street");

    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2);
    public static final List<Restaurant> restaurantsByVotes = List.of(restaurant2, restaurant1);

    public static Restaurant getNew() {
        return new Restaurant(null, "New Cool Resto", "30 Cool Street");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Renovated Cool Resto", "26 Cool Street");
    }
}
