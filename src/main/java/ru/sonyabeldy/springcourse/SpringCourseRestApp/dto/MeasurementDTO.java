package ru.sonyabeldy.springcourse.SpringCourseRestApp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sonyabeldy.springcourse.SpringCourseRestApp.models.Sensor;

public class MeasurementDTO {

//    @NotEmpty(message = "Raining should not be empty")
    private boolean isRaining;

    private Sensor owner;

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public Sensor getOwner() {
        return owner;
    }

    public void setOwner(Sensor owner) {
        this.owner = owner;
    }
}
