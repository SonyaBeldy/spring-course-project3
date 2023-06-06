package ru.sonyabeldy.springcourse.SpringCourseRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Measurement;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Sensor;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.repositories.MeasurementRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void save(Measurement measurement, Sensor sensor) {
        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
    }


    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }
}
