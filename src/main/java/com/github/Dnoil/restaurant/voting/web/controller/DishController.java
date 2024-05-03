package com.github.Dnoil.restaurant.voting.web.controller;

import com.github.Dnoil.restaurant.voting.service.DishService;
import com.github.Dnoil.restaurant.voting.model.Dish;
import com.github.Dnoil.restaurant.voting.util.ValidationUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/admin/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {

    private DishService dishService;

    @GetMapping("/menu")
    public List<Dish> getAll(@RequestParam int id) {
        return dishService.getAll(id);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        return dishService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody @Valid Dish dish) {
        ValidationUtil.checkNew(dish);
        Dish created = dishService.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/dishes/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Dish dish, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(dish, id);
        dishService.update(dish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        dishService.delete(id);
    }
}