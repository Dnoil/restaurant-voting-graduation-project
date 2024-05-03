package com.github.Dnoil.restaurant.voting.repository;

import com.github.Dnoil.restaurant.voting.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE :name")
    Restaurant getByName(@Param("name") String name);

    @Query("SELECT r, COUNT(v) FROM Restaurant r LEFT JOIN Vote v ON r.id = v.restaurant.id GROUP BY r ORDER BY COUNT(v) DESC")
    List<Restaurant> getAllByVotes();
}
