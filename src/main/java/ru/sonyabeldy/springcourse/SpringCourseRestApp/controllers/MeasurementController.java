package ru.sonyabeldy.springcourse.SpringCourseRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.dto.MeasurementDTO;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Measurement;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.services.MeasurementService;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.services.SensorService;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.utils.MeasurementErrorResponse;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.utils.MeasurementNotCreatedException;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.utils.MeasurementValidator;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.utils.RainyDaysResponse;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementDTO);

        checkBindingResults(bindingResult);

        measurementValidator.validate(measurement, bindingResult);
        checkBindingResults(bindingResult);

        measurementService.save(convertToMeasurement(measurementDTO), sensorService.get(measurementDTO.getSensor().getName()).orElseThrow());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    private void checkBindingResults(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public RainyDaysResponse getRainyDaysCount() {
//        String countMsg = "Rainy days count: " + measurementService.countRainyDays();
        return new RainyDaysResponse(measurementService.countRainyDays());
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
