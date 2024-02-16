package com.energyapp.usermanagement.dtos;

import java.util.Objects;

public class UserDTO {

    private Integer id;
    private String username;
    private String role;

    public UserDTO() {
    }

    public UserDTO(Integer id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO appUserDTO = (UserDTO) o;
        return Objects.equals(username, appUserDTO.username) &&
                Objects.equals(role, appUserDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

}
