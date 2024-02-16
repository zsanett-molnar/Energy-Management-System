package com.energyapp.devicemanagement.dtos.builders;

import com.energyapp.devicemanagement.dtos.UserDTO;
import com.energyapp.devicemanagement.dtos.UserDetailsDTO;
import com.energyapp.devicemanagement.entities.AppUser;

public class UserBuilder {
    private UserBuilder() {
    }

    public static UserDTO toPersonDTO(AppUser appUser) {
        return new UserDTO(appUser.getUserID(), appUser.getUsername());
    }

    public static UserDetailsDTO toPersonDetailsDTO(AppUser appUser) {
        return new UserDetailsDTO(appUser.getUserID(), appUser.getUsername());
    }

    public static AppUser toEntity(UserDetailsDTO userDetailsDTO) {
        return new AppUser(userDetailsDTO.getId(),userDetailsDTO.getUsername());
    }
}
