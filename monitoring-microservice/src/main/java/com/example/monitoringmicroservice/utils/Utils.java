package com.example.monitoringmicroservice.utils;

import com.example.monitoringmicroservice.dtos.DeviceJsonDTO;
import com.example.monitoringmicroservice.dtos.JsonDTO;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Utils {

    public Utils() {
    }

    public JsonDTO processJson(String message) {
        String jsonContent = message.replaceAll("\\{|\\}", "");

        // Separă conținutul JSON în perechi de cheie-valoare
        String[] keyValuePairs = jsonContent.split(",");

        // Extrage fieldurile
        Long timestamp = null;
        Integer deviceID = null;
        Float measurement = null;

        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            if ("\"timestamp\"".equals(key)) {
                timestamp = Long.parseLong(value);
            } else if ("\"deviceID\"".equals(key)) {
                deviceID = Integer.parseInt(value);
            } else if ("\"measurement\"".equals(key)) {
                measurement = Float.parseFloat(value);
            }
        }

        JsonDTO dto = new JsonDTO(timestamp, deviceID, measurement);
        return  dto;
    }

    public Integer getHourFromTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public DeviceJsonDTO processDeviceJson(String message) {
        String jsonContent = message.replaceAll("\\{|\\}", "");

        // Separă conținutul JSON în perechi de cheie-valoare
        String[] keyValuePairs = jsonContent.split(",");

        // Extrage fieldurile
        Integer deviceID = null;
        Integer userID = null;
        Integer maxConsumption = null;
        String operation = null;

        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            if ("\"deviceID\"".equals(key)) {
                deviceID = Integer.parseInt(value);
            } else if ("\"userID\"".equals(key)) {
                userID = Integer.parseInt(value);
            } else if ("\"maxConsumption\"".equals(key)) {
                maxConsumption = Integer.parseInt(value);
            } else if ("\"operation\"".equals(key)) {
                operation = value;
            }
        }

        DeviceJsonDTO dto = new DeviceJsonDTO(deviceID, userID, maxConsumption, operation);
        return  dto;
    }

    public Timestamp incrementDay(Timestamp timestamp, Integer increment) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        // Increment the day
        LocalDateTime incrementedDateTime = localDateTime.plusDays(increment);
        System.out.println("incremented datetime:" + incrementedDateTime);

        // Convert back to Timestamp
        Timestamp incrementedTimestamp = Timestamp.valueOf(incrementedDateTime);
        System.out.println("icnremented timestamp" + incrementedTimestamp);
        return incrementedTimestamp;

    }
}
