package com.energyapp.usermanagement.controllers;

import com.energyapp.usermanagement.dtos.LoginResponse;
import com.energyapp.usermanagement.dtos.LoginUserDTO;
import com.energyapp.usermanagement.dtos.UserDetailsDTO;
import com.energyapp.usermanagement.services.JwtService;
import com.energyapp.usermanagement.services.LoginService;
import com.energyapp.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.hateoas.Link;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final JwtService jwtService;
    private final LoginService loginService;

    private final UserService userService;

    @Autowired
    public LoginController(JwtService jwtService, LoginService loginService, UserService userService) {
        this.jwtService = jwtService;
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginRequest(@RequestBody LoginUserDTO loginCredentials) {
        UserDetailsDTO userDetailsDTO = loginService.login(loginCredentials);
        String token = jwtService.generateJwtToken(userDetailsDTO.getUsername(), userDetailsDTO.getId());
        LoginResponse loginResponse = new LoginResponse(userDetailsDTO, token);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }



}
