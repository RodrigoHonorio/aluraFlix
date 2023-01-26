package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.domain.category.*;
import br.com.alura.aluraflix.domain.movie.DataAddMovie;
import br.com.alura.aluraflix.domain.movie.Movie;
import br.com.alura.aluraflix.domain.movie.MovieDetaliedData;
import br.com.alura.aluraflix.domain.movie.MoviesListingData;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    /**
     * POST
     * Respond to a POST request that creates a new category, if all fields are filled in and validated.
     * @param data
     * @param uriBuilder
     * @return Json with information about the created category.
     */
    @PostMapping
    @Transactional
    public ResponseEntity addCategory(@RequestBody @Valid DataAddCategory data, UriComponentsBuilder uriBuilder){
        var category = new Category(data);
        repository.save(category);

        var uri = uriBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryDetaliedData(category));
    }
}
