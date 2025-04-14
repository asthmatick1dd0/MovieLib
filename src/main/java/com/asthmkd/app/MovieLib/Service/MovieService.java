package com.asthmkd.app.MovieLib.Service;

import com.asthmkd.app.MovieLib.Models.Movie;
import com.asthmkd.app.MovieLib.Models.StatusEnum;
import com.asthmkd.app.MovieLib.Repo.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String getPage() {
        return "Welcome";
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));

    }

    public Movie addMovie(@RequestBody Movie movie) {
        if (movie.getStatus() == StatusEnum.WATCHED) {
            movie.setWatchedDate(LocalDate.now());
        }
        return movieRepository.save(movie);
    }

    public Movie updateMovie(@PathVariable long id, @RequestBody Movie movie) {
        Movie updateMovie = movieRepository.findById(id).get();
        updateMovie.setTitle(movie.getTitle());
        updateMovie.setGenre(movie.getGenre());
        updateMovie.setMovieDate(movie.getMovieDate());
        updateMovie.setStatus(movie.getStatus());
        if (movie.getStatus() == StatusEnum.WATCHED) {
            updateMovie.setWatchedDate(LocalDate.now());
        }
        return movieRepository.save(updateMovie);
    }

    public void deleteMovie(@PathVariable long id) {
        movieRepository.deleteById(id);
    }

    public void deleteAllMovies(){
        movieRepository.deleteAll();
    }

    public void deleteRangeMovie(@PathVariable String ids) {
        String[] idrange = ids.split("-");
        long firstId = Long.parseLong(idrange[0]);
        long lastId = Long.parseLong(idrange[1]);

        for (long i = firstId; i <= lastId; i++) {
            movieRepository.deleteById(i);
        }
    }



}
