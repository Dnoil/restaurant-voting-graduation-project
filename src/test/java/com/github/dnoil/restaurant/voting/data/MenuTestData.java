package com.github.dnoil.restaurant.voting.data;

import com.github.dnoil.restaurant.voting.MatcherFactory;
import com.github.dnoil.restaurant.voting.model.Menu;
import com.github.dnoil.restaurant.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

import static com.github.dnoil.restaurant.voting.data.RestaurantTestData.restaurant1;
import static com.github.dnoil.restaurant.voting.data.RestaurantTestData.restaurant2;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "restaurant", "dishes");

    public static final int MENU_ID = 0;

    public static final Menu menu1 = new Menu(MENU_ID, restaurant1);
    public static final Menu menu2 = new Menu(MENU_ID + 1, restaurant2);
    public static final Menu oldMenu = new Menu(MENU_ID + 2, LocalDate.of(2024, 3, 5),restaurant1);

    public static final List<Menu> allMenusOfFirstRestaurant = List.of(oldMenu, menu1);

    public static Menu getNew(Restaurant newRestaurant) {
        return new Menu(null, newRestaurant);
    }

    public static Menu getUpdated() {
        return new Menu(MENU_ID, restaurant1);
    }

}
