package xyz.aqlabs.cookbook.service;

/*
The User Service handles the business logic forwarded by the User controller.
It's Responsible for using the User interface to communicate with the database
for any query's regarding users.
*/

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.aqlabs.cookbook.model.User;
import xyz.aqlabs.cookbook.model.dto.UserDto;
import xyz.aqlabs.cookbook.repository.UserRepository;
import xyz.aqlabs.cookbook.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;

@Service
@RequiredArgsConstructor
public class UserService  {

    //Implemented logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final UserRepository repo;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    @PersistenceContext
    private EntityManager entityManager;


    // creates a new user this is called from the authentication controller
    public ResponseEntity<?> createUser(UserDto dto) {
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| Method INVOKED in User Service |---[o][o][o]");
        LOGGER.info("[o][o][o]---| createUser("+dto.hashCode()+") |---[o][o][o]");

        LOGGER.info("[X][X][X]---| Building User with username: "+dto.getUsername()+" |---[X][X][X]");
        var user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        // Repository saves the entity to the database.
        repo.save(user);
        LOGGER.info("[X][X][X]---| COOKBOOK with hashcode: "+user.hashCode()+" has been CREATED |---[X][X][X]");
        LOGGER.info("[o][o][o]---| EXITING method: createUser("+dto.hashCode()+") in User Service with SUCCESS |---[o][o][o]");
        return ResponseEntity.ok().body("{\"Msg\" : \"Created User Successfully\"}");
    }

/*
    // updates a user th
    @Transactional
    public ResponseEntity<?> updateUser(User user){
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| Method INVOKED in User Service |---[o][o][o]");
        LOGGER.info("[o][o][o]---| updateUser("+user.hashCode()+") |---[o][o][o]");


        // Checks if the user exists via ID if it does not exist returns 404 NOT FOUND.
        LOGGER.info("[X][X][X]---| CHECKING if USER with UserID: "+ user.getUserId()+ " exists |---[X][X][X]");
        if(!repo.existsById(user.getUserId())) {
            LOGGER.info("[X][X][X]---| USER with UserID: " + user.getUserId() + " DID NOT exist  |---[X][X][X]");
            LOGGER.info("[o][o][o]---| EXITING method: updateUser("+user.hashCode()+") in User Service with FAILURE |---[o][o][o]");
            return ResponseEntity.ok().body("{\"Msg\" : \"USER with UserID:" + user.getUserId() +" was NOT FOUND.\"}");
        }

        // if the user is valid and user exists via ID, update the user, and return 200 ok.
        LOGGER.info("[X][X][X]---| USER with UserID: "+ user.getUserId() +" DID exist |---[X][X][X]");

        LOGGER.info("[X][X][X]---| Applying changes to USER with UserID: "+ user.getUserId() +" |---[X][X][X]");
        repo.updateUser(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );

        LOGGER.info("[X][X][X]---| Changes to USER with UserID: "+ user.getUserId() +" SUCCESSFULLY applied |---[X][X][X]");
        LOGGER.info("[o][o][o]---| EXITING method: updateUser("+user.hashCode()+") in User Service with SUCCESS |---[o][o][o]");
        return ResponseEntity.ok().body("{\"Msg\" : \"Updated User Successfully\"}");
    }

    // deletes a user
    @Transactional
    public ResponseEntity<?> deleteUser(Integer userId) {
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| Method INVOKED in User Service |---[o][o][o]");
        LOGGER.info("[o][o][o]---| deleteUser("+userId+") |---[o][o][o]");

        // Checks to see if User exists in the returns 404 NOT-FOUND
        LOGGER.info("[X][X][X]---| CHECKING if User EXISTS with Id: "+userId+" |---[X][X][X]");
        if(!repo.existsById(userId)) {
            LOGGER.info("[X][X][X]---| User with ID: " + userId + " did NOT EXIST |---[X][X][X]");
            LOGGER.info("[o][o][o]---| EXITING method: deleteUser("+ userId +") in User Service with FAILURE |---[o][o][o]");
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("{\"Msg\" : \"User was not found\"}");
        }

        // Repository deletes User entity with matching ID returns 203 NO-CONTENT
        LOGGER.info("[X][X][X]---| User with ID: "+ userId +" EXISTS |---[X][X][X]");
        LOGGER.info("[X][X][X]---| DELETING User with ID: "+userId+" |---[X][X][X]");
        repo.deleteUser(userId);
        LOGGER.info("[o][o][o]---| EXITING method: deleteUser("+ userId +") with SUCCESS |---[o][o][o]");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body("{\"Msg\" : \"Deleted User Successfully\"}");
    }

*/
}
