package com.github.dnoil.restaurant.voting.service;

import com.github.dnoil.restaurant.voting.model.Restaurant;
import com.github.dnoil.restaurant.voting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.github.dnoil.restaurant.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Cacheable(value = "restaurants")
    public List<Restaurant> getAllActual() {
        return restaurantRepository.getAllActual(LocalDate.now());
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepository.findById(id), id);
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
