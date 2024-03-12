package com.github.Dnoil.restaurant_voting.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(max = 128)
    private String address;

    @Column(name = "voted_popularity")
    private Integer votedPopularity;

    public Restaurant() {
    }
}
