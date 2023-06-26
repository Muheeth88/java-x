package com.xapp.xjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xapp.xjava.entities.Movie;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {
    
    
}
