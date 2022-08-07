package ru.baykov.project3.dto;

import javax.validation.constraints.Size;

public class SensorDTO {
    @Size(min = 3, max = 30, message = "Sensor name length should be between 3 and 30 symbols.")
    private String name;

    public SensorDTO() {}

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
