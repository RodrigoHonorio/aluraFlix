package br.com.alura.aluraflix.domain.movie;

import br.com.alura.aluraflix.domain.category.Category;

import java.util.Optional;


public record MovieDetaliedData(Long id, String name, String description, String url) {
    public MovieDetaliedData(Movie movie) {
        this(movie.getId(),movie.getName(),movie.getDescription(),movie.getUrl());
    }



}
