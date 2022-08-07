package ru.baykov.project3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baykov.project3.model.Measurement;
import ru.baykov.project3.model.Sensor;
import ru.baykov.project3.repo.MeasurementRepo;
import ru.baykov.project3.util.SensorRegistrationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepo measurementRepo;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepo measurementRepo, SensorService sensorService) {
        this.measurementRepo = measurementRepo;
        this.sensorService = sensorService;
    }

    @Transactional
    public void saveMeasurement(Measurement measurement) {
        String sensorName = measurement.getSensor().getName();
        Optional<Sensor> sensor = sensorService.getSensorByName(sensorName);
        if (sensor.isEmpty()) {
            throw new SensorRegistrationException("Sensor not registered.");
        }
        measurement.setSensor(sensor.get());
        measurement.setCreatedAt(LocalDateTime.now());
        measurementRepo.save(measurement);
    }

    public List<Measurement> getMeasurements() {
        return measurementRepo.findAll();
    }

    public Integer getRainyDaysCount() {
        return measurementRepo.countByRainingIsTrue();
    }
}
