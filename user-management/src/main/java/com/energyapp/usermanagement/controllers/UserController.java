package com.energyapp.usermanagement.controllers;

import com.energyapp.usermanagement.dtos.InsertDeviceUserDTO;
import com.energyapp.usermanagement.dtos.UpdateUserDTO;
import com.energyapp.usermanagement.dtos.UserDTO;
import com.energyapp.usermanagement.dtos.UserDetailsDTO;
import com.energyapp.usermanagement.entities.AppUser;
import com.energyapp.usermanagement.services.JwtService;
import com.energyapp.usermanagement.services.LoginService;
import com.energyapp.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/user")
public class UserController {

    private final JwtService jwtService;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @GetMapping()
    public ResponseEntity<List<UserDTO>> getPersons() {
        List<UserDTO> dtos = userService.findPersons();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/getAdmin")
    public ResponseEntity<AppUser> getAdminId() {
        AppUser admin = userService.getAdmin();
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<AppUser>> getAllUsers(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the header is not present or does not start with "Bearer "
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);

        if (!jwtService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the token is invalid
        }

        System.out.println("A intrat in controller");
        List<AppUser> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Integer> insertProsumer(@RequestBody UserDetailsDTO userDTO) {
        Integer personID = userService.insert(userDTO);
        String mesaj = "Inserat";

        RestTemplate restTemplate = new RestTemplate();
        String apiURL = "http://172.18.0.6:8081/device_db/user/insert/" + personID;
        //String apiURL = "http://localhost:8081/device_db/user/insert/" + personID;
        System.out.println(apiURL);
        InsertDeviceUserDTO dto = new InsertDeviceUserDTO(personID, userDTO.getUsername());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<InsertDeviceUserDTO> requestEntity = new HttpEntity<>(dto, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity, Void.class);



        return new ResponseEntity<>(personID, HttpStatus.OK);
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<AppUser> getUser(@RequestHeader(value = "Authorization", required = false) String authorizationHeader , @PathVariable("username") String username, @PathVariable("password") String password) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the header is not present or does not start with "Bearer "
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);

        if (!jwtService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the token is invalid
        }

        AppUser user = userService.findPersonByUsernameAndPassword(username, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable("id") Integer userId) {
        UserDetailsDTO dto = userService.findPersonById(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/register/{username}")
    public ResponseEntity<Boolean> getUser(@PathVariable("username") String username) {
        Boolean isPresent = userService.findPersonByUsername(username);
        return new ResponseEntity<>(isPresent, HttpStatus.OK);
    }

    //TODO: UPDATE, DELETE per resource

    @PutMapping(value = "/{id}")
    public ResponseEntity<AppUser> updateUser(@RequestHeader(value = "Authorization", required = false) String authorizationHeader , @PathVariable("id") Integer userID, @RequestBody UpdateUserDTO dto) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the header is not present or does not start with "Bearer "
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);

        if (!jwtService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the token is invalid
        }

        AppUser newUser = userService.updateUserById(dto, userID);

        //String apiURL = "http://localhost:8081/device_db/user/" + userID;
        String apiURL = "http://172.18.0.6:8081/device_db/user/" + userID;
        RestTemplate rest = new RestTemplate();
        rest.put(apiURL,dto);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AppUser> deleteUser(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @PathVariable("id") Integer userID) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the header is not present or does not start with "Bearer "
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);

        if (!jwtService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the token is invalid
        }

        System.out.println("A INTRAT IN DELETE");
        AppUser user = userService.deleteUserByID(userID);

        //String apiURL = "http://localhost:8081/device_db/user/" + userID;
        String apiURL = "http://172.18.0.6:8081/device_db/user/" + userID;
        System.out.println(apiURL);
        RestTemplate rest = new RestTemplate();
        rest.delete(apiURL);

        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteAll")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("/test")
    public String testare() {
        return "HELLO WORLD";
    }

}
