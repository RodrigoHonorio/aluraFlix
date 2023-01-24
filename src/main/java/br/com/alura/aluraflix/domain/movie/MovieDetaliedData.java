package br.com.alura.aluraflix.domain.movie;

import java.util.Optional;


public record MovieDetaliedData(Long id, String name, String description, String url) {
    public MovieDetaliedData(Movie movie) {
        this(movie.getId(),movie.getName(),movie.getDescription(),movie.getUrl());
    }


}
