package com.example.monitoringmicroservice.dtos;

import jakarta.persistence.Column;

import java.sql.Date;

public class MeasurementDTO {

    private Date measurementDate;

    private Integer hour;

    private Float value;

    public MeasurementDTO(Date measurementDate, Integer hour, Float value) {
        this.measurementDate = measurementDate;
        this.hour = hour;
        this.value = value;
    }

    public MeasurementDTO() {
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
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
}
