package com.github.Dnoil.restaurant_voting.web;

import com.github.Dnoil.restaurant_voting.model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NoSuchElementException;

import static com.github.Dnoil.restaurant_voting.data.UserTestData.USER_ID;
import static com.github.Dnoil.restaurant_voting.data.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteControllerTest extends AbstractControllerTest {
    private static final String VOTES_URL = "/votes";

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(VOTES_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(votes));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(VOTES_URL + "/" + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(VOTES_URL + "/" + VOTE_ID))
                .andExpect(status().isNoContent());
        assertThrows(NoSuchElementException.class, () -> voteService.get(VOTE_ID));
    }

    @Test
    void update() throws Exception {
        Vote updated = getUpdated();
        perform(MockMvcRequestBuilders.put(VOTES_URL + "/" + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        VOTE_MATCHER.assertMatch(voteService.get(USER_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(VOTES_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isCreated());
        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.get(newVote.getUser().id()), newVote);
    }
}
