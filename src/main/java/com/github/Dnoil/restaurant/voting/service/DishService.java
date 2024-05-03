package com.github.Dnoil.restaurant.voting.service;

import com.github.Dnoil.restaurant.voting.error.NotFoundException;
import com.github.Dnoil.restaurant.voting.model.Dish;
import com.github.Dnoil.restaurant.voting.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.Dnoil.restaurant.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public List<Dish> getAll(int menuId) {
        return dishRepository.getAllByMenuId(menuId);
    }

    public Dish get(int id) {
        return dishRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found dish with id=" + id));
    }

    public Dish create(Dish dish) {
        Assert.notNull(dish, "Dish can not be null");
        return dishRepository.save(dish);
    }

    public void update(Dish dish) {
        Assert.notNull(dish, "Dish can not be null");
        dishRepository.save(dish);
    }

    public void delete(int id) {
        checkNotFoundWithId(dishRepository.delete(id), id);
    }
}
