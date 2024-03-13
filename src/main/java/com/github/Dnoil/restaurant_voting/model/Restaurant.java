package com.github.Dnoil.restaurant_voting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant extends BaseEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(max = 128)
    private String address;

    @Column(name = "voted_popularity")
    private Integer votedPopularity;
}
