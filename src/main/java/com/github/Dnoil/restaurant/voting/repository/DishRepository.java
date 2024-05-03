package com.github.Dnoil.restaurant.voting.repository;

import com.github.Dnoil.restaurant.voting.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menuId")
    List<Dish> getAllByMenuId(@Param("menuId") int menuId);
}
