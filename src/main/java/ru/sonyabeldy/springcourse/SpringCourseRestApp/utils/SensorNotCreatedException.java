package ru.sonyabeldy.springcourse.SpringCourseRestApp.utils;

public class SensorNotCreatedException extends RuntimeException {

    public SensorNotCreatedException(String message) {
        super(message);
    }
}
