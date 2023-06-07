package com.xapp.xjava.controllers;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.entities.Movie;
import com.xapp.xjava.services.MoviesService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MoviesService moviesService;

    // @GetMapping("")
    // ResponseEntity<List<Movie>> getAllMovies(@AuthenticationPrincipal CustomUserDetails user) {
    //     System.out.println(user.getUsername() + " yoo!!!!!!!!!!!!!");
    //     List<Movie> allMovies = moviesService.getAllMovies();
    //     return ResponseEntity.ok(allMovies);
    // }

    @GetMapping("")
    ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> allMovies = moviesService.getAllMovies();
        return ResponseEntity.ok(allMovies);
    }

    @GetMapping("/{movieId}")
    ResponseEntity<Optional<Movie>> getUser(@PathVariable("movieId") Long movieId) {
        Optional<Movie> movie = moviesService.getUser(movieId);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("")
    ResponseEntity<Movie> addMovie(@RequestBody Movie req) {
        Movie newMovie = moviesService.addMovie(req);
        return ResponseEntity.ok(newMovie);
    }
}
