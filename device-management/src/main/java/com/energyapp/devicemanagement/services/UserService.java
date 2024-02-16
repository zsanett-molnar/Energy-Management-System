package com.energyapp.devicemanagement.services;

import com.energyapp.devicemanagement.controllers.exceptions.ResourceNotFoundException;
import com.energyapp.devicemanagement.dtos.UpdateUserDTO;
import com.energyapp.devicemanagement.dtos.UserDTO;
import com.energyapp.devicemanagement.dtos.UserDetailsDTO;
import com.energyapp.devicemanagement.dtos.builders.UserBuilder;
import com.energyapp.devicemanagement.entities.AppUser;
import com.energyapp.devicemanagement.repositories.UserRepository;
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

    public UserDetailsDTO findPersonById(Integer id) {
        Optional<AppUser> prosumerOptional = userRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(AppUser.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }

    public Integer insert(UserDetailsDTO userDTO) {
        AppUser appUser = UserBuilder.toEntity(userDTO);
        System.out.println("app user id " + appUser.getUserID());
        userRepository.save(appUser);
        LOGGER.debug("Person with id {} was inserted in db", appUser.getUserID());
        return appUser.getUserID();
    }

    public AppUser updateUserById(UpdateUserDTO dto, Integer id) {
        Optional<AppUser> oldUser = userRepository.findById(id);
        System.out.println("A intrat aici in update");
        oldUser.get().setUsername(dto.getUsername());
        return userRepository.save(oldUser.get());
    }

    public boolean deleteUserByID(Integer id) {
        boolean isDeleted = false;
        Optional<AppUser> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            isDeleted = true;
        }
        System.out.println(isDeleted);
        return isDeleted;
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();;
    }
}
