package booktracker.controller;

import booktracker.data.BookRepository;
import booktracker.data.ReadingProgressRepository;
import booktracker.data.UserRepository;
import booktracker.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import org.springframework.ui.Model;;


@Controller
@RequestMapping("/library")
public class LibraryController {

    private final BookRepository bookRepo;
    private final ReadingProgressRepository progressRepo;
    private final UserRepository userRepo;

    public LibraryController(BookRepository bookRepo,
                             ReadingProgressRepository progressRepo,
                             UserRepository userRepo) {
        this.bookRepo = bookRepo;
        this.progressRepo = progressRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String userLibrary(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("progresses", progressRepo.findByUser(user));
        model.addAttribute("statuses", Status.values());

        return "library";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        model.addAttribute("form", new ReadingProgressForm());
        return "addReadingProgress";
    }

    @PostMapping("/add")
    public String addProgress(@ModelAttribute ReadingProgressForm form,
                              Principal principal,
                              Model model) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        Book book = bookRepo.findById(form.getBookId()).orElse(null);

        if (book == null) {
            model.addAttribute("error", "Книга не найдена");
            model.addAttribute("books", bookRepo.findAll());
            return "addReadingProgress";
        }

        ReadingProgress progress = new ReadingProgress(
                null,
                user,
                book,
                form.getCurrentPage(),
                LocalDate.now(),
                form.getStatus() == Status.FINISHED ? LocalDate.now() : null,
                form.getStatus()
        );

        progressRepo.save(progress);
        return "redirect:/library";
    }

    @PostMapping("/delete/{id}")
    public String deleteProgress(@PathVariable Long id, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        ReadingProgress progress = progressRepo.findById(id).orElse(null);
        if (progress != null && progress.getUser().equals(user)) {
            progressRepo.delete(progress);
        }
        return "redirect:/library";
    }

    @PostMapping("/update/{id}")
    public String updateProgress(@PathVariable Long id,
                                 @RequestParam int page,
                                 @RequestParam Status status,
                                 Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElse(null);
        ReadingProgress progress = progressRepo.findById(id).orElse(null);
        if (progress != null && progress.getUser().equals(user)) {
            progress.setCurrentPage(page);
            progress.setStatus(status);
            if (status == Status.FINISHED && progress.getFinishDate() == null) {
                progress.setFinishDate(LocalDate.now());
            }
            progressRepo.save(progress);
        }
        return "redirect:/library";
    }
}

