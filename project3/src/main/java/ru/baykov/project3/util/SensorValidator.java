package ru.baykov.project3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.baykov.project3.dto.SensorDTO;
import ru.baykov.project3.model.Sensor;
import ru.baykov.project3.service.SensorService;

import java.util.Optional;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO newSensor = (SensorDTO) target;
        Optional<Sensor> sensor = sensorService.getSensorByName(newSensor.getName());
        if (sensor.isPresent()) {
            errors.rejectValue("name", "", "This sensor already exists.");
        }
    }
}
