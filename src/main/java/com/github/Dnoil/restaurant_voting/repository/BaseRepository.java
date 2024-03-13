package com.github.Dnoil.restaurant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository <T, Integer> {

}
