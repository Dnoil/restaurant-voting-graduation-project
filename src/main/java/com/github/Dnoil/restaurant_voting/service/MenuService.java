package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Menu;
import com.github.Dnoil.restaurant_voting.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.github.Dnoil.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    //FIXME
    public Menu get(int restaurantId) {
        Menu menu = menuRepository.getByRestaurantId(restaurantId);
        if (menu == null) {
            throw new NoSuchElementException();
        }
        return menu;
    }

    public Menu createOrUpdate(Menu menu) {
        return menuRepository.save(menu);
    }

    public void delete(int id) {
        checkNotFoundWithId(menuRepository.delete(id), id);
    }
}
