package xyz.aqlabs.cookbook.model.deserialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LogProbs {

    @JsonProperty("logprobs")
    @JsonIgnore
    private String data;

}
