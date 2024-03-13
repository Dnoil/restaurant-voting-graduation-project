package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.BaseEntity;
import com.github.Dnoil.restaurant_voting.model.Menu;
import com.github.Dnoil.restaurant_voting.model.Restaurant;
import lombok.Data;

import java.util.Date;

@Data
public class MenuTo extends BaseTo {
    private Date date;
    private Restaurant restaurant;

    public MenuTo(Menu menu) {
        super(menu);
        this.date = menu.getDate();
        this.restaurant = menu.getRestaurant();
    }
}
