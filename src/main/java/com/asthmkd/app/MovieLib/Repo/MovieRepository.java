package com.asthmkd.app.MovieLib.Repo;

import com.asthmkd.app.MovieLib.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
