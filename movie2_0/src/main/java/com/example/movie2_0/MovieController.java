package com.example.movie2_0;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> listEmployees(@RequestParam Optional<String> title) {
        return movieService.getMovies(title);
    }

    @GetMapping("/{id}")
    public MovieDto findMovieById(@PathVariable("id") long id) {
        return movieService.findMovieById(id);

    }

    @PostMapping
    public MovieDto createMovie(@RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        movieService.deleteMovie(id);
    }

    @PostMapping("/{id}/rating")
    public MovieDto ratingMovie(@PathVariable("id") long id, @RequestBody RatingMovieCommand command) {
        return movieService.addRating(id, command);

    }



}
