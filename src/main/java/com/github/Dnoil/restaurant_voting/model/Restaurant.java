package com.github.Dnoil.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Menu menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Vote> votes;
}
