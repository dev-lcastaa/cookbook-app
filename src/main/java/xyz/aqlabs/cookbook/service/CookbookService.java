package xyz.aqlabs.cookbook.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xyz.aqlabs.cookbook.model.Cookbook;
import xyz.aqlabs.cookbook.model.Type;
import xyz.aqlabs.cookbook.model.User;
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
                .type(dto.getType())
                .userId(dto.getUserId())
                .build();

        // Repository saves the entity to the database.
        repo.save(cookbook);
        LOGGER.info("COOKBOOK with hashcode: "+cookbook.hashCode()+" has been CREATED");
        LOGGER.info("[x][x][x]---| METHOD EXITING: createCookbook("+dto.hashCode()+") |---[x][x][x]");
        return ResponseEntity.ok().body("{\"Msg\" : \"Created Cookbook Successfully\"}");
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
