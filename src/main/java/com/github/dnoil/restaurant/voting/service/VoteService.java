package com.github.dnoil.restaurant.voting.service;

import com.github.dnoil.restaurant.voting.model.Vote;
import com.github.dnoil.restaurant.voting.repository.VoteRepository;
import com.github.dnoil.restaurant.voting.util.TimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.github.dnoil.restaurant.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public List<Vote> getAllActual() {
        return voteRepository.getAllActualByDay(LocalDate.now());
    }

    public List<Vote> getAllByUserId(int userId) {
        return voteRepository.getAllByUserId(userId);
    }

    public Vote getActual(int userId) {
        return checkNotFoundWithId(voteRepository.getActualByUserIdAndDay(userId, LocalDate.now()), userId);
    }

    public Vote create(Vote vote) {
        Assert.notNull(vote, "Vote can not be null");
        //TimeUtil.validateVoteTime(vote);
        return voteRepository.save(vote);
    }

    public void update(Vote vote) {
        Assert.notNull(vote, "Vote can not be null");
        TimeUtil.validateVoteTime(vote);
        voteRepository.save(vote);
    }

    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id), id);
    }

    public void deleteByUserId(int userId) {
        voteRepository.deleteByUserIdAndDay(userId, LocalDate.now());
    }
}
