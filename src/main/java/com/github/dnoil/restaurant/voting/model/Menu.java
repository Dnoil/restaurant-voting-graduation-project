package com.github.dnoil.restaurant.voting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "day"},
        name = "single_menu_per_day"), indexes = @Index(columnList = "day", name = "date_idx"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {

    @Column(name = "day")
    @NotNull
    private LocalDate day = LocalDate.now();

    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    private Restaurant restaurant;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Dish> dishes;

    public Menu(Integer id, Restaurant restaurant) {
        super(id);
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + getId() + '\'' +
                ", day=" + day +
                ", restaurant=" + restaurant +
                '}';
    }
}
