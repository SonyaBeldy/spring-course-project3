package ru.sonyabeldy.springcourse.SpringCourseRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.dto.SensorDTO;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Sensor;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.services.SensorService;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.utils.SensorErrorResponse;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.utils.SensorNotCreatedException;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.utils.SensorValidator;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;

    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator validator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = validator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {

        sensorValidator.validate(convertedToSensor(sensorDTO), bindingResult);

        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new SensorNotCreatedException(errorMsg.toString());
        }

        sensorService.save(convertedToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertedToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }


    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
