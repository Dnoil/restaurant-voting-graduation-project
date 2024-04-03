package com.github.Dnoil.restaurant_voting.repository;

import com.github.Dnoil.restaurant_voting.model.Vote;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VoteRepository extends BaseRepository<Vote> {
    @Query("SELECT v FROM Vote v")
    List<Vote> getAll();

    @Query("SELECT v FROM Vote v RIGHT JOIN User u ON u.id = v.user.id")
    List<Vote> getAllWithNoVotes();

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
    Vote getByUserId(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);
}
