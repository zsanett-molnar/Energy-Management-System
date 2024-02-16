package com.energyapp.devicemanagement.dtos;

public class UpdateDeviceDTO {

    private String description;

    private  String address;

    private  Integer maxConsumption;
    private Integer userID;

    public UpdateDeviceDTO() {

    }
    public UpdateDeviceDTO(String description, String address, Integer maxConsumption, Integer userID) {
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
        this.userID = userID;
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
