package org.ncu.movieappcollege.Repository;

import org.ncu.movieappcollege.Model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Movie movie) {
        String  sql = "INSERT INTO movies (movie_id, movie_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, movie.getMovieId(), movie.getMovieName());
    }

    public void batchInsertRecords(List<Movie> movies) {
        String sql = "INSERT INTO movies (movie_id, movie_name) VALUES (?, ?)";
        jdbcTemplate.batchUpdate(sql, movies, movies.size(), (ps, movie) -> {
            ps.setLong(1, movie.getMovieId());
            ps.setString(2, movie.getMovieName());
        });
    }

    public List<Movie> findAll() {
        String sql = "SELECT * FROM movies";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Movie(
                rs.getInt("movie_Id"),
                rs.getString("movie_Name")
        ));
    }

    public Movie findById(int movieId) {
        String sql = "SELECT * FROM movies WHERE movie_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{movieId}, (rs, rowNum) -> new Movie(
                rs.getInt("movie_Id"),
                rs.getString("movie_Name")
        ));
    }

    public void  update(Movie movie) {
        String  sql = "UPDATE movies SET movie_name = ? WHERE movie_id = ?";
        jdbcTemplate.update(sql, movie.getMovieName(), movie.getMovieId());
    }

    public void delete(int  movieId) {
        String  sql = "DELETE FROM movies WHERE movie_id = ?";
        jdbcTemplate.update(sql, movieId);
    }
}
