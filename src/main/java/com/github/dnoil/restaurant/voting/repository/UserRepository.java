package com.github.dnoil.restaurant.voting.repository;

import com.github.dnoil.restaurant.voting.model.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    User getByEmail(String email);
}
