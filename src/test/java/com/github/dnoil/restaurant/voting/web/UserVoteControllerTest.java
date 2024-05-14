package com.github.dnoil.restaurant.voting.web;

import com.github.dnoil.restaurant.voting.error.NotFoundException;
import com.github.dnoil.restaurant.voting.model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.dnoil.restaurant.voting.data.UserTestData.USER1_MAIL;
import static com.github.dnoil.restaurant.voting.data.VoteTestData.*;
import static com.github.dnoil.restaurant.voting.data.VoteTestData.VOTE_MATCHER;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserVoteControllerTest extends AbstractControllerTest {
    private static final String USER_VOTES_URL = "/api/votes";

    @Test
    @WithUserDetails(value = USER1_MAIL)
    void getAllActual() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_VOTES_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(votesOfUser));
    }

    @Test
    @WithUserDetails(value = USER1_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(USER_VOTES_URL))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> voteService.getActual(VOTE_ID));
    }

    @Test
    @WithUserDetails(value = USER1_MAIL)
    void createWithLocation() throws Exception {
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(USER_VOTES_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isCreated());
        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.getActual(newVote.getUser().id()), newVote);
    }
}
