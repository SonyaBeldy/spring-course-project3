package ru.sonyabeldy.springcourse.SpringCourseRestApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Measurement;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    List<Measurement> findAllByIsRaining(boolean isRaining);
}
