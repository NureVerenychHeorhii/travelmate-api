package ua.nure.verenych.travelmate_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.verenych.travelmate_api.model.Review;
import ua.nure.verenych.travelmate_api.repository.ReviewRepository;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @PostMapping
    public Review create(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    // Редагувати відгук (змінити оцінку або текст)
    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        Review review = reviewRepository.findById(id).orElseThrow();
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        return reviewRepository.save(review);
    }

    // Видалити відгук
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reviewRepository.deleteById(id);
    }
}