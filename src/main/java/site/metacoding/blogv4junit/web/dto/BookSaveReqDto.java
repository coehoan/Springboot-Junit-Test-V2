package site.metacoding.blogv4junit.web.dto;

import lombok.Setter;
import site.metacoding.blogv4junit.domain.book.Book;

// ReqDto는 빈생성자 + Setter로 동작한다.
@Setter
public class BookSaveReqDto {
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder().title(this.title).author(this.author).build();
    }
}
