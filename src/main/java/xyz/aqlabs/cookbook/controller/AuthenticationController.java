package xyz.aqlabs.cookbook.controller;

import lombok.RequiredArgsConstructor;
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


    private final AuthenticationService authService;
    private final UserService service;


    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody UserDto dto){return service.createUser(dto);}


    @PostMapping("/login")
    ResponseEntity<?> logIn(@RequestBody LoginDto dto){return authService.logIn(dto);}


}
