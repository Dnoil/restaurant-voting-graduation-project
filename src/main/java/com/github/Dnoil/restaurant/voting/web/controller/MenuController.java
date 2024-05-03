package com.github.Dnoil.restaurant.voting.web.controller;

import com.github.Dnoil.restaurant.voting.service.MenuService;
import com.github.Dnoil.restaurant.voting.model.Menu;
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
@RequestMapping(value = "/admin/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    private MenuService menuService;

    @GetMapping("/old/restaurant")
    public List<Menu> getAll(@RequestParam int id) {
        return menuService.getAll(id);
    }

    @GetMapping("/restaurant")
    public Menu get(@RequestParam int id) {
        return menuService.getActual(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody @Valid Menu menu) {
        ValidationUtil.checkNew(menu);
        Menu created = menuService.create(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/menus/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Menu menu, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(menu, id);
        menuService.update(menu);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        menuService.delete(id);
    }

}
