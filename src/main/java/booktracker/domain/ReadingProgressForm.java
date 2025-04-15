package booktracker.domain;

import lombok.Data;

@Data
public class ReadingProgressForm {
    private Long bookId;
    private int currentPage;
    private Status status;
}
