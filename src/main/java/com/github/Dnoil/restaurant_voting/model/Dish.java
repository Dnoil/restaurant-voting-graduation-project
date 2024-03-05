package com.github.Dnoil.restaurant_voting.model;

import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "dish")
public class Dish extends BaseEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 0)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_menu", nullable = false)
    @NotNull
    private Menu menu;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
