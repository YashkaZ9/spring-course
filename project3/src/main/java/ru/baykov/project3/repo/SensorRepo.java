package ru.baykov.project3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baykov.project3.model.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepo extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
