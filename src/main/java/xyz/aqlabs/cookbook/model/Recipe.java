package xyz.aqlabs.cookbook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "recipe")
@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer cookBookId;
    private String name;
    @Column(length = 500)
    private String ingredients;
    @Column(length = 500)
    private String steps;
}
