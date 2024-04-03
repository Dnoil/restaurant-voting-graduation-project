package com.github.Dnoil.restaurant_voting.repository;

import com.github.Dnoil.restaurant_voting.model.Restaurant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("SELECT r FROM Restaurant r ORDER BY r.votes.size DESC")
    List<Restaurant> getAllByVotes();

    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant get(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r WHERE CONTAINS(r.name, :name) ")
    Restaurant getByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);
}
