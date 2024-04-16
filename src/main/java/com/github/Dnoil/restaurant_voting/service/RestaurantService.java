package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Restaurant;
import com.github.Dnoil.restaurant_voting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    //TODO it in service or in repository
    public List<Restaurant> getAllByVotes() {
        return restaurantRepository.getAllByVotes();
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
