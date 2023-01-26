package br.com.alura.aluraflix.domain.category;

public record CategoriesListingData(Long id, String title, String color) {

    public CategoriesListingData(Category category) {
        this(category.getId(), category.getTitle(), category.getColor());
    }
}
