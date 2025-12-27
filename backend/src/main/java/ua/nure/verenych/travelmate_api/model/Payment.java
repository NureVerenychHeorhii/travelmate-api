package ua.nure.verenych.travelmate_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    private BigDecimal amount;

    private String status; // PAID, FAILED

    // 👇 ДОДАНО: Поле, через яке була помилка в базі
    @Column(name = "type")
    private String type; // CARD, CASH

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate = LocalDateTime.now();
}