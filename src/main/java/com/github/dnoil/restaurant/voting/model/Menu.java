package com.github.dnoil.restaurant.voting.model;

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
@Table(name = "menu", uniqueConstraints = @UniqueConstraint(columnNames = {"date", "restaurant_id"},
        name = "single_menu_per_day"), indexes = @Index(columnList = "date", name = "date_idx"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {

    @Column(name = "date")
    @NotNull
    private LocalDate date = LocalDate.now();

    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Dish> dishes;

    public Menu(Integer id, String name, Restaurant restaurant) {
        super(id, name);
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
