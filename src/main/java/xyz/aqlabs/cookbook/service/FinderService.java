package xyz.aqlabs.cookbook.service;

/*
The finder service handles the business logic of any request made to the controller
Responsible for communicating to external OpenAi API
*/

import lombok.SneakyThrows;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.aqlabs.cookbook.model.deserialization.JsonResponse;
import xyz.aqlabs.cookbook.model.dto.FetchRecipeDto;

import java.util.*;



@Service
public class FinderService {

    //Implemented logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(FinderService.class);

    final RestTemplate RESTTEMPLATE = new RestTemplate();


    // Endpoint to openAI api
    String url = "https://api.openai.com/v1/chat/completions";


    // method responsible for communicating and returning a recipe
    @SneakyThrows
    public ResponseEntity<?> recipeFinder(FetchRecipeDto dto) {

        LOGGER.info("[o][o][o]---| Method INVOKED in Finder Service |---[o][o][o]");
        LOGGER.info("[o][o][o]---| recipeFinder("+dto.hashCode()+") |---[o][o][o]");

        //creates an Object Mapper
        var objectMapper = new ObjectMapper();

        // Model configuration to POST openAi
        LOGGER.info("[X][X][X]---| CONFIGURING Model |---[X][X][X]");
        var modelConfig = "{\"model\": \"gpt-4\", \"messages\": [{\"role\": \"system\", \"content\": \"You are a recipe book. You will receive ingredients and an ethnicity in the following JSON format:\\n{\\n  \\\"ingredients\\\": \\\"ingredient1, ingredient2\\\",\\n  \\\"ethnicity\\\": \\\"ethnicity\\\"\\n}\\nYou have to return a detailed recipe from the specified ethnicity using the provided provided ingredients separated by commas.\\nIf there are no ingredients or the ingredients are not valid such as non-food items or the ethnicity is not specified or the ethnicity is invalid return an error message response with the reason why you deemed it invalid in the following JSON \\n{\\n  \\\"errorMsg\\\": \\\"The reason why\\\"\\n}\\n .\\n If the input does not match the specified format, return an error message response like I specified. Do not give incomplete responses, responses are to be in JSON format, All special characters within the JSON string are to be properly escaped\"}, {\"role\": \"user\", \"content\": \"{\\\"ingredients\\\":\\\""+dto.getIngredient()+"\\\",\\\"ethnicity\\\":\\\""+dto.getEthnicity()+"\\\"}\" }, {\"role\": \"assistant\", \"content\": \"{\\\"name\\\": \\\"Your Recipe Name\\\", \\\"ingredients\\\": \\\"Your Recipe Ingredients\\\", \\\"steps\\\": \\\"Your Recipe Steps\\\"}\"}], \"temperature\": 0.5, \"max_tokens\": 400}\n";

        // Create an HTTP entity with the required headers (including the API key) and the model configuration.
        LOGGER.info("[X][X][X]---| CONFIGURING Request Headers |---[X][X][X]");
        HttpEntity<String> requestEntity = createAuthEntity(getApiKey(), modelConfig);

        // Send the POST request to the OpenAI API.
        LOGGER.info("[X][X][X]---| MAKING request to API |---[X][X][X]");
        ResponseEntity<?> response = RESTTEMPLATE.exchange(url, HttpMethod.POST, requestEntity, String.class);

        //Create a JsonResponse object from the response body
        LOGGER.info("[X][X][X]---| DESERIALIZING response |---[X][X][X]");
        var jsonResponse = objectMapper.readValue(Objects.requireNonNull(response.getBody()).toString(), JsonResponse.class);

        // Extract the "content" field from the first choice and return it's in a more abstracted response
        LOGGER.info("[X][X][X]---| EXTRACTING recipe |---[X][X][X]");
        LOGGER.info("[o][o][o]---| EXITING method: recipeFinder("+dto.hashCode()+") in Finder Service with SUCCESS |---[o][o][o]");
        System.out.println(jsonResponse.getChoices().get(0).getMessage().getContent());
        return ResponseEntity.ok().body(jsonResponse.getChoices().get(0).getMessage().getContent());
    }


    // sets the HttpEntity with necessary headers
    private HttpEntity<String> createAuthEntity(String apiKey, String modelConfig) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        return new HttpEntity<>(modelConfig, headers);
    }


    // returns the api key
    private String getApiKey() {
        return System.getProperty("KEY");
    }

}
