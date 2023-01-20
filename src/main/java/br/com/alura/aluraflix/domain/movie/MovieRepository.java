package br.com.alura.aluraflix.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface that creates a repository for interacting with the database
 */

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
