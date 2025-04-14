package booktracker.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class ReadingProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private final User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private final Book book;

    private int currentPage;
    private LocalDate startDate;
    private LocalDate finishDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

}
