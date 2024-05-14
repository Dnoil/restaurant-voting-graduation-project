package com.github.dnoil.restaurant.voting.repository;

import com.github.dnoil.restaurant.voting.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menuId")
    List<Dish> getAllByMenuId(@Param("menuId") int menuId);
}
