package ru.baykov.project3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baykov.project3.model.Measurement;

@Repository
public interface MeasurementRepo extends JpaRepository<Measurement, Integer> {
    Integer countByRainingIsTrue();
}
