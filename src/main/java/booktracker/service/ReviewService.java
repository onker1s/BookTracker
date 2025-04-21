package booktracker.service;

import booktracker.data.ReviewRepository;
import booktracker.domain.Review;
import booktracker.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;

    public Review save(Review review) {
        return reviewRepository.save(review);
    }
    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        reviewRepository.findAll().forEach(reviews::add);
        return reviews;
    }
    public List<Review> findAllByUsername(String username) {
        List<Review> reviews = new ArrayList<>();
        User user = userService.findByUsername(username);
        reviewRepository.findAllByUser(user).forEach(reviews::add);
        return reviews;
    }
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Отзыв не найден"));
    }

    public void updateReview(Long id, Review updated) {
        Review existing = findById(id);
        existing.setComment(updated.getComment());
        existing.setRating(updated.getRating());
        reviewRepository.save(existing);
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}

