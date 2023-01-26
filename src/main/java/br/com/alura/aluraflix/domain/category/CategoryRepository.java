package br.com.alura.aluraflix.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface that creates a repository for interacting with the database
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
