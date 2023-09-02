package xyz.aqlabs.cookbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String[] ingredients;
    private String[] steps;
}
