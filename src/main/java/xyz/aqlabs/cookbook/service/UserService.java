package xyz.aqlabs.cookbook.service;


/*
This Service Class is responsible for handling the business
logic of any employee query made from the controller. It is
also responsible for validating its own data using predefined
lambdas found in the tools package.
 */


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    @PersistenceContext
    private EntityManager entityManager;


    public ResponseEntity<?> createUser(UserDto dto) {
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: createUser("+dto.hashCode()+") |---[o][o][o]");

        var user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        // Repository saves the entity to the database.
        repo.save(user);
        LOGGER.info("USER with hashcode: "+user.hashCode()+" has been CREATED");
        var token = jwtService.generateToken(user);
        LOGGER.info("[x][x][x]---| METHOD EXITING: createUser("+dto.hashCode()+") |---[x][x][x]");
        return ResponseEntity.ok().body("Login Successful");
    }


    @Transactional
    public ResponseEntity<?> updateUser(User user){
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: updateUser("+user.hashCode()+") |---[o][o][o]");

        // If user is valid but user does not exist via its ID , return 404 not found.
        LOGGER.info("CHECKING if USER with [userID]: "+ user.getUserId()+ " exists.");
        if(!repo.existsById(user.getUserId())) {
            LOGGER.info("USER with [userID]: " + user.getUserId() + " DID NOT exist.");
            LOGGER.info("[x][x][x]---| METHOD EXITING: updateUser("+user.hashCode()+")  with 404 NOT FOUND |---[x][x][x]");
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("USER with [userId]: " + user.getUserId() + " was NOT FOUND.");
        }

        // if the user is valid and user exists via ID, update the user, and return 200 ok.
        LOGGER.info("USER with [userId]: " + user.getUserId() + " DOES EXIST.");
        LOGGER.info("Applying changes to USER with [userId]: " + user.getUserId() +".");

        repo.updateUser(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );

        LOGGER.info("USER with [userID]: " + user.getUserId() + " was CHANGED.");
        LOGGER.info("[x][x][x]---| METHOD EXITING: updateUser("+user.hashCode()+")  with 200 OK |---[x][x][x]");
        return ResponseEntity.ok().body("User Updated");
    }


    @Transactional
    public ResponseEntity<?> deleteUser(Integer userId) {
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: deleteUser("+userId+") |---[o][o][o]");


        // Checks to see if User exists in the returns 404 NOT-FOUND
        LOGGER.info("CHECKING if USER exists with [userId]: "+userId+".");
        if(!repo.existsById(userId)) {
            LOGGER.info("USER with [userId]: " + userId + " did NOT EXIST.");
            LOGGER.info("[x][x][x]---| METHOD EXITING: deleteUser("+userId+") with a 404 NOT FOUND |---[x][x][x]");
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("No USER with [userId]: " + userId+".");
        }

        // Repository deletes User entity with matching ID returns 203 NO-CONTENT
        LOGGER.info("USER with [userId]: " + userId + " did EXIST.");
        LOGGER.info("DELETING User with [userId]: "+userId+".");
        repo.deleteUser(userId);
        LOGGER.info("[x][x][x]---| METHOD EXITING: deleteUser("+userId+") with SUCCESS |---[x][x][x]");
        return  ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body("DELETED User with [userId]:"+ userId);
    }


    public ResponseEntity<?> getUserByUserId(Integer userId){
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: getUserByUserId("+userId+") |---[o][o][o]");


        LOGGER.info("CHECKING if USER with [userId]: "+userId+" EXISTS...");
        //makes sure the response is not empty
        LOGGER.info("Making sure RETRIEVAL RESPONSE is not empty");
        if(repo.findById(userId).isPresent()){

            LOGGER.info("USER with ID: "+userId+" EXISTS...");
            var response = repo.findById(userId);
            var user = response.orElseThrow();
            LOGGER.info("[x][x][x]---| METHOD EXITING: deleteUser("+userId+") with a 200 OK |---[x][x][x]");
            return ResponseEntity.ok(user);
        }
        LOGGER.info("USER with [userId]: "+userId+" did NOT EXIST.");
        LOGGER.info("[x][x][x]---| METHOD EXITING: deleteUser("+userId+") with a 404 NOT FOUND |---[x][x][x]");
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("USER with [userId]: "+userId+" WAS NOT FOUND");
    }


    public ResponseEntity<?> getUserByUsername(String username){
        // Logs when method is invoked provides hash code for following object through log file.
        LOGGER.info("[o][o][o]---| METHOD EXECUTING: getUserByUsername("+username+") |---[o][o][o]");


        LOGGER.info("CHECKING if USER with [username]: "+username+" EXISTS...");
        //makes sure the response is not empty
        LOGGER.info("Making sure RETRIEVAL RESPONSE is not empty");
        if(repo.findByUsername(username).isPresent()){

            LOGGER.info("USER with [username]: "+username+" EXISTS...");
            var response = repo.findByUsername(username);
            var user = response.orElseThrow();
            LOGGER.info("[x][x][x]---| METHOD EXITING: getUserByUsername("+ username +") with a 200 OK |---[x][x][x]");
            return ResponseEntity.ok(user);
        }
        LOGGER.info("USER with [username]: "+username+" did NOT EXIST.");
        LOGGER.info("[x][x][x]---| METHOD EXITING: getUserByUsername("+username+") with a 404 NOT FOUND |---[x][x][x]");
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("USER with [username]: "+username+" WAS NOT FOUND");
    }


}
