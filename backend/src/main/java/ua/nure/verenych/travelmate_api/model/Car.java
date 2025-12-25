package ua.nure.verenych.travelmate_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data // Lombok автоматично створить геттери та сеттери
@Entity // Це каже Spring: "Цей клас — це таблиця в базі даних"
@Table(name = "cars") // Назва таблиці в Supabase
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make_model", nullable = false)
    private String makeModel; // Наприклад: "Toyota RAV4"

    @Column(name = "plate_number", unique = true, nullable = false)
    private String plateNumber;

    @Column(name = "fuel_type")
    private String fuelType; // PETROL, DIESEL

    @Column(name = "transmission")
    private String transmission; // AUTOMATIC, MANUAL

    @Column(name = "price_per_day", nullable = false)
    private BigDecimal pricePerDay; // Використовуємо BigDecimal для грошей

    @Column(name = "status")
    private String status; // AVAILABLE, BOOKED

    private String location; // Місто або адреса
}