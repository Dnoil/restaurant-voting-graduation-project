package com.github.Dnoil.restaurant_voting.web;

import com.github.Dnoil.restaurant_voting.model.User;
import com.github.Dnoil.restaurant_voting.to.UserTo;
import com.github.Dnoil.restaurant_voting.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.Dnoil.restaurant_voting.TestUtil.userHttpBasic;
import static com.github.Dnoil.restaurant_voting.data.UserTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractControllerTest {
    private static final String USER_URL = "/user";

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_URL)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user1));
    }

    @Test
    void register() throws Exception {
        UserTo newTo = userTo;
        User newUser = UserUtil.createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andExpect(status().isCreated());

        User created = USER_MATCHER.readFromJson(action);
        int newId = created.id();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userService.get(newId), newUser);
    }

    @Test
    void update() throws Exception {
        UserTo updatedTo = userTo;
        perform(MockMvcRequestBuilders.put(USER_URL + "/" + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userService.get(USER_ID), UserUtil.updateFromTo(updatedTo, new User(user1)));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(USER_URL)
                .with(userHttpBasic(user1)))
                .andExpect(status().isNoContent());
        USER_MATCHER.assertMatch(userService.getAll(), admin, user2);
    }
}
