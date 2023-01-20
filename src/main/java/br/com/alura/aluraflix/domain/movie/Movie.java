package br.com.alura.aluraflix.domain.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity class to store the Movie object
 */


@Table(name = "movies")
@Entity(name = "Movie")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String url;


    public Movie(DataAddMovie data) {
        this.name = data.name();
        this.description = data.description();
        this.url = data.url();
    }
}
