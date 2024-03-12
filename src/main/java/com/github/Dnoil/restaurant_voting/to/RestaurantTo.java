package com.github.Dnoil.restaurant_voting.to;

import lombok.Data;

@Data
public class RestaurantTo extends BaseTo {
    private String address;
    private Integer votedPopularity;
}
