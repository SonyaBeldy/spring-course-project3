package ru.sonyabeldy.springcourse.SpringCourseRestApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Sensor;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> get(String name) {
        return sensorRepository.findByName(name);
    }
}
