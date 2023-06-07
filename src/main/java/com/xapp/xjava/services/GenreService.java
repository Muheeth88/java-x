package com.xapp.xjava.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapp.xjava.entities.Genre;
import com.xapp.xjava.repositories.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public ArrayList<Genre> getAllGenres() {
        ArrayList<Genre> allGenres = (ArrayList<Genre>) genreRepository.findAll();
        return allGenres;
    }

    public Optional<Genre> getGenreById(Long genreId) {
        Optional<Genre> genre = genreRepository.findById(genreId);
        return genre;
    }

    public Genre getGenre(String genre) {
        Genre reqGenre = genreRepository.findByGenre(genre);
        return reqGenre;
    }
}