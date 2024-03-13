package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.Dish;
import com.github.Dnoil.restaurant_voting.model.Menu;
import lombok.Data;

@Data
public class DishTo extends BaseTo {
    private Integer price;
    private Menu menu;

    public DishTo(Dish dish) {
        super(dish);
        this.price = dish.getPrice();
        this.menu = dish.getMenu();
    }
}
