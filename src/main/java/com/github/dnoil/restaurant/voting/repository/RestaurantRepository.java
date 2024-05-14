package com.github.dnoil.restaurant.voting.repository;

import com.github.dnoil.restaurant.voting.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE :name")
    Restaurant getByName(@Param("name") String name);

    @Query("SELECT r FROM Restaurant r LEFT JOIN Menu m ON r.id = m.restaurant.id WHERE m.day=:day")
    List<Restaurant> getAllActual(@Param("day") LocalDate day);
}
