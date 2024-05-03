package com.github.Dnoil.restaurant.voting.web;

import com.github.Dnoil.restaurant.voting.data.UserTestData;
import com.github.Dnoil.restaurant.voting.data.VoteTestData;
import com.github.Dnoil.restaurant.voting.error.NotFoundException;
import com.github.Dnoil.restaurant.voting.model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteControllerTest extends AbstractControllerTest {
    private static final String VOTES_URL = "/votes";

    @Test
    @WithUserDetails(value = UserTestData.USER1_MAIL)
    void getAllActual() throws Exception {
        perform(MockMvcRequestBuilders.get(VOTES_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.VOTE_MATCHER.contentJson(VoteTestData.votes));
    }

    @Test
    @WithUserDetails(value = UserTestData.USER1_MAIL)
    void getAllWithOld() throws Exception {
        perform(MockMvcRequestBuilders.get(VOTES_URL + "/old/user?id=" + UserTestData.USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.VOTE_MATCHER.contentJson(VoteTestData.votesOfUserWithOld));
    }

    @Test
    @WithUserDetails(value = UserTestData.USER1_MAIL)
    void getActual() throws Exception {
        perform(MockMvcRequestBuilders.get(VOTES_URL + "/user?id=" + UserTestData.USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.VOTE_MATCHER.contentJson(VoteTestData.vote1));
    }

    @Test
    @WithUserDetails(value = UserTestData.USER1_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(VOTES_URL + "/" + VoteTestData.VOTE_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> voteService.getActual(VoteTestData.VOTE_ID));
    }

    @Test
    @WithUserDetails(value = UserTestData.USER1_MAIL)
    void createWithLocation() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(VOTES_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isCreated());
        Vote created = VoteTestData.VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VoteTestData.VOTE_MATCHER.assertMatch(created, newVote);
        VoteTestData.VOTE_MATCHER.assertMatch(voteService.getActual(newVote.getUser().id()), newVote);
    }
}
