package booktracker.data;

import booktracker.domain.Review;
import org.springframework.data.repository.CrudRepository;
import booktracker.domain.User;
public interface ReviewRepository extends CrudRepository<Review, Long> {
    public Iterable<Review> findAllByUser(User user);
}
