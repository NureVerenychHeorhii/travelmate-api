package ua.nure.verenych.travelmate_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.verenych.travelmate_api.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}