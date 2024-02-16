package com.energyapp.devicemanagement.dtos;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.Objects;

public class DeviceDetailsDTO {

    private Integer id;

    @NotNull
    private String description;

    @NotNull
    private String address;

    @NotNull
    private Integer maxConsumption;

    private Integer userID;

    public DeviceDetailsDTO() {

    }

    public DeviceDetailsDTO(String description, String address, Integer maxConsumption, Integer userID) {
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
        this.userID = userID;
    }

    public DeviceDetailsDTO(Integer id, String description, String address, Integer maxConsumption, Integer userID) {
        this.id = id;
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDetailsDTO that = (DeviceDetailsDTO) o;
        return Objects.equals(description, that.description) &
                Objects.equals(address, that.address) &&
                (maxConsumption == that.maxConsumption) &&
                (userID == that.userID);
    }



    @Override
    public int hashCode() {
        return Objects.hash(description, address, maxConsumption);
    }


}
