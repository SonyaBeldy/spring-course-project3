package ru.sonyabeldy.springcourse.SpringCourseRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.dto.MeasurementDTO;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Measurement;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.services.MeasurementService;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.services.SensorService;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.utils.MeasurementValidator;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final MeasurementValidator measurementValidator;

    public MeasurementController(ModelMapper modelMapper, MeasurementService measurementService, SensorService sensorService, MeasurementValidator measurementValidator) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.measurementValidator = measurementValidator;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {

        Measurement measurement = convertedToMeasurement(measurementDTO);
        measurementValidator.validate(measurement, bindingResult);

        measurementService.save(convertedToMeasurement(measurementDTO), sensorService.get(measurementDTO.getSensor().getName()).orElseThrow());

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Measurement> getMeasurements() {
        return measurementService.findAll();
    }

    private Measurement convertedToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
