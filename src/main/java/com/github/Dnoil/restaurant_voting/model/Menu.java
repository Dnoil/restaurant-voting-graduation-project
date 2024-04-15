package com.github.Dnoil.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    //@JsonManagedReference(value = "restaurant-menu")
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    @NotNull
    private Restaurant restaurant;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Dish> dishes;

    public Menu(Integer id, String name, LocalDate date, Restaurant restaurant) {
        super(id, name);
        this.date = date;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", date=" + date +
                ", restaurant=" + restaurant +
                ", dishes=" + dishes +
                '}';
    }
}
