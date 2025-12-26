package ua.nure.verenych.travelmate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.verenych.travelmate_api.model.Car;
import ua.nure.verenych.travelmate_api.repository.CarRepository;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    // 1. Отримати всі машини (READ)
    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // 2. Отримати одну машину за ID (READ)
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    // 3. Додати нову машину (CREATE)
    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    // 4. Оновити машину (UPDATE)
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Car car = carRepository.findById(id).orElseThrow();

        car.setPricePerDay(carDetails.getPricePerDay());
        car.setStatus(carDetails.getStatus());
        car.setLocation(carDetails.getLocation());
        // Можна додати інші поля, якщо треба

        return carRepository.save(car);
    }

    // 5. Видалити машину (DELETE)
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }
}