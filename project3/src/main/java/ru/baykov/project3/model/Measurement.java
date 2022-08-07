package ru.baykov.project3.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @NotNull(message = "Temperature should be mentioned.")
    @Min(value = -100, message = "Invalid temperature.")
    @Max(value = 100, message = "Invalid temperature.")
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Raining state should be mentioned.")
    private Boolean raining;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull(message = "This sensor does not exist.")
    private Sensor sensor;

    public Measurement() {}

    public Measurement(Double value, Boolean raining) {
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measurement)) return false;

        Measurement that = (Measurement) o;

        if (!getValue().equals(that.getValue())) return false;
        return raining.equals(that.raining);
    }

    @Override
    public int hashCode() {
        int result = getValue().hashCode();
        result = 31 * result + raining.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", isRaining=" + raining +
                ", createdAt=" + createdAt +
                '}';
    }
}
