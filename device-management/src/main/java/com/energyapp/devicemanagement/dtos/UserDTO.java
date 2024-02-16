package com.energyapp.devicemanagement.dtos;

import java.util.Objects;

public class UserDTO {

    private Integer id;
    private String username;


    public UserDTO() {
    }

    public UserDTO(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO appUserDTO = (UserDTO) o;
        return Objects.equals(username, appUserDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, id);
    }
}
