package ua.nure.verenych.travelmate_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Зв'язок: Багато бронювань може робити один юзер
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Зв'язок: Багато бронювань може бути у однієї машини (в різний час)
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // PENDING, CONFIRMED
}