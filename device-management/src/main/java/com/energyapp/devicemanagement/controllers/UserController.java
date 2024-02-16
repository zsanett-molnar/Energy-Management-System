package com.energyapp.devicemanagement.controllers;

import com.energyapp.devicemanagement.dtos.UpdateUserDTO;
import com.energyapp.devicemanagement.dtos.UserDTO;
import com.energyapp.devicemanagement.dtos.UserDetailsDTO;
import com.energyapp.devicemanagement.entities.AppUser;
import com.energyapp.devicemanagement.entities.Device;
import com.energyapp.devicemanagement.services.DeviceService;
import com.energyapp.devicemanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/device_db/user")
public class UserController {

    private final UserService userService;

    private final DeviceService deviceService;

    @Autowired
    public UserController(UserService userService, DeviceService deviceService) {
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getPersons() {
        List<UserDTO> dtos = userService.findPersons();
//        for (UserDTO dto : dtos) {
//            Link personLink = linkTo(methodOn(UserController.class)
//                    .getUser(dto.getId())).withRel("personDetails");
//            dto.add(personLink);
//        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/insert/{id}")
    public ResponseEntity<Integer> insertProsumer(@PathVariable("id") Integer userId, @RequestBody UserDetailsDTO userDTO) {
        System.out.println("user id transmis:"+ userId);
        userDTO.setId(userId);
        Integer personID = userService.insert(userDTO);
        return new ResponseEntity<>(personID, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable("id") Integer userId) {
        UserDetailsDTO dto = userService.findPersonById(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //TODO: UPDATE, DELETE per resource

    @PutMapping(value = "/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable("id") Integer userID,@RequestBody UpdateUserDTO dto) {
        AppUser newUser = userService.updateUserById(dto, userID);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userID) {
        List<Device> usersDevices = deviceService.findDeviceByUser(userID);
        if(usersDevices.size()>0) {
            for(Device d : usersDevices) {
                d.setUserID(null);
            }
        }

        boolean isDeleted = userService.deleteUserByID(userID);
        if(isDeleted==true) {
            return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteAll")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

}