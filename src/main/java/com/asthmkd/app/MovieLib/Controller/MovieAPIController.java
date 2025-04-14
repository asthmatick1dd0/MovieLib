package com.asthmkd.app.MovieLib.Controller;

import com.asthmkd.app.MovieLib.Models.Movie;
import com.asthmkd.app.MovieLib.Service.MovieService;
import com.asthmkd.app.MovieLib.Service.OMDbService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieAPIController {
    private final MovieService movieService;
    private final OMDbService omdbService;

    public MovieAPIController(MovieService movieService, OMDbService omdbService) {
        this.movieService = movieService;
        this.omdbService = omdbService;
    }
    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value = "/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(value = "/movies/{id}")
    public Movie getMovieById(@PathVariable long  id) {
        return movieService.getMovieById(id);
    }

    @PostMapping(value = "/save")
    public Movie fetchAndSaveMovie(@RequestBody Movie movie_) {
        Movie movie = omdbService.fetchMovieDetails(movie_.getTitle());
        movie.setUserRating(movie_.getUserRating());
        movie.setStatus(movie_.getStatus());
        return movieService.addMovie(movie);
    }

    @PutMapping(value = "/update/{id}")
    public String updateMovie(@PathVariable long id, @RequestBody Movie movie) {
        movieService.updateMovie(id, movie);
        return "updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return "deleted";
    }

    @DeleteMapping(value = "/delete")
    public String deleteAllMovies() {
        movieService.deleteAllMovies();
        return "all movie deleted";
    }

    @DeleteMapping(value = "/delete/range/{idRange}")
    public String deleteRangeMovies(@PathVariable String idRange) {
        movieService.deleteRangeMovie(idRange);
        return "deleted movies in range" + idRange;
    }
}
