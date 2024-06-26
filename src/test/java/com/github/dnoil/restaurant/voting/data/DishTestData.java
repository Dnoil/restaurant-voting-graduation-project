package com.github.dnoil.restaurant.voting.data;

import com.github.dnoil.restaurant.voting.MatcherFactory;
import com.github.dnoil.restaurant.voting.model.Dish;

import java.math.BigDecimal;
import java.util.List;

import static com.github.dnoil.restaurant.voting.data.MenuTestData.menu1;
import static com.github.dnoil.restaurant.voting.data.MenuTestData.menu2;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "menu");

    public static final int DISH_ID = 0;

    public static final Dish dish1_1 = new Dish(DISH_ID, "First Dish of First Menu", new BigDecimal(100), menu1);
    public static final Dish dish1_2 = new Dish( DISH_ID + 1, "Second Dish of First Menu", new BigDecimal(150), menu1);
    public static final Dish dish2_1 = new Dish(DISH_ID + 2, "First Dish of Second Menu", new BigDecimal(200), menu2);
    public static final Dish dish2_2 = new Dish(DISH_ID + 3, "Second Dish of Second Menu", new BigDecimal(250), menu2);

    public static final List<Dish> dishes1 = List.of(dish1_1, dish1_2);

    public static Dish getNew() {
        return new Dish(null, "New Dish of First Menu", new BigDecimal(300), menu1);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID, "Updated Dish of First Menu", BigDecimal.valueOf(111), menu1);
    }
}
