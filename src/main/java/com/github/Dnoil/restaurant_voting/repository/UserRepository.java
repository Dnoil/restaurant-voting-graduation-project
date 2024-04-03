package com.github.Dnoil.restaurant_voting.repository;

import com.github.Dnoil.restaurant_voting.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<User> {
    @Query("SELECT u FROM User u")
    List<User> getAll();

    //must check
    @Query("SELECT u FROM User u WHERE u.vote IS NULL")
    List<User> getAllNoVotes();

    @Query("SELECT u FROM User u WHERE u.id=:id")
    User get(@Param("id") int id);

    @Query("SELECT u FROM User u WHERE u.email=:email")
    User getByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);
}
