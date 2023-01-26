package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.domain.category.CategoriesListingData;
import br.com.alura.aluraflix.domain.category.Category;
import br.com.alura.aluraflix.domain.category.CategoryDetaliedData;
import br.com.alura.aluraflix.domain.category.CategoryRepository;
import br.com.alura.aluraflix.domain.movie.Movie;
import br.com.alura.aluraflix.domain.movie.MovieDetaliedData;
import br.com.alura.aluraflix.domain.movie.MoviesListingData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class that receives requests for categories
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;
    /**
     * GET
     * Serve a GET request that displays all categories with the following endpoint: /category
     * @param pagination
     * @return Returns a list of categories
     */
    @GetMapping
    public ResponseEntity<Page<CategoriesListingData>> list(Pageable pagination) {
        var page = repository.findAll(pagination).map(CategoriesListingData::new);
        return ResponseEntity.ok(page);
    }

    /**
     * GET
     * Serve a GET request that returns a single category,
     * if the video ID is valid, with the following endpoint: /categories/1
     * @param id
     * @return If the video is not registered, return a Not Found message.
     */
    @GetMapping("/{id}")
    public ResponseEntity searchCategoryById(@PathVariable Long id){
        try {
            var category = repository.getReferenceById(id);
            return ResponseEntity.ok(new CategoryDetaliedData((Category)category));
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
