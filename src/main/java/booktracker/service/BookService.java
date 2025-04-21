package booktracker.service;

import booktracker.data.BookRepository;
import booktracker.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }
    public List<Book> findAllForUser(String username) {
        List<Book> books = new ArrayList<>();
        bookRepository.findAllByPublisher(username).forEach(e -> books.add((Book) e));
        return books;
    }
    public void updateBook(Long id, Book updated) {
        Book existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setAuthor(updated.getAuthor());
        existing.setTotalPages(updated.getTotalPages());
        bookRepository.save(existing);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

