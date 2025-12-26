package ua.nure.verenych.travelmate_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.verenych.travelmate_api.model.User;
import ua.nure.verenych.travelmate_api.repository.BookingRepository;
import ua.nure.verenych.travelmate_api.repository.CarRepository;
import ua.nure.verenych.travelmate_api.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {

    @Autowired private UserRepository userRepository;
    @Autowired private BookingRepository bookingRepository;
    @Autowired private CarRepository carRepository;

    // 1. Адмін-функція: Блокування користувача
    public void banUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setRole("BANNED"); // Припустимо, ми використовуємо поле role для цього
        userRepository.save(user);
    }

    // 2. Адмін-функція: Зміна ролі (наприклад, зробити менеджером)
    public void changeUserRole(Long userId, String newRole) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setRole(newRole);
        userRepository.save(user);
    }

    // 3. Функція моніторингу: Статистика системи
    public Map<String, Object> getSystemStatistics() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("total_users", userRepository.count());
        stats.put("total_cars", carRepository.count());
        stats.put("total_bookings", bookingRepository.count());


        return stats;
    }
}