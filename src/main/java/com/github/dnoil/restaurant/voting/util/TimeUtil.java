package com.github.dnoil.restaurant.voting.util;

import com.github.dnoil.restaurant.voting.error.IllegalVotingTimeException;
import com.github.dnoil.restaurant.voting.model.Vote;

import java.time.LocalTime;

public class TimeUtil {

    private static final LocalTime VALID_TIME = LocalTime.of(11, 0);

    public static Vote validateVoteTime(Vote vote) throws IllegalVotingTimeException {
        if (vote.getVotedTime().isAfter(VALID_TIME)) {
            throw new IllegalVotingTimeException("Too late to change the vote (vote id=" + vote.id() + ")");
        }
        return vote;
    }
}
