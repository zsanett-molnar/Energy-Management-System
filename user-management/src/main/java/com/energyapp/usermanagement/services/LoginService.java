package com.energyapp.usermanagement.services;

import com.energyapp.usermanagement.controllers.exceptions.ResourceNotFoundException;
import com.energyapp.usermanagement.dtos.LoginUserDTO;
import com.energyapp.usermanagement.dtos.UserDetailsDTO;
import com.energyapp.usermanagement.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.energyapp.usermanagement.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.energyapp.usermanagement.dtos.builders.UserBuilder;

@Service
public class LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public AppUser findByUsernameAndPassword(String username, String password) {
        AppUser user = userRepository.findByUsername(username).get();
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public UserDetailsDTO login(LoginUserDTO loginDTO) {
        AppUser userFound = findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (userFound == null) {
            LOGGER.error("Person with username {} was not found in db", loginDTO.getUsername());
            throw new ResourceNotFoundException(AppUser.class.getSimpleName() + " with id: " + loginDTO.getUsername());
        }
        else {
            return UserBuilder.toPersonDetailsDTO(userFound);
        }
    }
}
