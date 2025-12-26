package ua.nure.verenych.travelmate_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.verenych.travelmate_api.model.Booking;
import ua.nure.verenych.travelmate_api.model.Car;
import ua.nure.verenych.travelmate_api.model.User;
import ua.nure.verenych.travelmate_api.repository.BookingRepository;
import ua.nure.verenych.travelmate_api.repository.CarRepository;
import ua.nure.verenych.travelmate_api.repository.UserRepository;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private CarRepository carRepository;
    @Autowired private UserRepository userRepository;

    // Головний метод бізнес-логіки: Створення "розумного" бронювання
    public Booking createBooking(Long userId, Long carId, Booking bookingRequest) {
        // 1. Шукаємо Юзера і Авто
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // 2. Валідація: Чи доступне авто? (Спрощена логіка - перевіряємо статус)
        if (!"AVAILABLE".equals(car.getStatus())) {
            throw new RuntimeException("Car is not available for booking");
        }

        // 3. Бізнес-логіка: Розрахунок вартості
        long days = ChronoUnit.DAYS.between(bookingRequest.getStartTime(), bookingRequest.getEndTime());
        if (days <= 0) {
            throw new RuntimeException("End date must be after start date");
        }

        BigDecimal totalCost = car.getPricePerDay().multiply(BigDecimal.valueOf(days));

        // 4. Заповнюємо об'єкт
        bookingRequest.setUser(user);
        bookingRequest.setCar(car);
        bookingRequest.setTotalCost(totalCost);
        bookingRequest.setStatus("CONFIRMED");

        // 5. Міняємо статус машини на BOOKED (щоб ніхто інший не зайняв)
        car.setStatus("BOOKED");
        carRepository.save(car);

        return bookingRepository.save(bookingRequest);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}