package com.github.Dnoil.restaurant_voting.repository;

import com.github.Dnoil.restaurant_voting.model.Menu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MenuRepository extends BaseRepository<Menu> {

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    Menu getByRestaurantId(@Param("restaurantId") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);
}
