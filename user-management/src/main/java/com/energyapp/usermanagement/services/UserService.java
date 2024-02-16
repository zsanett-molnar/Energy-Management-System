package com.energyapp.usermanagement.services;

import com.energyapp.usermanagement.controllers.exceptions.ResourceNotFoundException;
import com.energyapp.usermanagement.dtos.UpdateUserDTO;
import com.energyapp.usermanagement.dtos.UserDTO;
import com.energyapp.usermanagement.dtos.UserDetailsDTO;
import com.energyapp.usermanagement.dtos.builders.UserBuilder;
import com.energyapp.usermanagement.entities.AppUser;
import com.energyapp.usermanagement.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findPersons() {
        List<AppUser> personList = userRepository.findAll();
        return personList.stream()
                .map(UserBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public AppUser getAdmin() {
        return userRepository.findByRole("admin").get();
    }
    public List<UserDetailsDTO> findUsers() {
        List<AppUser> personList = userRepository.findAll();
        return personList.stream()
                .map(UserBuilder::toPersonDetailsDTO)
                .collect(Collectors.toList());
    }

    public List<AppUser> getAllUsers() {
        List<AppUser> users = userRepository.findAll();
        return  users;
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();;
    }

    public UserDetailsDTO findPersonById(Integer id) {
        Optional<AppUser> prosumerOptional = userRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(AppUser.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }

    public Boolean findPersonByUsername(String username) {
        Boolean doesExist = true;
        Optional<AppUser> prosumerOptional = userRepository.findByUsername(username);
        if(!prosumerOptional.isPresent()) {
            doesExist = false;
            LOGGER.error("User with username {} was not found in db", username);
        }

        return doesExist;
    }

    public AppUser findPersonByUsernameAndPassword(String username, String password) {
        Optional<AppUser> prosumerOptional = userRepository.findByUsernameAndPassword(username,password);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with username was not found in db", username);
            throw new ResourceNotFoundException(AppUser.class.getSimpleName() + " with username: " + username);
        }
        return prosumerOptional.get();
    }

    public Integer insert(UserDetailsDTO userDTO) {
        AppUser appUser = UserBuilder.toEntity(userDTO);
        appUser = userRepository.save(appUser);
        LOGGER.debug("Person with id {} was inserted in db", appUser.getUserID());
        return appUser.getUserID();
    }

    public AppUser updateUserById(UpdateUserDTO dto, Integer id) {
        Optional<AppUser> oldUser = userRepository.findById(id);
        oldUser.get().setUsername(dto.getUsername());
        oldUser.get().setPassword(dto.getPassword());
        oldUser.get().setRole(dto.getRole());

        return userRepository.save(oldUser.get());
    }

    public AppUser deleteUserByID(Integer id) {
        boolean isDeleted = false;
        Optional<AppUser> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            isDeleted = true;
        }
        System.out.println(isDeleted);
        return user.get();
    }


}

