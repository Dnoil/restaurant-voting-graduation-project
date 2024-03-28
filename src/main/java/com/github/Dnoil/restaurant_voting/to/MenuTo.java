package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.Menu;
import com.github.Dnoil.restaurant_voting.model.Restaurant;

import java.util.Date;

public class MenuTo extends BaseTo {
    private final Date date;
    private final Restaurant restaurant;

    public MenuTo(Menu menu) {
        super(menu);
        this.date = menu.getDate();
        this.restaurant = menu.getRestaurant();
    }
}
