package br.com.alura.aluraflix.domain.movie;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DataAddMovie(
        @Valid
        String name,

        @Valid
        String description,

        @Valid
        String url) { }
