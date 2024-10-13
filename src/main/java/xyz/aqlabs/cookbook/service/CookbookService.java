package xyz.aqlabs.cookbook.service;

/*
The Cookbook Service handles the business logic forwarded by the cookbook controller.
Responsible for using the Cookbook interface to communicate with the database.
*/

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.aqlabs.cookbook.model.Cookbook;
import xyz.aqlabs.cookbook.model.dto.CookbookDto;
import xyz.aqlabs.cookbook.repository.CookbookRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class CookbookService {

    //Implemented logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(CookbookService.class);

    @Autowired
    private final CookbookRepository repo;

    //creates cookbooks
    public ResponseEntity<?> createCookBook(CookbookDto dto) {
        var cookbook = Cookbook.builder()
                .name(dto.getName())
                .userId(dto.getUserId())
                .build();

        repo.save(cookbook);
        return ResponseEntity.ok().body("{\"Msg\" : \"Created Cookbook Successfully\"}");
    }


    //deletes cookbook with a cookbook id
    @Transactional
    public ResponseEntity<?> deleteCookbook(Integer id) {
        if(!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("{\"Msg\" : \"Cookbook was not found\"}");
        }

        // Repository deletes Recipe entity with matching ID returns 203 NO-CONTENT
        repo.deleteCookbook(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body("{\"Msg\" : \"Deleted Cookbook Successfully\"}");
    }


    //gets an array of cookbooks belonging to a userId
    public ResponseEntity<?> getByUserId(Integer userId){
        Cookbook[] cookbooks = repo.findByUserId(userId).orElseThrow();
        return ResponseEntity.ok().body(cookbooks);
    }
}
