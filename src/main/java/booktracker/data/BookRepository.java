package booktracker.data;

import booktracker.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthorAndTotalPages(String title, String author, int totalPages);
    Iterable<Object> findAllByPublisher(String username);
}
