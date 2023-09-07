package xyz.aqlabs.cookbook.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.aqlabs.cookbook.model.dto.RecipeDto;
import xyz.aqlabs.cookbook.service.RecipeService;


@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {


    @Autowired
    private RecipeService service;


    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody RecipeDto dto){
        return service.createRecipe(dto);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteRecipe(@RequestParam("recipeId")Integer recipeId){
         return service.deleteRecipe(recipeId);
    }


    @GetMapping
    public ResponseEntity<?> getByCookBookId(@RequestParam("cookBookId") Integer cookBookId){
        return service.getByCookBookId(cookBookId);
    }


}
