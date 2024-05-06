package com.github.dnoil.restaurant.voting.util;

import com.github.dnoil.restaurant.voting.error.NotFoundException;
import com.github.dnoil.restaurant.voting.model.Menu;
import com.github.dnoil.restaurant.voting.model.Vote;
import com.github.dnoil.restaurant.voting.error.IllegalVotingTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {
    public static Vote validateVoteDateTime(Vote vote) {
        LocalDateTime validDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(11,0));
        LocalDateTime voteDateTime = vote.getVotedDateTime();
        if (voteDateTime.isAfter(validDateTime)) {
            throw new IllegalVotingTimeException("Too late to change the vote (vote id=" + vote.id() + ")");
        }
        return vote;
    }

    public static Menu validateMenuDate(Menu menu) {
        LocalDate currentDate = LocalDate.now();
        LocalDate menuDate = menu.getDate();
        if (!menuDate.isEqual(currentDate)) {
            throw new NotFoundException("No today's menu (menu id=" + menu.id() + ")");
        }
        return menu;
    }
}
