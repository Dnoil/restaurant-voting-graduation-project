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

    public List<Dish> getAll(int menuId) {
        return dishRepository.getAllByMenuId(menuId);
    }

    public Dish get(int id) {
        return dishRepository.get(id);
    }

    public Dish createOrUpdate(Dish dish) {
        return dishRepository.save(dish);
    }

    public void delete(int id) {
        dishRepository.delete(id);
    }
}
