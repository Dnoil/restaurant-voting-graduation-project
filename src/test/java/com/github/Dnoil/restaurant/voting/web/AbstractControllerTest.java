package com.github.Dnoil.restaurant.voting.web;

import com.github.Dnoil.restaurant.voting.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.github.Dnoil.restaurant.voting.data.DishTestData.*;
import static com.github.Dnoil.restaurant.voting.data.MenuTestData.*;
import static com.github.Dnoil.restaurant.voting.data.RestaurantTestData.restaurant1;
import static com.github.Dnoil.restaurant.voting.data.RestaurantTestData.restaurant2;
import static com.github.Dnoil.restaurant.voting.data.UserTestData.*;
import static com.github.Dnoil.restaurant.voting.data.VoteTestData.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public abstract class AbstractControllerTest {

    @Autowired
    protected UserService userService;

    @Autowired
    protected RestaurantService restaurantService;

    @Autowired
    protected MenuService menuService;

    @Autowired
    protected DishService dishService;

    @Autowired
    protected VoteService voteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    protected void loadData() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        userService.create(admin);
        userService.create(user1);
        userService.create(user2);
        restaurantService.create(restaurant1);
        restaurantService.create(restaurant2);
        oldMenu.setDate(LocalDate.now().minusDays(1));
        menuService.create(oldMenu);
        menuService.create(menu1);
        menuService.create(menu2);
        dishService.create(dish1_1);
        dishService.create(dish1_2);
        dishService.create(dish2_1);
        dishService.create(dish2_2);
        voteService.create(vote1);
        voteService.create(vote2);
        oldVote.setVotedDateTime(LocalDateTime.now().minusDays(1));
        voteService.create(oldVote);
    }

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }
}
