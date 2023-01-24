package br.com.alura.aluraflix.domain.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Movie entity class, has the attributes and methods of the object
 * @param  id- Unic, Required, not null
 * @param name - Required, not null
 * @param description - Required, not null
 * @param url - Required, not null
 * @Author Rodrigo Marcel Honorio
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
    @NotEmpty(message="The name cannot be null")
    @NotNull(message="The name cannot be null")
    private String name;
    @NotEmpty(message="The description cannot be null")
    @NotNull(message="The description cannot be null")
    private String description;
    @NotEmpty(message="The url cannot be null")
    @NotNull(message="The url cannot be null")
    private String url;


    public Movie(DataAddMovie data) {
        this.name = data.name();
        this.description = data.description();
        this.url = data.url();
    }

    public void update(MovieDetaliedData data) {
        if(data.name() != null)
            this.name = data.name();
        if(data.description() != null)
            this.description = data.description();
        if(data.url() != null)
            this.url = data.url();
    }
}
