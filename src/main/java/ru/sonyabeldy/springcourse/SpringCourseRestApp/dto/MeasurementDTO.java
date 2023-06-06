package ru.sonyabeldy.springcourse.SpringCourseRestApp.dto;

import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Sensor;

public class MeasurementDTO {

    private double value;
//    @NotEmpty(message = "Raining should not be empty")
    private boolean isRaining;

    private Sensor sensor;

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
