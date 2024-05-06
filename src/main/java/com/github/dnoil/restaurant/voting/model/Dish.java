package com.github.dnoil.restaurant.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Entity
@Table(name = "dish", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "menu_id"}, name = "unique_dish_per_menu"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish extends BaseEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 0)
    private BigDecimal price;

    @JsonBackReference
    @JoinColumn(name = "menu_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Menu menu;

    public Dish(Integer id, String name, BigDecimal price, Menu menu) {
        super(id, name);
        this.price = price;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + price +
                ", menu=" + menu +
                '}';
    }
}
