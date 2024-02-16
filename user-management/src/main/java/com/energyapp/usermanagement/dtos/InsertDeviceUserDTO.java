package com.energyapp.usermanagement.dtos;

public class InsertDeviceUserDTO  {

    private Integer userID;
    private String username;

    public InsertDeviceUserDTO(Integer userID, String username) {
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