package br.com.alura.aluraflix.domain.movie;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DataAddMovie(
        @NotNull @Valid
        String name,

        @NotNull @Valid
        String description,

        @NotNull @Valid
        String url) { }
