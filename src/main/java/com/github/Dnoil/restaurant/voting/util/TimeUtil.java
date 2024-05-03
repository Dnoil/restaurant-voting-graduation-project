package com.github.Dnoil.restaurant.voting.util;

import com.github.Dnoil.restaurant.voting.error.IllegalVotingTimeException;
import com.github.Dnoil.restaurant.voting.model.Menu;
import com.github.Dnoil.restaurant.voting.error.NotFoundException;
import com.github.Dnoil.restaurant.voting.model.Vote;

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
