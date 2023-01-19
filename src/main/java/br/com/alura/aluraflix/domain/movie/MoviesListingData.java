package br.com.alura.aluraflix.domain.movie;

public record MoviesListingData(Long id, String name, String description, String url) {

    public MoviesListingData(Movie movie) {
        this(movie.getId(), movie.getName(), movie.getDescription(), movie.getUrl());
    }

}
