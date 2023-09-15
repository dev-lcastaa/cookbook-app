package xyz.aqlabs.cookbook.service;

/*
The Cookbook Service handles the business logic forwarded by the cookbook controller.
Responsible for using the Cookbook interface to communicate with the database.
*/

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
public class CookbookService {

    //Implemented logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(CookbookService.class);


    @Autowired
    private final CookbookRepository repo;


    //creates cookbooks
    public ResponseEntity<?> createCookBook(CookbookDto dto) {

        LOGGER.info("[o][o][o]---| Method INVOKED in Cookbook Service |---[o][o][o]");
        LOGGER.info("[o][o][o]---| createCookBook("+dto.hashCode()+") |---[o][o][o]");

        LOGGER.info("[X][X][X]---| Building Cookbook with name: "+dto.getName()+" |---[X][X][X]");
        var cookbook = Cookbook.builder()
                .name(dto.getName())
                .userId(dto.getUserId())
                .build();

        repo.save(cookbook);
        LOGGER.info("[X][X][X]---| COOKBOOK with hashcode: "+cookbook.hashCode()+" has been CREATED |---[X][X][X]");
        LOGGER.info("[o][o][o]---| EXITING method: createCookBook() in Cookbook Service with SUCCESS |---[o][o][o]");
        return ResponseEntity.ok().body("{\"Msg\" : \"Created Cookbook Successfully\"}");
    }


    //deletes cookbook with a cookbook id
    @Transactional
    public ResponseEntity<?> deleteCookbook(Integer id) {
        LOGGER.info("[o][o][o]---| Method INVOKED in Cookbook Service |---[o][o][o]");
        LOGGER.info("[o][o][o]---| deleteCookBook("+id+") |---[o][o][o]");


        // Checks to see if Recipe exists ,if not returns 404 NOT-FOUND
        LOGGER.info("[X][X][X]---| CHECKING if cookbook EXISTS with Id: "+id+" |---[X][X][X]");
        if(!repo.existsById(id)) {
            LOGGER.info("[X][X][X]---| Cookbook with ID: " + id + " did NOT EXIST |---[X][X][X]");
            LOGGER.info("[o][o][o]---| EXITING method: deleteCookbook("+ id +") in Cookbook Service with FAILURE |---[o][o][o]");
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("{\"Msg\" : \"Cookbook was not found\"}");
        }

        // Repository deletes Recipe entity with matching ID returns 203 NO-CONTENT
        LOGGER.info("[X][X][X]---| Cookbook with ID: "+ id +" EXISTS |---[X][X][X]");
        LOGGER.info("[X][X][X]---| DELETING Cookbook with ID: "+id+" |---[X][X][X]");
        repo.deleteCookbook(id);
        LOGGER.info("[o][o][o]---| EXITING method: deleteCookbook("+ id +") with SUCCESS |---[o][o][o]");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body("{\"Msg\" : \"Deleted Cookbook Successfully\"}");
    }


    //gets an array of cookbooks belonging to a userId
    public ResponseEntity<?> getByUserId(Integer userId){
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| Method INVOKED in Cookbook Service |---[o][o][o]");
        LOGGER.info("[o][o][o]---| getByUserId("+userId+") |---[o][o][o]");

        LOGGER.info("[X][X][X]---| FINDING Cookbook with UserId: "+ userId +" |---[X][X][X]");
        Cookbook[] cookbooks = repo.findByUserId(userId).orElseThrow();


        LOGGER.info("[X][X][X]---| CookBooks Were FOUND |---[X][X][X]");
        LOGGER.info("[o][o][o]---| EXITING method: deleteCookbook("+ userId +") with SUCCESS |---[o][o][o]");
        return ResponseEntity.ok().body(cookbooks);
    }
}
