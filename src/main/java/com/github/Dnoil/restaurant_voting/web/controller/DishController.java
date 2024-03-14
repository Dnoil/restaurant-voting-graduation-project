package com.github.Dnoil.restaurant_voting.web.controller;

import com.github.Dnoil.restaurant_voting.model.Dish;
import com.github.Dnoil.restaurant_voting.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;

    @GetMapping
    public String dish() {
        return "dish";
    }
}
