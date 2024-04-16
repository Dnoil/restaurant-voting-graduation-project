package com.github.Dnoil.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends BaseEntity {

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String address;

    @JsonBackReference(value = "restaurant-menu")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Menu menu;

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
