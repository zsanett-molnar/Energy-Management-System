package com.energyapp.usermanagement.dtos.builders;

import com.energyapp.usermanagement.dtos.UserDTO;
import com.energyapp.usermanagement.dtos.UserDetailsDTO;
import com.energyapp.usermanagement.entities.AppUser;

public class UserBuilder {

    private UserBuilder() {
    }

    public static UserDTO toPersonDTO(AppUser appUser) {
        return new UserDTO(appUser.getUserID(), appUser.getUsername(), appUser.getPassword());
    }

    public static UserDetailsDTO toPersonDetailsDTO(AppUser appUser) {
        return new UserDetailsDTO(appUser.getUserID(), appUser.getUsername(), appUser.getPassword(), appUser.getRole());
    }

    public static AppUser toEntity(UserDetailsDTO userDetailsDTO) {
        return new AppUser(userDetailsDTO.getUsername(),
                userDetailsDTO.getPassword(),
                userDetailsDTO.getRole());
    }
}
