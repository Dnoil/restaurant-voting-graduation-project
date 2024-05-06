package com.github.dnoil.restaurant.voting.service;

import com.github.dnoil.restaurant.voting.model.Restaurant;
import com.github.dnoil.restaurant.voting.repository.RestaurantRepository;
import com.github.dnoil.restaurant.voting.error.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.dnoil.restaurant.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Cacheable(value = "restaurants")
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getAllByVotes() {
        return restaurantRepository.getAllByVotes();
    }

    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found dish with id=" + id));
    }

    public Restaurant getByName(String name) {
        return restaurantRepository.getByName(name);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant can not be null");
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant can not be null");
        restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }
}
