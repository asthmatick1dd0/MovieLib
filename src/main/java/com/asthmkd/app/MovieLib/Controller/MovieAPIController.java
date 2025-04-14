package com.asthmkd.app.MovieLib.Controller;

import com.asthmkd.app.MovieLib.Models.Movie;
import com.asthmkd.app.MovieLib.Service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieAPIController {
    private final MovieService movieService;

    public MovieAPIController(MovieService movieService) {
        this.movieService = movieService;
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
    public String addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return "saved";
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
