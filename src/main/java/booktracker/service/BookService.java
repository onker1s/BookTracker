package booktracker.service;

import booktracker.data.BookRepository;
import booktracker.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public boolean bookExists(Book book) {
        return bookRepository
                .findByTitleAndAuthorAndTotalPages(book.getTitle(), book.getAuthor(), book.getTotalPages())
                .isPresent();
    }
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}

