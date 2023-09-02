package xyz.aqlabs.cookbook.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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



    public ResponseEntity<?> getByCookBookId(Integer cookBookId){
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: getByCookBookId("+cookBookId+") |---[o][o][o]");

        Recipe[] recipes = repo.findByCookBookId(cookBookId).orElseThrow();

        // Repository saves the entity to the database.
        LOGGER.info("[x][x][x]---| METHOD EXITING: getByCookBookId("+cookBookId+") |---[x][x][x]");
        return ResponseEntity.ok().body(recipes);
    }


}
