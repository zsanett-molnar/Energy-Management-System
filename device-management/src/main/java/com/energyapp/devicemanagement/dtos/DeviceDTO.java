package com.energyapp.devicemanagement.dtos;

import com.energyapp.devicemanagement.entities.AppUser;

public class DeviceDTO {

    private Integer id;

    private String description;

    private  String address;

    private  Integer maxConsumption;

    private AppUser userID;

    public DeviceDTO(Integer id, String description, String address, Integer maxConsumption, AppUser userID) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
        this.userID = userID;
    }

    public DeviceDTO(String description, String address, Integer maxConsumption, AppUser userID) {
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
        this.userID = userID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMaxConsumption() {
        return maxConsumption;
    }

    public void setMaxConsumption(Integer maxConsumption) {
        this.maxConsumption = maxConsumption;
    }

    public AppUser getUserID() {
        return userID;
    }

    public void setUserID(AppUser userID) {
        this.userID = userID;
    }
}
