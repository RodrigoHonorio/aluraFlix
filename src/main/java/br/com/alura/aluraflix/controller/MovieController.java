package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.domain.movie.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller class that receives requests for movies
 */
@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    /**
     * GET
     * Serve a GET request that displays all videos with the following endpoint: /videos
     * @param pagination
     * @return Returns a list of videos
     */
    @GetMapping
    public ResponseEntity<Page<MoviesListingData>> list(Pageable pagination) {
        var page = repository.findAll(pagination).map(MoviesListingData::new);
        return ResponseEntity.ok(page);
    }

    /**
     * GET
     * Serve a GET request that returns a single video,
     * if the video ID is valid, with the following endpoint: /movies/1
     * @param id
     * @return If the video is not registered, return a Not Found message.
     */
    @GetMapping("/{id}")
    public ResponseEntity searchMovieById(@PathVariable Long id){
        try {
            var movie = repository.getReferenceById(id);
            return ResponseEntity.ok(new MovieDetaliedData((Movie)movie));
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * POST
     * Respond to a POST request that creates a new video, if all fields are filled in and validated.
     * @param data
     * @param uriBuilder
     * @return Json with information about the created video.
     */
    @PostMapping
    @Transactional
    public ResponseEntity addMovie(@RequestBody @Valid DataAddMovie data, UriComponentsBuilder uriBuilder){
        var movie = new Movie(data);
        repository.save(movie);

        var uri = uriBuilder.path("/movies/{id}").buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(uri).body(new MovieDetaliedData(movie));
    }

    /**
     * DELETE
     * Respond to a request capable of deleting a video by ID, as shown in the example endpoint:
     * @param id
     * @return Return a success or failure message.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id){
        try {
            var movie = repository.getReferenceById(id);
            repository.delete(movie);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     *
     PUT / PATCH
     Answer a request capable of updating one or more fields of a video.
     * @param data "Movie"
     * @return Json with updated movie information.
     */
    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestBody @Valid MovieDetaliedData data){
        try {
            var movie = repository.getReferenceById(data.id());
            System.out.println("Aque ->>> "+ movie.getName());
            System.out.println("Aque ->>> "+ movie.getDescription());
            System.out.println("Aque ->>> "+ movie.getUrl());
            movie.update(data);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
