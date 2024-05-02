package com.github.Dnoil.restaurant_voting.repository;

import com.github.Dnoil.restaurant_voting.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.votedDateTime>CURRENT_DATE")
    Vote getActualByUserId(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.votedDateTime>CURRENT_DATE")
    List<Vote> getAll();
}
