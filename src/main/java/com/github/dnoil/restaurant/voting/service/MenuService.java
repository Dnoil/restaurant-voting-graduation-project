package com.github.dnoil.restaurant.voting.service;

import com.github.dnoil.restaurant.voting.model.Menu;
import com.github.dnoil.restaurant.voting.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.github.dnoil.restaurant.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<Menu> getAll(int restaurantId) {
        return menuRepository.getAllByRestaurantId(restaurantId);
    }

    public Menu getActual(int restaurantId) {
        return checkNotFoundWithId(menuRepository.getActualByRestaurantIdAndDay(restaurantId, LocalDate.now()), restaurantId);
    }

    public Menu create(Menu menu) {
        Assert.notNull(menu, "Menu can not be null");
        return menuRepository.save(menu);
    }

    public void update(Menu menu) {
        Assert.notNull(menu, "Menu can not be null");
        menuRepository.save(menu);
    }


    public void delete(int id) {
        checkNotFoundWithId(menuRepository.delete(id), id);
    }
}
