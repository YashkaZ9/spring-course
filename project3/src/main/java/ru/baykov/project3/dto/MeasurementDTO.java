package ru.baykov.project3.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {
    @NotNull(message = "Temperature should be mentioned.")
    @Min(value = -100, message = "Invalid temperature.")
    @Max(value = 100, message = "Invalid temperature.")
    private Double value;

    @NotNull(message = "Raining state should be mentioned.")
    private Boolean raining;

    @NotNull(message = "This sensor does not exist.")
    private SensorDTO sensor;

    public MeasurementDTO() {}

    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
