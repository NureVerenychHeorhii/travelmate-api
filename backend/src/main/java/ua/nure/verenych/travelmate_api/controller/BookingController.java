package ua.nure.verenych.travelmate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.verenych.travelmate_api.model.Booking;
import ua.nure.verenych.travelmate_api.repository.BookingRepository;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    // Історія бронювань
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Створити бронювання
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    // Оновити статус (наприклад, CONFIRMED або CANCELLED)
    @PutMapping("/{id}")
    public Booking updateBookingStatus(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setStatus(bookingDetails.getStatus());
        return bookingRepository.save(booking);
    }

    // Видалити бронювання (скасування)
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
    }
}