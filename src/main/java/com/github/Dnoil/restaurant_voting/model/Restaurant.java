package com.github.Dnoil.restaurant_voting.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_menu")
    private Menu menu;

    @Column(name = "voted_popularity")
    private Integer votedPopularity;
}
