package com.github.Dnoil.restaurant_voting.util;

import com.github.Dnoil.restaurant_voting.error.IllegalVotingTimeException;
import com.github.Dnoil.restaurant_voting.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {
    public static Vote checkTime(Vote vote) {
        LocalDateTime assertTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(11,0));
        LocalDateTime voteDateTime = vote.getVotedDateTime();
        if (voteDateTime.isAfter(assertTime)) {
            throw new IllegalVotingTimeException("Too late to change the vote");
        }
        return vote;
    }
}
