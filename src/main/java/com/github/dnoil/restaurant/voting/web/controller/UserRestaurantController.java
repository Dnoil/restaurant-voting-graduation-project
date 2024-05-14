package com.github.dnoil.restaurant.voting.web.controller;

import com.github.dnoil.restaurant.voting.model.Restaurant;
import com.github.dnoil.restaurant.voting.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController {
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAllActual();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return restaurantService.get(id);
    }

    @GetMapping("/name")
    public Restaurant getByName(@RequestParam String value) {
        return restaurantService.getByName(value);
    }
}
