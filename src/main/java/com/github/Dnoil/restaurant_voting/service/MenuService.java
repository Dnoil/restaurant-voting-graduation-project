package com.github.Dnoil.restaurant_voting.service;

import com.github.Dnoil.restaurant_voting.model.Menu;
import com.github.Dnoil.restaurant_voting.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    public Menu get(int id) {
        return menuRepository.getReferenceById(id);
    }

    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu update(Menu menu) {
        return menuRepository.save(menu);
    }

    public void delete(int id) {
        menuRepository.deleteById(id);
    }
}
