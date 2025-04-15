package booktracker.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force=true)
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(nullable = false)
    private int rating;

    @Column(length = 1000)
    private String comment;

    private LocalDate createdAt;

}

