package com.xapp.xjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xapp.xjava.entities.Movie;
import com.xapp.xjava.models.PageResponse;
import com.xapp.xjava.repositories.MoviesRepository;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    public Movie addMovie(Movie newMovie) {
        return moviesRepository.save(newMovie);
    }

    public PageResponse getAllMovies(Integer pageNumber, Integer pageSize, String orderBy) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(orderBy));
        Page<Movie> pageMovies = moviesRepository.findAll(page);
        List<Movie> allMovies =  pageMovies.getContent();
        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(allMovies);
        pageResponse.setPageNumber(pageMovies.getNumber());
        pageResponse.setPageSize(pageMovies.getSize());
        pageResponse.setTotalElements(pageMovies.getTotalElements());
        pageResponse.setTotalPages(pageMovies.getTotalPages());
        pageResponse.setLastPage(pageMovies.isLast());
        return pageResponse;
    }

    public Optional<Movie> getMovieById(Long movieId) {
        Optional<Movie> movie = moviesRepository.findById(movieId);
        return movie;
    }

}
