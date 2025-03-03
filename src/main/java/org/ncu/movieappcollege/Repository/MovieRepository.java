package org.ncu.movieappcollege.Repository;

import org.ncu.movieappcollege.Model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

    public void updateMovieByName(String oldName, String newName) {
        String sql = "UPDATE movies SET movie_name = :newName WHERE movie_name = :oldName";
        Map<String, Object> params = new HashMap<>();
        params.put("newName", newName);
        params.put("oldName", oldName);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void  update(Movie movie) {
        String  sql = "UPDATE movies SET movie_name = ? WHERE movie_id = ?";
        jdbcTemplate.update(sql, movie.getMovieName(), movie.getMovieId());
    }

    public void delete(int  movieId) {
        String  sql = "DELETE FROM movies WHERE movie_id = ?";
        jdbcTemplate.update(sql, movieId);
    }

    public void batchDeleteRecords(List<Movie> movies) {
        String sql = "DELETE FROM movies WHERE movie_id = ?";
        jdbcTemplate.batchUpdate(sql, movies, movies.size(), (ps, id) -> ps.setInt(1, id.getMovieId()));
    }

    public List<Movie> paginatedRecords(int pageSize, int  pageNum ) {
        int pageOffset =  pageNum*pageSize;
        String sql = "SELECT * FROM movies LIMIT :pageSize OFFSET :pageOffset";
        Map<String, Object> params = new HashMap<>();
        params.put("pageSize", pageSize);
        params.put("pageOffset", pageOffset);
        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) ->  new Movie(
                rs.getInt("movie_Id"),
                rs.getString("movie_Name")
        ));
    }
}
