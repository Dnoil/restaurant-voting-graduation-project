package com.github.Dnoil.restaurant_voting.data;

import com.github.Dnoil.restaurant_voting.MatcherFactory;
import com.github.Dnoil.restaurant_voting.model.Menu;
import com.github.Dnoil.restaurant_voting.model.Restaurant;

import java.time.LocalDate;

import static com.github.Dnoil.restaurant_voting.data.RestaurantTestData.*;

public class MenuTestData {
    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingEqualsComparator(Menu.class);

    public static final int MENU_ID = 1;

    public static final Menu menu1 = new Menu(MENU_ID, "Menu of Cool Resto", LocalDate.of(2024, 1, 1), restaurant1);
    public static final Menu menu2 = new Menu(MENU_ID + 1, "Menu of Cooler Resto", LocalDate.of(2024, 1, 1), restaurant2);

    public static Menu getNew(Restaurant newRestaurant) {
        return new Menu(MENU_ID + 2, "Menu of New Cool Resto", LocalDate.of(2024, 1, 1), newRestaurant);
    }

    public static Menu getUpdated() {
        return new Menu(MENU_ID, "Menu of Renovated Cool Resto", LocalDate.of(2024, 1, 1), restaurant1);
    }
}
