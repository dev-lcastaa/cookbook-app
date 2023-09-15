package xyz.aqlabs.cookbook.model.deserialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Choice {

    @JsonProperty("index")
    private int index;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("finish_reason")
    private String finishReason;


}
