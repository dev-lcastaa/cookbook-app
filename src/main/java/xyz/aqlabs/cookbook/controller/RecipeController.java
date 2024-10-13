package xyz.aqlabs.cookbook.controller;

/*
The Recipe Controller is the first point of contact when a user has a query about a recipe
The controller handles HTTP verbs such as POST, DELETE, GET
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.aqlabs.cookbook.model.dto.RecipeDto;
import xyz.aqlabs.cookbook.service.RecipeService;


@RestController
@RequestMapping("/v1/api/recipe")
public class RecipeController {


    //Implemented logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);


    @Autowired
    private RecipeService service;


    // POST request endpoint to create recipes forwards request to the recipe service
    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody RecipeDto dto){
        return service.createRecipe(dto);
    }

    // DELETE request endpoint to remove recipes method forwards request to the recipe service
    @DeleteMapping
    public ResponseEntity<?> deleteRecipe(@RequestParam("recipeId")Integer recipeId){
         return service.deleteRecipe(recipeId);
    }

    // GET request endpoint to get an array of recipes forwards request to the recipe service
    @GetMapping
    public ResponseEntity<?> getRecipesByCookBookId(@RequestParam("cookBookId") Integer cookBookId){
        return service.getByCookBookId(cookBookId);
    }


}
