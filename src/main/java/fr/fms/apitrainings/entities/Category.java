package fr.fms.apitrainings.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Represents a category of trainings.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class Category implements Serializable {

    /**
     * The unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the category.
     */
    private String name;

    /**
     * The collection of trainings associated with the category.
     */
    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    @JsonIgnore
    private Collection<Training> trainings;

    /**
     * Constructs a new Category with the specified name and collection of trainings.
     *
     * @param name      the name of the category.
     * @param trainings the collection of trainings associated with the category.
     */
    public Category(String name, Collection<Training> trainings) {
        this.name = name;
        this.trainings = trainings;
    }
}
