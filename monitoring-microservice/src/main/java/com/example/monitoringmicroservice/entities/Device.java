package com.example.monitoringmicroservice.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="device", schema="monitoring_db")
public class Device {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "consumption", nullable = false)
    private  Integer maxConsumption;

    @Column(name = "userID", nullable = false)
    private Integer userID;

    public Device(Integer id, Integer maxConsumption, Integer userID) {
        this.id = id;
        this.maxConsumption = maxConsumption;
        this.userID = userID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Device() {

    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", maxConsumption=" + maxConsumption +
                ", userID=" + userID +
                '}';
    }
}
