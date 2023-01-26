package br.com.alura.aluraflix.domain.category;

import jakarta.validation.Valid;

public record DataAddCategory(
        @Valid
        String title,
        @Valid
        String color
) {
}
