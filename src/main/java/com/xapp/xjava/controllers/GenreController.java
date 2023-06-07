package com.xapp.xjava.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xapp.xjava.entities.Genre;
import com.xapp.xjava.services.GenreService;

@RestController
@RequestMapping("/movies/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("")
    ResponseEntity<Genre> addGenre(@RequestBody Genre req) {
        Genre newGenre = genreService.addGenre(req);
        return ResponseEntity.ok(newGenre);
    }

    @GetMapping("")
    ResponseEntity<ArrayList<Genre>> getAllGenres() {
        ArrayList<Genre> allGenres = genreService.getAllGenres();
        return ResponseEntity.ok(allGenres);
    }

    @GetMapping("/genreId")
    ResponseEntity<Optional<Genre>> getGenre(@PathVariable("genreId") Long genreId) {
        Optional<Genre> genres = genreService.getGenreById(genreId);
        return ResponseEntity.ok(genres);
    }

}