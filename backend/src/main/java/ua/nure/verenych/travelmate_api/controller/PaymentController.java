package ua.nure.verenych.travelmate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.verenych.travelmate_api.model.Payment;
import ua.nure.verenych.travelmate_api.repository.PaymentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    // Оновити статус платежу (наприклад, FAILED або REFUNDED)
    @PutMapping("/{id}")
    public Payment updateStatus(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id).orElseThrow();
        payment.setStatus(paymentDetails.getStatus());
        // Можна також оновити суму, якщо треба
        if (paymentDetails.getAmount() != null) {
            payment.setAmount(paymentDetails.getAmount());
        }
        return paymentRepository.save(payment);
    }

    // Видалити запис про платіж (наприклад, помилковий)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentRepository.deleteById(id);
    }
}