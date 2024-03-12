package com.github.Dnoil.restaurant_voting.to;

import com.github.Dnoil.restaurant_voting.model.Menu;
import lombok.Data;

@Data
public class DishTo extends BaseTo {
    private Integer price;
    private Menu menu;
}
