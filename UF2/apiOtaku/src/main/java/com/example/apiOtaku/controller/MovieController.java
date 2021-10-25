package com.example.apiOtaku.controller;

import com.example.apiOtaku.domain.model.Movie;
import com.example.apiOtaku.repository.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

}
