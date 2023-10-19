package xyz.aqlabs.cookbook.security;

/*
This Class is responsible for authenticating the user
using the information from front-end login pages.
*/


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import xyz.aqlabs.cookbook.model.dto.LoginDto;
import xyz.aqlabs.cookbook.model.dto.ResponseDto;
import xyz.aqlabs.cookbook.repository.UserRepository;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class AuthenticationService{


    @Autowired
    private final UserRepository repo;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;
    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);


    // Handles the Login request from the authentication controller
    public ResponseEntity<?> login(LoginDto dto) {
        LOGGER.info("[o][o][o]---| Method INVOKED in Authentication Service|---[o][o][o]");
        LOGGER.info("[o][o][o]---| login("+dto.hashCode()+") |---[o][o][o]");

        try {
            LOGGER.info("[X][X][X]---| ATTEMPTING to Validate credentials in [dto]: "+ dto.hashCode()+" |---[X][X][X]");
            authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        }catch (AuthenticationException e){
            LOGGER.info("[X][X][X]---| Validation FAILED with [dto]: "+ dto.hashCode() +" |---[X][X][X]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body("{\"Msg\" : \"Authentication FAILED check credentials.\"}");
        }


        try {
            LOGGER.info("[X][X][X]---| CHECKING if user with Username: "+ dto.getUsername() +" exists |---[X][X][X]");
            var user = repo.findByUsername(dto.getUsername())
                    .orElseThrow();
            var token = jwtService.generateToken(user);
            LOGGER.info("[X][X][X]---| Authentication PASSED |---[X][X][X]");
            LOGGER.info("[o][o][o]---| Method EXITING in Authentication Service with SUCCESS |---[o][o][o]");
            return ResponseEntity.ok().body(new ResponseDto(user,token));
        }catch (NoSuchElementException e){
            LOGGER.info("[X][X][X]---| Authentication FAILED |---[X][X][X]");
            LOGGER.info("[o][o][o]---| Method EXITING in Authentication Service with FAILURE |---[o][o][o]");
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("{\"Msg\" : \"NO User found with credentials\"}");
        }
    }
}