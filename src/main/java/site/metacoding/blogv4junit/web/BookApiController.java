package site.metacoding.blogv4junit.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.metacoding.blogv4junit.web.dto.BookSaveReqDto;

@RestController
public class BookApiController {

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestBody BookSaveReqDto bookSaveReqDto) {
        BookSaveReqDto bookEntity = bookSaveReqDto;
        // System.out.println(bookEntity.getTitle());
        // System.out.println(bookEntity.getAuthor());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
