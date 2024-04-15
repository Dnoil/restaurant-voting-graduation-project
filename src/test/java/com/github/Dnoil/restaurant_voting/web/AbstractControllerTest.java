package com.github.Dnoil.restaurant_voting.web;

import com.github.Dnoil.restaurant_voting.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.github.Dnoil.restaurant_voting.data.DishTestData.*;
import static com.github.Dnoil.restaurant_voting.data.MenuTestData.menu1;
import static com.github.Dnoil.restaurant_voting.data.MenuTestData.menu2;
import static com.github.Dnoil.restaurant_voting.data.RestaurantTestData.restaurant1;
import static com.github.Dnoil.restaurant_voting.data.RestaurantTestData.restaurant2;
import static com.github.Dnoil.restaurant_voting.data.UserTestData.*;
import static com.github.Dnoil.restaurant_voting.data.VoteTestData.vote1;
import static com.github.Dnoil.restaurant_voting.data.VoteTestData.vote2;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public abstract class AbstractControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    MenuService menuService;

    @Autowired
    DishService dishService;

    @Autowired
    VoteService voteService;

    @PostConstruct
    protected void loadData() {
        userService.createOrUpdate(admin);
        userService.createOrUpdate(user1);
        userService.createOrUpdate(user2);
        restaurantService.createOrUpdate(restaurant1);
        restaurantService.createOrUpdate(restaurant2);
        menuService.createOrUpdate(menu1);
        menuService.createOrUpdate(menu2);
        dishService.createOrUpdate(dish1_1);
        dishService.createOrUpdate(dish1_2);
        dishService.createOrUpdate(dish2_1);
        dishService.createOrUpdate(dish2_2);
        voteService.createOrUpdate(vote1);
        voteService.createOrUpdate(vote2);
    }
//    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

//    static {
//        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
//        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
//    }

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext webApplicationContext;

//    @PostConstruct
//    private void postConstruct() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(webApplicationContext)
//                .addFilter(CHARACTER_ENCODING_FILTER)
//                .build();
//    }

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }
}
