package booktracker.controller;

import booktracker.domain.Book;
import booktracker.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/plib")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String showBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("allBooks", books);
        return "books";
    }

    @GetMapping("/get")
    public String showUserBooks(Model model, Principal principal) {
        List<Book> books = bookService.findAllForUser(principal.getName());
        model.addAttribute("userBooks", books);
        return "userBooks";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book(null, "", "", "",
                0, "",  null, null));
        return "addBook";
    }

    @PostMapping("/add")
    public String handleAddBook(@ModelAttribute Book book, Model model, Principal principal) {
        if (bookService.bookExists(book)) {
            model.addAttribute("error", "Такая книга уже существует");
            return "addBook";
        }
        book.setPublisher(principal.getName());
        bookService.save(book);
        return "redirect:/plib";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "editBook"; // thymeleaf-шаблон для редактирования
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        bookService.updateBook(id, book);
        return "redirect:/plib/get";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/plib/get";
    }
}
