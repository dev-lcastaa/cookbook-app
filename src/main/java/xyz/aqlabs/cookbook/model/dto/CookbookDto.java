package xyz.aqlabs.cookbook.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.aqlabs.cookbook.model.Type;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CookbookDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("type")
    private Type type;
}
