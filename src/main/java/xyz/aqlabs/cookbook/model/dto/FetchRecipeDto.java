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
public class FetchRecipeDto {

    @JsonProperty("ingredients")
    private String ingredient;

    @JsonProperty("ethnicity")
    private String ethnicity;

}
