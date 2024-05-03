package com.github.Dnoil.restaurant.voting.data;

import com.github.Dnoil.restaurant.voting.MatcherFactory;
import com.github.Dnoil.restaurant.voting.model.Menu;
import com.github.Dnoil.restaurant.voting.model.Restaurant;

import java.util.List;

import static com.github.Dnoil.restaurant.voting.data.RestaurantTestData.*;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingEqualsComparator(Menu.class);

    public static final int MENU_ID = 1;

    public static final Menu menu1 = new Menu(MENU_ID, "Menu of Cool Resto", restaurant1);
    public static final Menu menu2 = new Menu(MENU_ID + 1, "Menu of Cooler Resto", restaurant2);
    public static final Menu oldMenu = new Menu(MENU_ID + 2, "Old Menu of Cool Resto", restaurant1);

    public static final List<Menu> allMenusOfFirstRestaurant = List.of(menu1, oldMenu);

    public static Menu getNew(Restaurant newRestaurant) {
        return new Menu(null, "Menu of New Cool Resto", newRestaurant);
    }

    public static Menu getUpdated() {
        return new Menu(MENU_ID, "Menu of Renovated Cool Resto", restaurant1);
    }

}
