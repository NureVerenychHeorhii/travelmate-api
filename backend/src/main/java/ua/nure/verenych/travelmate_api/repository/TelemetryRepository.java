package ua.nure.verenych.travelmate_api.repository; // <--- ВИПРАВЛЕНО

import ua.nure.verenych.travelmate_api.model.CarTelemetry; // <--- ВИПРАВЛЕНО
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetryRepository extends JpaRepository<CarTelemetry, Long> {
    List<CarTelemetry> findByCarIdOrderByTimestampDesc(Long carId);
}