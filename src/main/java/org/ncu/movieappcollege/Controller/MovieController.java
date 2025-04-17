package org.ncu.movieappcollege.Controller;

import org.ncu.movieappcollege.Model.Movie;
import org.ncu.movieappcollege.Repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
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
        movieRepository.saveAll(movies);
    }

    @GetMapping("/get")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<Movie> getMovieById(@PathVariable long id) {
        return movieRepository.findById(id);
    }

    @PutMapping("/update")
    public void updateMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable long id) {
        movieRepository.deleteById(id);
    }

    @DeleteMapping("/deleteBatch")
    public void batchDeleteMovies(@RequestBody List<Movie> movies) {
        movieRepository.deleteAll(movies);
    }

}
