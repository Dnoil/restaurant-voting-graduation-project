package com.github.Dnoil.restaurant_voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository <T, Integer>, CrudRepository<T, Integer> {

}
