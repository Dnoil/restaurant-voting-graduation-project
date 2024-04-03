package com.github.Dnoil.restaurant_voting.web.controller;

import com.github.Dnoil.restaurant_voting.model.Restaurant;
import com.github.Dnoil.restaurant_voting.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    private RestaurantService restaurantService;

    @GetMapping
    public void getAll() {
        restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public void get(@PathVariable int id) {
        restaurantService.get(id);
    }

    @GetMapping("/{name}")
    public void getByName(@PathVariable String name) {
        restaurantService.getByName(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.createOrUpdate(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/restaurant/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant) {
        restaurantService.createOrUpdate(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        restaurantService.delete(id);
    }
}
