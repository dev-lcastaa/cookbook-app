package xyz.aqlabs.cookbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.aqlabs.cookbook.model.dto.CookbookDto;
import xyz.aqlabs.cookbook.service.CookbookService;

@RestController
@RequestMapping("/api/v1/cookbook")
@RequiredArgsConstructor
public class CookBookController {

    @Autowired
    private CookbookService service;

    @PostMapping
    public ResponseEntity<?> addCookBook(@RequestBody CookbookDto dto){
        return service.createCookBook(dto);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCookbook(@RequestParam("id")Integer id){
        return service.deleteCookbook(id);
    }


    @GetMapping
    public ResponseEntity<?> getByUserId(@RequestParam("userId") Integer userId){
        return service.getByUserId(userId);
    }

}
