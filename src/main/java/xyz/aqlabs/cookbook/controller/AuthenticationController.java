package xyz.aqlabs.cookbook.controller;

/*
Authentication controller is responsible for logging in current users and registering new users
using POST http method.
*/

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.aqlabs.cookbook.model.dto.LoginDto;
import xyz.aqlabs.cookbook.model.dto.UserDto;
import xyz.aqlabs.cookbook.security.AuthenticationService;
import xyz.aqlabs.cookbook.service.UserService;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    //Implemented logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authService;

    private final UserService service;


    // handles the POST request made to the endpoint forwards request to the User service layer
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody UserDto dto){
        LOGGER.info("[o][o][o]---| Method INVOKED in Authentication Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| register("+dto.hashCode()+") |---[o][o][o]");
        return service.createUser(dto);
    }

    // handles the POST request made to the endpoint forwards request to the Authentication service layer
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginDto dto){
        LOGGER.info("[o][o][o]---| Method INVOKED in Authentication Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| login("+dto.hashCode()+") |---[o][o][o]");
        return authService.login(dto);
    }
}
