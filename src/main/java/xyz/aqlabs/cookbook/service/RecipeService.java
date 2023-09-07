package xyz.aqlabs.cookbook.service;


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


    private static final Logger LOGGER = LoggerFactory.getLogger(CookbookService.class);
    @Autowired
    private final RecipeRepository repo;


    public ResponseEntity<?> createRecipe(RecipeDto dto) {
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: createRecipe("+dto.hashCode()+") |---[o][o][o]");
        LOGGER.info(dto.getName());

        var recipe = Recipe.builder()
                .cookBookId(dto.getCookBookId())
                .name(dto.getName())
                .ingredients(dto.getIngredients())
                .steps(dto.getSteps())
                .build();

        // Repository saves the entity to the database.
        repo.save(recipe);
        LOGGER.info("RECIPE with hashcode: "+recipe.hashCode()+" has been CREATED");
        LOGGER.info("[x][x][x]---| METHOD EXITING: createRecipe("+dto.hashCode()+") |---[x][x][x]");
        return ResponseEntity.ok().body("{\"Msg\" : \"Created Recipe Successfully\"}");
    }


    @Transactional
    public ResponseEntity<?> deleteRecipe(Integer recipeId) {
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("------ ENTERING METHOD: deleteRecipe(" + recipeId + ").");


        // Checks to see if Recipe exists ,if not returns 404 NOT-FOUND
        LOGGER.info("CHECKING if recipe exists with recipe id: "+recipeId+".");
        if(!repo.existsById(recipeId)) {
            LOGGER.info("Recipe with ID: " + recipeId + " did NOT EXIST.");
            LOGGER.info("------ EXITING METHOD: deleteRecipe(" + recipeId + ") with NOT-FOUND.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("{\"Msg\" : \"Recipe was not found\"}");
        }

        // Repository deletes Recipe entity with matching ID returns 203 NO-CONTENT
        LOGGER.info("Recipe with ID: " + recipeId + " did EXIST.");
        LOGGER.info("DELETING Recipe with ID: "+recipeId+".");
        repo.deleteRecipe(recipeId);
        LOGGER.info("------ EXITING METHOD: deleteRecipe(" + recipeId + ") with SUCCESS.");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body("{\"Msg\" : \"Created Recipe Successfully\"}");
    }


    public ResponseEntity<?> getByCookBookId(Integer cookBookId){
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: getByCookBookId("+cookBookId+") |---[o][o][o]");

        Recipe[] recipes = repo.findByCookBookId(cookBookId).orElseThrow();

        // Repository saves the entity to the database.
        LOGGER.info("[x][x][x]---| METHOD EXITING: getByCookBookId("+cookBookId+") |---[x][x][x]");
        return ResponseEntity.ok().body(recipes);
    }


}
