package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.domain.movie.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;



/**
 * Controller class that receives requests for movies
 */
@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;
    /** Method called by the GET verb that returns a list of movies
     * does not receive parameters from the uri and returns a list **/
    @GetMapping
    public ResponseEntity<Page<MoviesListingData>> list(Pageable pagination) {
        var page = repository.findAll(pagination).map(MoviesListingData::new);
        return ResponseEntity.ok(page);
    }
    /** GET request that returns a single video, if the video ID is valid, with the following endpoint:
     * /videos/1
     * If the video is not registered, return a Not Found message.*/
    @GetMapping("/{id}")
    public ResponseEntity searchMovieById(@PathVariable Long id){
        try {
            var movie = repository.getReferenceById(id);
            return ResponseEntity.ok(new MovieDetaliedData((Movie)movie));
        }catch(EntityNotFoundException ex){
            return (ResponseEntity) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }
    @PostMapping
    @Transactional
    public ResponseEntity addMovie(@RequestBody @Valid DataAddMovie data, UriComponentsBuilder uriBuilder){
        var movie = new Movie(data);
        repository.save(movie);

        var uri = uriBuilder.path("/movies/{id}").buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(uri).body(new MovieDetaliedData(movie));
    }

}
