package com.github.dnoil.restaurant.voting.repository;

import com.github.dnoil.restaurant.voting.model.Vote;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.day=:day")
    Vote getActualByUserIdAndDay(@Param("userId") int userId, @Param("day")LocalDate day);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.day DESC")
    List<Vote> getAllByUserId(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.day=:day")
    List<Vote> getAllActualByDay(@Param("day") LocalDate day);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.user.id=:userId AND v.day=:day")
    void deleteByUserIdAndDay(@Param("userId") int userId, @Param("day") LocalDate day);
}
