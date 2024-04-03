package com.github.Dnoil.restaurant_voting.repository;

import com.github.Dnoil.restaurant_voting.model.Dish;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menuId")
    List<Dish> getAllByMenuId(@Param("menuId") int menuId);

    @Query("SELECT d FROM Dish d WHERE d.id=:id")
    Dish get(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);
}
