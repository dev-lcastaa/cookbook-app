package xyz.aqlabs.cookbook.service;

/*
The Recipe Service handles the business logic forwarded by the recipe controller.
It's Responsible for using the recipe interface to communicate with the database
for any query's regarding recipes.
*/

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.aqlabs.cookbook.model.Recipe;
import xyz.aqlabs.cookbook.model.dto.RecipeDto;
import xyz.aqlabs.cookbook.repository.RecipeRepository;


@Service
@RequiredArgsConstructor
public class RecipeService {

    // Implements Logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(CookbookService.class);


    @Autowired
    private final RecipeRepository repo;


    // creates recipes.
    public ResponseEntity<?> createRecipe(RecipeDto dto) {
        var recipe = Recipe.builder()
                .cookBookId(dto.getCookBookId())
                .name(dto.getName())
                .ingredients(dto.getIngredients())
                .steps(dto.getSteps())
                .build();

        // Repository saves the entity to the database.
        repo.save(recipe);
        return ResponseEntity.ok().body("{\"Msg\" : \"Created Recipe Successfully\"}");
    }


    // deletes recipes.
    @Transactional
    public ResponseEntity<?> deleteRecipe(Integer recipeId) {


        // Checks to see if Recipe exists ,if not returns 404 NOT-FOUND
        if(!repo.existsById(recipeId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("{\"Msg\" : \"Recipe was not found\"}");
        }
        // Repository deletes Recipe entity with matching ID returns 203 NO-CONTENT
        repo.deleteRecipe(recipeId);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body("{\"Msg\" : \"Created Recipe Successfully\"}");
    }


    // gets an array of recipes based on what cookbook the recipe belongs to.
    public ResponseEntity<?> getByCookBookId(Integer cookBookId){
        // Logs when method is invoked provides hash code for following object through log file.

        Recipe[] recipes = repo.findByCookBookId(cookBookId).orElseThrow();

        // Repository saves the entity to the database.
        return ResponseEntity.ok().body(recipes);
    }

}
