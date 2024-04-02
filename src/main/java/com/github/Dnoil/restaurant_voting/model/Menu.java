package com.github.Dnoil.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor
public class Menu extends BaseEntity {

    @Column(name = "date")
    @NotNull
    private Date date;

    @JoinColumn(name = "restaurant_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private List<Dish> dishes;
}
