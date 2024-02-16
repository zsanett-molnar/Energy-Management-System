package com.energyapp.devicemanagement.entities;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Entity
@Table(name="device", schema="user_db")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private  String address;

    @Column(name = "consumption", nullable = false)
    private  Integer maxConsumption;

    @ManyToOne(optional = true)
    @Nullable
    @JoinColumn(name="userID", nullable = true)
    private AppUser userID;


    public Device() {

    }

    public Device(String description, String address, Integer maxConsumption, AppUser userID) {
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
        this.userID = userID;
    }

    public Device(String description, String address, Integer maxConsumption) {
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
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

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", maxConsumption=" + maxConsumption +
                ", userID=" + userID.getUserID() +
                '}';
    }
}
