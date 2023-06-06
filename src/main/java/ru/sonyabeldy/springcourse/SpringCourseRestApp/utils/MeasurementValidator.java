package ru.sonyabeldy.springcourse.SpringCourseRestApp.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Measurement;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.services.MeasurementService;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final MeasurementService measurementService;
    private final SensorService sensorService;

    public MeasurementValidator(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (sensorService.get(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("Sensor", "", "Sensor with this name doesnt exist");
        }
    }
}
