package br.com.alura.aluraflix.controller;

import br.com.alura.aluraflix.domain.movie.MovieRepository;
import br.com.alura.aluraflix.domain.movie.MoviesListingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping
    public ResponseEntity<Page<MoviesListingData>> list(Pageable pagination) {
        var page = repository.findAll(pagination).map(MoviesListingData::new);
        return ResponseEntity.ok(page);
    }

}
