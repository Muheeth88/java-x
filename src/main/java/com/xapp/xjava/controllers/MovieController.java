package com.xapp.xjava.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.config.CustomUserDetails;
import com.xapp.xjava.entities.Movie;
import com.xapp.xjava.entities.User;
import com.xapp.xjava.repositories.UsersRepository;
import com.xapp.xjava.services.MoviesService;

@RestController
@CrossOrigin("*")
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MoviesService moviesService;

    @Autowired
	private UsersRepository usersRepository;

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
    ResponseEntity<Movie> addMovie(@AuthenticationPrincipal CustomUserDetails user, @RequestBody Movie req) throws Exception {
        String userName = user.getUsername();
        User userDetails = usersRepository.findByEmail(userName);
        if(userDetails.getRole() == "ADMIN") {
            Movie newMovie = moviesService.addMovie(req);
            return ResponseEntity.ok(newMovie);
        } else {
            System.out.println("You are not an Admin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return null;
        }
    }
}
