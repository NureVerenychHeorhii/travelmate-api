package ua.nure.verenych.travelmate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.verenych.travelmate_api.model.Booking;
import ua.nure.verenych.travelmate_api.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService; // Тепер звертаємось до Сервісу, а не Репозиторію

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Оновлений метод створення
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        // Ми очікуємо, що в JSON прийде user: {id: 1}, car: {id: 1}
        return bookingService.createBooking(
                booking.getUser().getId(),
                booking.getCar().getId(),
                booking
        );
    }
}