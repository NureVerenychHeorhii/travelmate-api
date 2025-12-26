package ua.nure.verenych.travelmate_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.verenych.travelmate_api.model.User;

// Ось цієї частини "extends JpaRepository<User, Long>" не вистачало!
public interface UserRepository extends JpaRepository<User, Long> {
}