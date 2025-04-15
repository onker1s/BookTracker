package booktracker.service;

import booktracker.data.ReadingProgressRepository;
import booktracker.data.UserRepository;
import booktracker.domain.ReadingProgress;
import org.springframework.stereotype.Service;
import booktracker.domain.Book;
import booktracker.domain.User;
import java.util.List;

@Service
public class ReadingProgressService {

    private final ReadingProgressRepository readingProgressRepository;
    private final UserRepository userRepository;

    public ReadingProgressService(ReadingProgressRepository readingProgressRepository, UserRepository userRepository) {
        this.readingProgressRepository = readingProgressRepository;
        this.userRepository = userRepository;
    }

    public List<Book> getUserBooks(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        List<ReadingProgress> progressList = readingProgressRepository.findAllByUser(user);

        return progressList.stream()
                .map(ReadingProgress::getBook)
                .distinct() // на случай, если вдруг есть дубликаты
                .toList();
    }
}
