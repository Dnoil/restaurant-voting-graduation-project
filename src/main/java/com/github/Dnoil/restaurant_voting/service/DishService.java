package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Dish;
import com.github.Dnoil.restaurant_voting.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish get(int id) {
        return dishRepository.getReferenceById(id);
    }

    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish update(Dish dish) {
        return dishRepository.save(dish);
    }

    public void delete(int id) {
        dishRepository.deleteById(id);
    }
}
