package booktracker.controller;

import booktracker.domain.Book;
import booktracker.domain.Review;
import booktracker.service.BookService;
import booktracker.service.ReadingProgressService;
import booktracker.service.ReviewService;
import booktracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private final ReadingProgressService readingProgressService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final BookService bookService;

    public ReviewController(ReadingProgressService readingProgressService, ReviewService reviewService,
                            UserService userService, BookService bookService) {
        this.readingProgressService = readingProgressService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public String showReviews(Model model, Principal principal) {
        List<Review> reviews = reviewService.findAll();
        model.addAttribute("allReviews", reviews);
        return "reviews";
    }

    @GetMapping("/get")
    public String showUserReviews(Model model, Principal principal) {
        List<Review> reviews = reviewService.findAllByUsername(principal.getName());
        model.addAttribute("userReviews", reviews);
        return "userReviews";
    }

    @GetMapping("/add")
    public String showReviewForm(Model model, Principal principal) {
        List<Book> userBooks = readingProgressService.getUserBooks(principal.getName());
        model.addAttribute("userBooks", userBooks);
        Review review = new Review();
        review.setBook(new Book());
        model.addAttribute("review", review);
        return "review_form";
    }

    @PostMapping("/add")
    public String submitReview(@ModelAttribute("review") Review review, Model model, Principal principal) {
        // Получаем объект Book из базы данных по id
        Book selectedBook = bookService.findById(review.getBook().getId());
        review.setBook(selectedBook);  // Устанавливаем объект книги в отзыв
        review.setUser(userService.findByUsername(principal.getName()));
        review.setCreatedAt(LocalDate.now());
        reviewService.save(review);
        return "redirect:/review/get";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Review review = reviewService.findById(id);
        model.addAttribute("review", review);
        return "editReview"; // thymeleaf-шаблон для редактирования
    }

    @PostMapping("/edit/{id}")
    public String updateReview(@PathVariable Long id, @ModelAttribute Review review) {
        reviewService.updateReview(id, review);
        return "redirect:/review/get";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteById(id);
        return "redirect:/review/get";
    }

}
