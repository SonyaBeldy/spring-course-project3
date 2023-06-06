package ru.sonyabeldy.springcourse.SpringCourseRestApp.utils;

public class MeasurementNotCreatedException extends RuntimeException{
    public MeasurementNotCreatedException(String message) {
        super(message);
    }
}
