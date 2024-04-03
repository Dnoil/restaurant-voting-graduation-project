package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Menu;
import com.github.Dnoil.restaurant_voting.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu get(int restaurantId) {
        return menuRepository.getByRestaurantId(restaurantId);
    }

    public Menu createOrUpdate(Menu menu) {
        return menuRepository.save(menu);
    }

    public void delete(int id) {
        menuRepository.delete(id);
    }
}
