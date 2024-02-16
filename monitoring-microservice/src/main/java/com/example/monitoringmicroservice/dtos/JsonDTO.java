package com.example.monitoringmicroservice.dtos;

public class JsonDTO {

    private Long timestamp;
    private Integer deviceID;
    private Float measurement;

    public JsonDTO() {
    }

    public JsonDTO(Long timestamp, Integer deviceID, Float measurement) {
        this.timestamp = timestamp;
        this.deviceID = deviceID;
        this.measurement = measurement;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Integer deviceID) {
        this.deviceID = deviceID;
    }

    public Float getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Float measurement) {
        this.measurement = measurement;
    }
}
