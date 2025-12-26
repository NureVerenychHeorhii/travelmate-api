package ua.nure.verenych.travelmate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.verenych.travelmate_api.model.MaintenanceLog;
import ua.nure.verenych.travelmate_api.repository.MaintenanceLogRepository;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceLogController {

    @Autowired
    private MaintenanceLogRepository maintenanceLogRepository;

    @GetMapping
    public List<MaintenanceLog> getAll() {
        return maintenanceLogRepository.findAll();
    }

    @PostMapping
    public MaintenanceLog create(@RequestBody MaintenanceLog log) {
        return maintenanceLogRepository.save(log);
    }

    // Оновити дані про ремонт (наприклад, змінилася ціна)
    @PutMapping("/{id}")
    public MaintenanceLog updateLog(@PathVariable Long id, @RequestBody MaintenanceLog logDetails) {
        MaintenanceLog log = maintenanceLogRepository.findById(id).orElseThrow();
        log.setDescription(logDetails.getDescription());
        log.setCost(logDetails.getCost());
        log.setServiceDate(logDetails.getServiceDate());
        return maintenanceLogRepository.save(log);
    }

    // Видалити запис про ремонт
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        maintenanceLogRepository.deleteById(id);
    }
}