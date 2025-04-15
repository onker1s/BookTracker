package booktracker.service;

import booktracker.data.BookRepository;
import booktracker.data.ReviewRepository;
import booktracker.domain.Book;
import booktracker.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

        public Review save(Review review) {
        return reviewRepository.save(review);
    }
}

