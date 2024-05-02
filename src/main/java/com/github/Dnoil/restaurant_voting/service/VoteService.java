package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.error.NotFoundException;
import com.github.Dnoil.restaurant_voting.model.Vote;
import com.github.Dnoil.restaurant_voting.repository.VoteRepository;
import com.github.Dnoil.restaurant_voting.util.TimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.Dnoil.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    @Cacheable(value = "votes")
    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    public List<Vote> getAllWithOld() {
        return voteRepository.findAll();
    }

    public Vote getActual(int userId) {
        return checkNotFoundWithId(voteRepository.getActualByUserId(userId), userId);
    }

    public Vote get(int id) {
        return voteRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found vote with id=" + id));
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
