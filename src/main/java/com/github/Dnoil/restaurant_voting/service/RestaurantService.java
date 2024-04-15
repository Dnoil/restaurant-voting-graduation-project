package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Restaurant;
import com.github.Dnoil.restaurant_voting.model.Vote;
import com.github.Dnoil.restaurant_voting.repository.RestaurantRepository;
import com.github.Dnoil.restaurant_voting.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final VoteRepository voteRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    //TODO implement
    public List<Restaurant> getAllByVotes() {
        List<Vote> votes = voteRepository.findAll();
        return null;
    }

    //TODO implement exception
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElseThrow();
    }

    public Restaurant getByName(String name) {
        return restaurantRepository.getByName(name);
    }

    public Restaurant createOrUpdate(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }
}
