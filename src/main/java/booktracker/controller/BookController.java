package booktracker.controller;

import booktracker.domain.Book;
import booktracker.domain.Status;
import booktracker.domain.User;
import booktracker.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/plib")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book(null, "", "", "", 0, null, null));
        return "addBook";
    }

    @PostMapping("/add")
    public String handleAddBook(@ModelAttribute Book book, Model model) {
        if (bookService.bookExists(book)) {
            model.addAttribute("error", "Такая книга уже существует");
            return "addBook";
        }
        System.out.println(book.toString());
        bookService.save(book);
        return "redirect:/plib";
    }
}
