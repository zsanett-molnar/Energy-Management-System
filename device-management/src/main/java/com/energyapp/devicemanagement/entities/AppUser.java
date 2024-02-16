package com.energyapp.devicemanagement.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="app_user", schema="user_db")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "userID")
    private Integer userID;

    @Column(name = "username", nullable = false)
    private String username;


    public AppUser() {
    }

    public AppUser(String username) {
        this.username = username;

    }

    public AppUser(Integer userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
