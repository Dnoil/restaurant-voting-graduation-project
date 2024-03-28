package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.Restaurant;

public class RestaurantTo extends BaseTo {
    private final String address;
    private final Integer votedPopularity;

    public RestaurantTo(Restaurant restaurant) {
        super(restaurant);
        this.address = restaurant.getAddress();
        this.votedPopularity = restaurant.getVotedPopularity();
    }
}
