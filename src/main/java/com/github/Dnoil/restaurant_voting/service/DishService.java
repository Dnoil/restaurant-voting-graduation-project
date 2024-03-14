package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Dish;
import com.github.Dnoil.restaurant_voting.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public Dish get(int id) {
        return dishRepository.findById(id).get();
    }
}
