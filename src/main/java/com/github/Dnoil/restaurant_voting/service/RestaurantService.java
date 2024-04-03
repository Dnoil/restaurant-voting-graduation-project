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
        return restaurantRepository.getAllByVotes();
    }

    public Restaurant get(int id) {
        return restaurantRepository.get(id);
    }

    public Restaurant getByName(String name) {
        return restaurantRepository.getByName(name);
    }

    public Restaurant createOrUpdate(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        restaurantRepository.delete(id);
    }
}
