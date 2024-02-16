package com.example.monitoringmicroservice.entities;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="measurement", schema="monitoring_db")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "measurement_date")
    private Timestamp measurementDate;
    @Column(name = "hour")
    private Integer hour;

//    @Column(name = "deviceID")
//    private Integer deviceID;

    @ManyToOne(optional = true)
    @Nullable
    @JoinColumn(name="deviceID", nullable = true)
    private Device deviceID;

    @Column(name = "value")
    private Float value;

//    public Measurement(Integer id, Timestamp measurementDate, Integer hour, Integer deviceID, Float value) {
//        this.id = id;
//        this.measurementDate = measurementDate;
//        this.hour = hour;
//        this.deviceID = deviceID;
//        this.value = value;
//    }
//
//    public Measurement(Timestamp measurementDate, Integer hour, Integer deviceID, Float value) {
//        this.measurementDate = measurementDate;
//        this.hour = hour;
//        this.deviceID = deviceID;
//        this.value = value;
//    }


    public Measurement(Integer id, Timestamp measurementDate, Integer hour, @Nullable Device deviceID, Float value) {
        this.id = id;
        this.measurementDate = measurementDate;
        this.hour = hour;
        this.deviceID = deviceID;
        this.value = value;
    }

    public Measurement(Timestamp measurementDate, Integer hour, @Nullable Device deviceID, Float value) {
        this.measurementDate = measurementDate;
        this.hour = hour;
        this.deviceID = deviceID;
        this.value = value;
    }

    public Measurement() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Timestamp measurementDate) {
        this.measurementDate = measurementDate;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Nullable
    public Device getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(@Nullable Device deviceID) {
        this.deviceID = deviceID;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementDate=" + measurementDate +
                ", hour=" + hour +
                ", deviceID=" + deviceID +
                ", value=" + value +
                '}';
    }
}
