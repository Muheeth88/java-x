package com.xapp.xjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xapp.xjava.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByGenre(String genre);

}
