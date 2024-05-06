package com.github.dnoil.restaurant.voting.web;

import com.github.dnoil.restaurant.voting.data.*;
import com.github.dnoil.restaurant.voting.service.*;
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
        userService.create(UserTestData.admin);
        userService.create(UserTestData.user1);
        userService.create(UserTestData.user2);
        restaurantService.create(RestaurantTestData.restaurant1);
        restaurantService.create(RestaurantTestData.restaurant2);
        MenuTestData.oldMenu.setDate(LocalDate.now().minusDays(1));
        menuService.create(MenuTestData.oldMenu);
        menuService.create(MenuTestData.menu1);
        menuService.create(MenuTestData.menu2);
        dishService.create(DishTestData.dish1_1);
        dishService.create(DishTestData.dish1_2);
        dishService.create(DishTestData.dish2_1);
        dishService.create(DishTestData.dish2_2);
        voteService.create(VoteTestData.vote1);
        voteService.create(VoteTestData.vote2);
        VoteTestData.oldVote.setVotedDateTime(LocalDateTime.now().minusDays(1));
        voteService.create(VoteTestData.oldVote);
    }

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }
}
