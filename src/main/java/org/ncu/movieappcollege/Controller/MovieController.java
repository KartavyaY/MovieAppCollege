package org.ncu.movieappcollege.Controller;

import org.ncu.movieappcollege.Model.Movie;
import org.ncu.movieappcollege.Repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    @PostMapping("/batchInsert")
    public void batchInsert(@RequestBody List<Movie> movies) {
        movieRepository.batchInsertRecords(movies);
    }

    @GetMapping("/get")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieRepository.findById(id);
    }

    @PostMapping("/update")
    public void updateMovie(@RequestBody Movie movie) {
        movieRepository.update(movie);
    }

//    @PostMapping("/batchUpdate")
//    public void batchUpdate(@RequestBody List<Movie> movies) {
//        movieRepository.batchUpdateRecords(movies);
//    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieRepository.delete(id);
    }

    @DeleteMapping("/deleteBatch")
    public void batchDeleteMovies(@RequestBody List<Movie> movies) {
        movieRepository.batchDeleteRecords(movies);
    }

}
