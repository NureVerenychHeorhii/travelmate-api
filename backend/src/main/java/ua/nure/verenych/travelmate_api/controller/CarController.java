package ua.nure.verenych.travelmate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.verenych.travelmate_api.model.Car;
import ua.nure.verenych.travelmate_api.repository.CarRepository;

import java.util.List;

@RestController // Кажемо Spring, що це API, яке приймає запити
@RequestMapping("/api/cars") // Усі запити до цього класу починаються з /api/cars
public class CarController {

    @Autowired
    private CarRepository carRepository; // Підключаємо наш "склад"

    // 1. Метод щоб отримати список усіх авто
    // Запит: GET http://localhost:8081/api/cars
    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // 2. Метод щоб додати нове авто
    // Запит: POST http://localhost:8081/api/cars
    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }
}