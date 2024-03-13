package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.BaseEntity;
import com.github.Dnoil.restaurant_voting.model.Restaurant;
import lombok.Data;

@Data
public class RestaurantTo extends BaseTo {
    private String address;
    private Integer votedPopularity;

    public RestaurantTo(Restaurant restaurant) {
        super(restaurant);
        this.address = restaurant.getAddress();
        this.votedPopularity = restaurant.getVotedPopularity();
    }
}
