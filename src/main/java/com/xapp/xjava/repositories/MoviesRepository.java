package com.xapp.xjava.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xapp.xjava.entities.Movie;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select m from Movie m where ( :title is null or lower(m.title) like lower(concat('%', cast(:title as string), '%')))", nativeQuery = false)
    Page<Movie> findAllMovies(Pageable page, String title);


    
}
