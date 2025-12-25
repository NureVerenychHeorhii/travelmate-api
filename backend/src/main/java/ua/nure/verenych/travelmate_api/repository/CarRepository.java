package ua.nure.verenych.travelmate_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.verenych.travelmate_api.model.Car;

// Цей інтерфейс дає нам готові методи: save(), findAll(), delete()
public interface CarRepository extends JpaRepository<Car, Long> {
}