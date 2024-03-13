package com.github.Dnoil.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor
public class Menu extends BaseEntity {

    @Column(name = "date")
    @NotNull
    private Date date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_restaurant", nullable = false)
    @NotNull
    private Restaurant restaurant;
}
