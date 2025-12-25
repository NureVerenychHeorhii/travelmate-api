package ua.nure.verenych.travelmate_api.repository;

import org.springframework.data.jpa.repository.JpaRepository; // <-- Перевірте цей імпорт
import ua.nure.verenych.travelmate_api.model.Booking;

// ВАЖЛИВО: Має бути "extends JpaRepository<Booking, Long>"
public interface BookingRepository extends JpaRepository<Booking, Long> {
}