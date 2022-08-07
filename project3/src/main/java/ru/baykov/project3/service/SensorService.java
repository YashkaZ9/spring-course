package ru.baykov.project3.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baykov.project3.dto.SensorDTO;
import ru.baykov.project3.model.Measurement;
import ru.baykov.project3.model.Sensor;
import ru.baykov.project3.repo.SensorRepo;
import ru.baykov.project3.util.SensorRegistrationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepo sensorRepo;

    @Autowired
    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    public List<Sensor> getSensors() {
        return sensorRepo.findAll();
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorRepo.findByName(name);
    }

    public List<Measurement> getSensorMeasurements(String name) {
        Optional<Sensor> sensor = sensorRepo.findByName(name);
        if (sensor.isEmpty()) {
            throw new SensorRegistrationException("Sensor not found.");
        }
        Hibernate.initialize(sensor.get().getMeasurements());
        return sensor.get().getMeasurements();
    }

    @Transactional
    public void registerSensor(Sensor sensor) {
        sensor.setCreatedAt(LocalDateTime.now());
        sensorRepo.save(sensor);
    }
}
