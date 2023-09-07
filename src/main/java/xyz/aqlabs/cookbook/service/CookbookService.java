package xyz.aqlabs.cookbook.service;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(CookbookService.class);
    @Autowired
    private final CookbookRepository repo;


    public ResponseEntity<?> createCookBook(CookbookDto dto) {
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: createCookbook("+dto.hashCode()+") |---[o][o][o]");
        LOGGER.info(dto.getName());

        var cookbook = Cookbook.builder()
                .name(dto.getName())
                .userId(dto.getUserId())
                .build();

        // Repository saves the entity to the database.
        repo.save(cookbook);
        LOGGER.info("COOKBOOK with hashcode: "+cookbook.hashCode()+" has been CREATED");
        LOGGER.info("[x][x][x]---| METHOD EXITING: createCookbook("+dto.hashCode()+") |---[x][x][x]");
        return ResponseEntity.ok().body("{\"Msg\" : \"Created Cookbook Successfully\"}");
    }

    @Transactional
    public ResponseEntity<?> deleteCookbook(Integer id) {
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("------ ENTERING METHOD: deleteCookbook(" + id + ").");


        // Checks to see if Recipe exists ,if not returns 404 NOT-FOUND
        LOGGER.info("CHECKING if Cookbook exists with id: "+id+".");
        if(!repo.existsById(id)) {
            LOGGER.info("Cookbook with ID: " + id + " did NOT EXIST.");
            LOGGER.info("------ EXITING METHOD: deleteCookbook(" + id + ") with NOT-FOUND.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("{\"Msg\" : \"Cookbook was not found\"}");
        }

        // Repository deletes Recipe entity with matching ID returns 203 NO-CONTENT
        LOGGER.info("Cookbook with ID: " + id + " did EXIST.");
        LOGGER.info("DELETING Cookbook with ID: "+id+".");
        repo.deleteCookbook(id);
        LOGGER.info("------ EXITING METHOD: deleteCookbook(" + id + ") with SUCCESS.");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body("{\"Msg\" : \"Deleted Cookbook Successfully\"}");
    }


    public ResponseEntity<?> getByUserId(Integer userId){
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: getByUserId("+userId+") |---[o][o][o]");

        Cookbook[] cookbooks = repo.findByUserId(userId).orElseThrow();

        // Repository saves the entity to the database.
        LOGGER.info("[x][x][x]---| METHOD EXITING: getByUserId("+userId+") |---[x][x][x]");
        return ResponseEntity.ok().body(cookbooks);
    }

}
