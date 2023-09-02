package xyz.aqlabs.cookbook.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private Integer cookBookId;
    private String name;
    private String[] ingredients;
    private String[] steps;

}
