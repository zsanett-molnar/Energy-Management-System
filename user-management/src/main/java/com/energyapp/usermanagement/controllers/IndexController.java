package com.energyapp.usermanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IndexController {

//    @GetMapping(value = "/")
//    public ResponseEntity<String> getStatus() {
//        return new ResponseEntity<>("Energy APP Service is running...", HttpStatus.OK);
//    }
}