package xyz.aqlabs.cookbook.controller;

/*
CookBook controller is the first point of contact for any query relating to a users cookbook
it can handle DELETE, GET and POST methods
*/

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.aqlabs.cookbook.model.dto.CookbookDto;
import xyz.aqlabs.cookbook.service.CookbookService;


@RestController
@RequestMapping("/v1/api/cookbook")
@RequiredArgsConstructor
public class CookBookController {

    //Implemented logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(CookBookController.class);

    @Autowired
    private CookbookService service;


    // POST request endpoint to create cookbooks, forwards request to the cookbook service
    @PostMapping
    public ResponseEntity<?> addCookBook(@RequestBody CookbookDto dto){
        LOGGER.info("[o][o][o]---| Method INVOKED in CookBook Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| addCookBook("+dto.hashCode()+") |---[o][o][o]");
        return service.createCookBook(dto);
    }


    // DELETE request endpoint to delete cookbooks, forwards request to the cookbook service
    @DeleteMapping
    public ResponseEntity<?> deleteCookbook(@RequestParam("id")Integer id){
        LOGGER.info("[o][o][o]---| Method INVOKED in CookBook Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| deleteCookBook("+id+") |---[o][o][o]");
        return service.deleteCookbook(id);
    }


    // GET request endpoint to get an array of cookbooks using userID, forwards request to the cookbook service
    @GetMapping
    public ResponseEntity<?> getCookbooksByUserId(@RequestParam("userId") Integer userId){
        LOGGER.info("[o][o][o]---| Method INVOKED in CookBook Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| getCookBooksByUserId("+userId+") |---[o][o][o]");
        return service.getByUserId(userId);
    }

}
