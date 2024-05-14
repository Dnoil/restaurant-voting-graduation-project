package com.github.dnoil.restaurant.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Entity
@Table(name = "dish", uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "name"}, name = "unique_dish_per_menu"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish extends BaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 1, max = 128)
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 0)
    private BigDecimal price;

    @JsonBackReference
    @JoinColumn(name = "menu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    private Menu menu;

    public Dish(Integer id, String name, BigDecimal price, Menu menu) {
        super(id);
        this.name = name;
        this.price = price;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
