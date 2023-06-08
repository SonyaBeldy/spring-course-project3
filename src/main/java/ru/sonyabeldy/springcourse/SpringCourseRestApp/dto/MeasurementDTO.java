package ru.sonyabeldy.springcourse.SpringCourseRestApp.dto;

import jakarta.validation.constraints.*;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Sensor;

public class MeasurementDTO {

    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be more than -100")
    @Max(value = 100, message = "Value should be less than 100")
    private double value;

    @NotNull(message = "Raining should not be empty")
    private boolean isRaining;

    @NotNull(message = "Sensor should not be empty")
    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
