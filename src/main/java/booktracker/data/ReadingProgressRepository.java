package booktracker.data;

import booktracker.domain.ReadingProgress;
import booktracker.domain.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ReadingProgressRepository extends CrudRepository<ReadingProgress, Long> {
    List<ReadingProgress> findByUser(User user);
    List<ReadingProgress> findAllByUser(User user);
}
