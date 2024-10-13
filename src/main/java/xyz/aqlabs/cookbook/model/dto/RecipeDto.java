package xyz.aqlabs.cookbook.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private Integer cookBookId;
    private String name;
    private List<String> ingredients;
    private List<String> steps;

}
