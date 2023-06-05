package ru.sonyabeldy.springcourse.SpringCourseRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.dto.MeasurementDTO;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Measurement;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.services.MeasurementService;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;

    public MeasurementController(ModelMapper modelMapper, MeasurementService measurementService) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody MeasurementDTO measurementDTO) {

        measurementService.save(convertedToMeasurement(measurementDTO));

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    private Measurement convertedToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
