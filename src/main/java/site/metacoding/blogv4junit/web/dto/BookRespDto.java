package site.metacoding.blogv4junit.web.dto;

import lombok.Getter;
import site.metacoding.blogv4junit.domain.book.Book;

@Getter
public class BookRespDto {
    private Long id;
    private String title;
    private String author;

    public BookRespDto toDto(Book bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
        return this;
    }

    // User 오브젝트가 필요 할 경우
    // private UserRespDto user;
}
