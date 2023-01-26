package br.com.alura.aluraflix.domain.category;

public record CategoryDetaliedData(Long id, String title, String color) {
    public CategoryDetaliedData(Category category) {
        this(category.getId(), category.getTitle(), category.getTitle());
    }
}
