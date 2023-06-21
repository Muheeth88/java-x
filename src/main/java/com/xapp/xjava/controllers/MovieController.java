package com.xapp.xjava.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.config.CustomUserDetails;
import com.xapp.xjava.entities.Movie;
import com.xapp.xjava.entities.User;
import com.xapp.xjava.models.PageResponse;
import com.xapp.xjava.repositories.UsersRepository;
import com.xapp.xjava.services.MoviesService;

@RestController
@CrossOrigin("*")
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MoviesService moviesService;

    @GetMapping("")
    ResponseEntity<PageResponse> getAllMovies(
        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "100", required = false ) Integer pageSize,
        @RequestParam(value = "orderBy", defaultValue = "movieId", required = false)  String orderBy,
        @RequestParam(value = "sortDirection", defaultValue = "asc", required = false)  String sortDirection
    ) {
        PageResponse pageResponse = moviesService.getAllMovies(pageNumber, pageSize, orderBy, sortDirection);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/{movieId}")
    ResponseEntity<Optional<Movie>> getUser(@PathVariable("movieId") Long movieId) {
        Optional<Movie> movie = moviesService.getMovieById(movieId);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Movie> addMovie(@AuthenticationPrincipal CustomUserDetails user, @RequestBody Movie req) throws Exception {
        Movie newMovie = moviesService.addMovie(req);
        return ResponseEntity.ok(newMovie);
    }


}
