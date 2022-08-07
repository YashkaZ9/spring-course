package ru.baykov.project3.client;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.baykov.project3.dto.MeasurementDTO;
import ru.baykov.project3.dto.MeasurementResponse;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Client {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
//        //Add sensor
//        registerSensor("Rest sensor");
//
//        //Send measurements
//        Random random = new Random();
//        int measurementsCount = 1000;
//        double minTemperature = -100;
//        double maxTemperature = 100;
//        String sensorName = "Rest sensor";
//        for (int i = 0; i < measurementsCount; ++i) {
//            sendMeasurement(minTemperature + random.nextDouble(maxTemperature * 2),
//                    random.nextBoolean(), sensorName);
//        }

        //Get sensor temperatures
        List<Double> temperatures = getTemperatures();

        //Draw chart
        drawChart(temperatures);
    }

    public static void registerSensor(String name) {
        final String url = "http://localhost:8080/sensors/registration";
        Map<String, Object> sensor = new HashMap<>();
        sensor.put("name", "Rest sensor");
        makePostJSONRequest(url, sensor);
    }

    public static void sendMeasurement(double temperature, boolean isRaining, String sensorName) {
        final String url = "http://localhost:8080/measurements/add";
        Map<String, Object> measurement = new HashMap<>();
        measurement.put("value", temperature);
        measurement.put("raining", isRaining);
        measurement.put("sensor", Map.of("name", sensorName));
        makePostJSONRequest(url, measurement);
    }

    public static void makePostJSONRequest(String url, Map<String, Object> jsonData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonData, headers);
        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Post request completed successfully.");
        } catch (HttpClientErrorException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static List<Double> getTemperatures() {
        final String url = "http://localhost:8080/measurements";
        MeasurementResponse measurements = restTemplate.getForObject(url, MeasurementResponse.class);
        if (measurements == null || measurements.getMeasurements() == null) {
            return Collections.emptyList();
        }
        return measurements.getMeasurements().stream().map(MeasurementDTO::getValue).collect(Collectors.toList());
    }

    private static void drawChart(List<Double> temperatures) {
        double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yData = temperatures.stream().mapToDouble(t -> t).toArray();
        XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "Temperatures",
                xData, yData);
        new SwingWrapper<>(chart).displayChart();
    }
}
