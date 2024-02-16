package com.energyapp.devicemanagement.dtos;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.Objects;

public class UserDetailsDTO {

    private Integer id;
    @NotNull
    private String username;


    public UserDetailsDTO() {
    }

    public UserDetailsDTO(String username) {
        this.username = username;
    }

    public UserDetailsDTO(Integer id, String username) {
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
        UserDetailsDTO that = (UserDetailsDTO) o;
        return Objects.equals(username, that.username);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

}
