package com.github.dnoil.restaurant.voting.service;

import com.github.dnoil.restaurant.voting.model.Vote;
import com.github.dnoil.restaurant.voting.repository.VoteRepository;
import com.github.dnoil.restaurant.voting.util.TimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.dnoil.restaurant.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    @Cacheable(value = "votes")
    public List<Vote> getAllActual() {
        return voteRepository.getAllActual();
    }

    public List<Vote> getAllByUserId(int userId) {
        return voteRepository.getAllByUserId(userId);
    }

    public Vote getActual(int userId) {
        return checkNotFoundWithId(voteRepository.getActualByUserId(userId), userId);
    }

    @CacheEvict(value = "votes", allEntries = true)
    public Vote create(Vote vote) {
        Assert.notNull(vote, "Vote can not be null");
        return voteRepository.save(vote);
    }

    @CacheEvict(value = "votes", allEntries = true)
    public void update(Vote vote) {
        Assert.notNull(vote, "Vote can not be null");
        TimeUtil.validateVoteDateTime(vote);
        voteRepository.save(vote);
    }

    @CacheEvict(value = "votes", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id), id);
    }
}