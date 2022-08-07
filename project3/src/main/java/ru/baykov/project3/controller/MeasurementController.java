package ru.baykov.project3.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.baykov.project3.dto.MeasurementDTO;
import ru.baykov.project3.dto.MeasurementResponse;
import ru.baykov.project3.model.Measurement;
import ru.baykov.project3.service.MeasurementService;
import ru.baykov.project3.util.ExceptionResponseBody;
import ru.baykov.project3.util.MeasurementRecordException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public MeasurementResponse getMeasurements() {
        return new MeasurementResponse(measurementService.getMeasurements().stream()
                .map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @PostMapping("/add")
    public HttpStatus fixMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                     BindingResult errors) {
        if (errors.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            errors.getFieldErrors().forEach(error -> msg
                    .append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append(";")
            );
            throw new MeasurementRecordException(msg.toString());
        }
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementService.saveMeasurement(measurement);
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

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
