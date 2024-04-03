package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.Restaurant;
import com.github.Dnoil.restaurant_voting.model.User;
import com.github.Dnoil.restaurant_voting.model.Vote;

public class VoteTo extends BaseTo {

    private final User user;

    private final Restaurant restaurant;

    public VoteTo(Vote vote) {
        super(vote);
        this.user = vote.getUser();
        this.restaurant = vote.getRestaurant();
    }
}
