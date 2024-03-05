package com.github.Dnoil.restaurant_voting.model;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends BaseEntity {

    @Column(name = "date")
    @NotNull
    private Date date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_restaurant", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_dish")
    private List<Dish> dishes;

}
