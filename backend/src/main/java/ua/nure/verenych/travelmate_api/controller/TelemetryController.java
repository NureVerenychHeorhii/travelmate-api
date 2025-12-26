package ua.nure.verenych.travelmate_api.controller; // <--- ВИПРАВЛЕНО

import ua.nure.verenych.travelmate_api.model.CarTelemetry;       // <--- ВИПРАВЛЕНО
import ua.nure.verenych.travelmate_api.repository.TelemetryRepository; // <--- ВИПРАВЛЕНО
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {

    @Autowired
    private TelemetryRepository telemetryRepository;

    @PostMapping
    public String receiveTelemetry(@RequestBody CarTelemetry data) {
        if (data.getTimestamp() == null) {
            data.setTimestamp(LocalDateTime.now());
        }

        telemetryRepository.save(data);

        System.out.println("💾 SAVED TO DB: Car ID " + data.getCarId() +
                " | Speed: " + String.format("%.2f", data.getSpeed()) + " km/h" +
                " | Fuel: " + String.format("%.2f", data.getFuelLevel()) + "%");

        return "Data saved to Database";
    }

    @GetMapping
    public List<CarTelemetry> getAllTelemetry() {
        return telemetryRepository.findAll();
    }
}