package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Vote;
import com.github.Dnoil.restaurant_voting.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.github.Dnoil.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    public Vote get(int userId) {
        Vote vote = voteRepository.getByUserId(userId);
        if (vote == null) {
            throw new NoSuchElementException();
        }
        return vote;
    }

    public Vote createOrUpdate(Vote vote) {
        return voteRepository.save(vote);
    }

    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id), id);
    }
}
