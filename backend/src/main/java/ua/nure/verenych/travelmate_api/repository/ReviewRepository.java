package ua.nure.verenych.travelmate_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.verenych.travelmate_api.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {}