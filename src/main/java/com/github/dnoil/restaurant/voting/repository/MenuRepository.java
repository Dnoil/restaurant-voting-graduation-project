package com.github.dnoil.restaurant.voting.repository;

import com.github.dnoil.restaurant.voting.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository extends BaseRepository<Menu> {

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId AND m.day=:day")
    Menu getActualByRestaurantIdAndDay(@Param("restaurantId") int id, @Param("day") LocalDate day);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    List<Menu> getAllByRestaurantId(@Param("restaurantId") int id);
}
