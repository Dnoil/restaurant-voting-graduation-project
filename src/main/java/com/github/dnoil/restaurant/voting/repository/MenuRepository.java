package com.github.dnoil.restaurant.voting.repository;

import com.github.dnoil.restaurant.voting.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends BaseRepository<Menu> {

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId AND m.date=CURRENT_DATE")
    Menu getActualByRestaurantId(@Param("restaurantId") int id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    List<Menu> getAllByRestaurantId(@Param("restaurantId") int id);
}
