package com.example.monitoringmicroservice.dtos;

public class DeviceJsonDTO {
    private Integer deviceID;
    private Integer userID;
    private Integer maxConsumption;
    private String operation;

    public DeviceJsonDTO() {
    }

    public DeviceJsonDTO(Integer deviceID, Integer userID, Integer maxConsumption, String operation) {
        this.deviceID = deviceID;
        this.userID = userID;
        this.maxConsumption = maxConsumption;
        this.operation = operation;
    }

    public Integer getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Integer deviceID) {
        this.deviceID = deviceID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getMaxConsumption() {
        return maxConsumption;
    }

    public void setMaxConsumption(Integer maxConsumption) {
        this.maxConsumption = maxConsumption;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "DeviceJsonDTO{" +
                "deviceID=" + deviceID +
                ", userID=" + userID +
                ", maxConsumption=" + maxConsumption +
                ", operation='" + operation + '\'' +
                '}';
    }
}
