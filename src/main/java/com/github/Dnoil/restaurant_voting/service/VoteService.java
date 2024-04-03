package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Vote;
import com.github.Dnoil.restaurant_voting.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    public List<Vote> getAllWithNoVotes() {
        return voteRepository.getAllWithNoVotes();
    }

    public Vote get(int userId) {
        return voteRepository.getByUserId(userId);
    }

    public Vote createOrUpdate(Vote vote) {
        return voteRepository.save(vote);
    }

    public void delete(int id) {
        voteRepository.delete(id);
    }
}
