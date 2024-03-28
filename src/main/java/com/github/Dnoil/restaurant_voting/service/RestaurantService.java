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

    public Restaurant get(int id) {
        return restaurantRepository.getReferenceById(id);
    }

    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }
}
