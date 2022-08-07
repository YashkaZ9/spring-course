package ru.baykov.project3.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.baykov.project3.dto.MeasurementDTO;
import ru.baykov.project3.dto.MeasurementResponse;
import ru.baykov.project3.dto.SensorDTO;
import ru.baykov.project3.dto.SensorResponse;
import ru.baykov.project3.model.Measurement;
import ru.baykov.project3.model.Sensor;
import ru.baykov.project3.service.SensorService;
import ru.baykov.project3.util.ExceptionResponseBody;
import ru.baykov.project3.util.SensorRegistrationException;
import ru.baykov.project3.util.SensorValidator;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping
    public SensorResponse getSensors() {
        return new SensorResponse(sensorService.getSensors().stream()
                .map(this::convertToSensorDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{name}/measurements")
    public MeasurementResponse getSensorMeasurements(@PathVariable String name) {
        return new MeasurementResponse(sensorService.getSensorMeasurements(name).stream()
                .map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @PostMapping("/registration")
    public HttpStatus registration(@RequestBody @Valid SensorDTO sensorDTO,
                                   BindingResult errors) {
        sensorValidator.validate(sensorDTO, errors);
        if (errors.hasErrors()) {
            String errorMsg = errors.getFieldError("name").getDefaultMessage();
            throw new SensorRegistrationException(errorMsg);
        }
        Sensor sensor = convertToSensor(sensorDTO);
        sensorService.registerSensor(sensor);
        return HttpStatus.ACCEPTED;
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseBody> handleException(RuntimeException ex) {
        ExceptionResponseBody response = new ExceptionResponseBody(
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
