package xyz.aqlabs.cookbook.controller;

/*
Finder Controller is the first point of contact when the user want to find a recipe
based on the ingredients and ethnicity group
*/

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.aqlabs.cookbook.model.dto.FetchRecipeDto;
import xyz.aqlabs.cookbook.service.FinderService;

@RestController
@RequestMapping("/v1/api/finder")
@RequiredArgsConstructor
public class FinderController {

    //Implemented logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(FinderController.class);

    @Autowired
    private FinderService service;


    @PostMapping("recipe")
    public ResponseEntity<?> getRecommendedRecipe(@RequestBody FetchRecipeDto dto){
        LOGGER.info("[o][o][o]---| Method INVOKED in Finder Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| getRecommendedRecipe("+dto.hashCode()+") |---[o][o][o]");
        return service.recipeFinder(dto);
    }
}
