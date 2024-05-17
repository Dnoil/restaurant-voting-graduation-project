package com.github.dnoil.restaurant.voting.web;

import com.github.dnoil.restaurant.voting.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.dnoil.restaurant.voting.data.UserTestData.ADMIN_MAIL;
import static com.github.dnoil.restaurant.voting.data.UserTestData.USER_ID;
import static com.github.dnoil.restaurant.voting.data.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminVoteControllerTest extends AbstractControllerTest {
    private static final String ADMIN_VOTES_URL = "/api/admin/votes";

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllActual() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_VOTES_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(votes));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllByUserId() throws Exception {
        perform(MockMvcRequestBuilders.get(ADMIN_VOTES_URL + "/user?id=" + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(votesOfUser));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_VOTES_URL + "/" + VOTE_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> voteService.getActual(VOTE_ID));
    }
}
