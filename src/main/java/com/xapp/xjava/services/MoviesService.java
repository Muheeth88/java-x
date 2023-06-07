package com.xapp.xjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapp.xjava.entities.Movie;
import com.xapp.xjava.repositories.MoviesRepository;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    public Movie addMovie(Movie newMovie) {

        return moviesRepository.save(newMovie);
    }

    public List<Movie> getAllMovies() {
        List<Movie> allMovies = moviesRepository.findAll();
        return allMovies;
    }

    public Optional<Movie> getUser(Long userId) {
        Optional<Movie> movie = moviesRepository.findById(userId);
        return movie;
    }

}
