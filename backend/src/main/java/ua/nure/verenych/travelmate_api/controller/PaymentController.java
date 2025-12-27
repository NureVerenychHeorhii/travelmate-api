package ua.nure.verenych.travelmate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nure.verenych.travelmate_api.model.Payment;
import ua.nure.verenych.travelmate_api.model.Booking;
import ua.nure.verenych.travelmate_api.repository.PaymentRepository;
import ua.nure.verenych.travelmate_api.repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Payment payment) {
        try {
            // 1. Перевірка вхідних даних
            if (payment.getBooking() == null || payment.getBooking().getId() == null) {
                return ResponseEntity.badRequest().body("Помилка: Не вказано ID бронювання (booking.id)");
            }

            // 2. Пошук бронювання
            Long bookingId = payment.getBooking().getId();
            Booking realBooking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Бронювання з ID " + bookingId + " не знайдено"));

            // 3. Заповнення даних платежу
            payment.setBooking(realBooking);

            // 👇 ВИПРАВЛЕННЯ ПОМИЛКИ: Якщо тип не вказано, ставимо "CARD"
            if (payment.getType() == null) {
                payment.setType("CARD");
            }

            // Про всяк випадок вручну ставимо дату, якщо її немає
            if (payment.getTransactionDate() == null) {
                payment.setTransactionDate(LocalDateTime.now());
            }

            // 4. Збереження
            paymentRepository.save(payment);

            // 5. Повертаємо успіх
            return ResponseEntity.ok("Оплата пройшла успішно! ID платежу збережено.");

        } catch (Exception e) {
            e.printStackTrace(); // Друкуємо помилку в консоль
            return ResponseEntity.status(500).body("КРИТИЧНА ПОМИЛКА: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Payment updateStatus(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id).orElseThrow();
        payment.setStatus(paymentDetails.getStatus());
        if (paymentDetails.getAmount() != null) {
            payment.setAmount(paymentDetails.getAmount());
        }
        return paymentRepository.save(payment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentRepository.deleteById(id);
    }
}