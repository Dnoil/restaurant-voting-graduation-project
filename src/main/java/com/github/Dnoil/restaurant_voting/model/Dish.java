package com.github.Dnoil.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
public class Dish extends BaseEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 0)
    private Integer price;

    @JoinColumn(name = "menu_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Menu menu;
}
