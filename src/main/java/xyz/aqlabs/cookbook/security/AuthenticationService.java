package xyz.aqlabs.cookbook.security;

/*
This Class is responsible for authenticating the user
using the information from front-end login pages.
*/

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import xyz.aqlabs.cookbook.model.User;
import xyz.aqlabs.cookbook.model.dto.LoginDto;
import xyz.aqlabs.cookbook.model.dto.ResponseDto;
import xyz.aqlabs.cookbook.repository.UserRepository;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class AuthenticationService{


 @Autowired
 private final UserRepository repo;
 private final JwtService jwtService;
 private final AuthenticationManager authManager;

 public ResponseEntity<ResponseDto> login(final LoginDto dto) {

     try {
         authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
     }catch (AuthenticationException e){
         return new ResponseEntity<>(new ResponseDto(), HttpStatus.FORBIDDEN);
     }


     try {
         final User user = repo.findByUsername(dto.getUsername()).orElseThrow();
         final String token = jwtService.generateToken(user);
         return ResponseEntity.ok().body(new ResponseDto(user,token));

     }catch (NoSuchElementException e){
         return new ResponseEntity<>(new ResponseDto(), HttpStatus.NOT_FOUND);
     }
 }

}