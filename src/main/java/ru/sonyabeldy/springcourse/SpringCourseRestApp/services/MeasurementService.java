package ru.sonyabeldy.springcourse.SpringCourseRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Measurement;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Sensor;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.repositories.MeasurementRepository;

import javax.xml.crypto.Data;
import java.util.Date;
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
        measurement.setCreatedAt(new Date());
        measurementRepository.save(measurement);
    }


    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public int countRainyDays() {
        return measurementRepository.findAllByIsRaining(true).size();
    }
}
