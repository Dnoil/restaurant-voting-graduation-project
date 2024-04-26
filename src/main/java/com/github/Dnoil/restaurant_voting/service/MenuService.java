package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Menu;
import com.github.Dnoil.restaurant_voting.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.github.Dnoil.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu get(int restaurantId) {
        return checkNotFoundWithId(menuRepository.getByRestaurantId(restaurantId), restaurantId);
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
