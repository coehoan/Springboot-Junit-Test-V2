package site.metacoding.blogv4junit.serivce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import site.metacoding.blogv4junit.domain.book.Book;
import site.metacoding.blogv4junit.domain.book.BookRepository;
import site.metacoding.blogv4junit.service.BookService;
import site.metacoding.blogv4junit.web.dto.BookRespDto;
import site.metacoding.blogv4junit.web.dto.BookSaveReqDto;

@ExtendWith(MockitoExtension.class) // 스프링 컨테이너에 편입시켜준다.
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookServiceTest {

    @InjectMocks // Mockito 환경에 Repository를 찾아서 의존하게된다.
    private BookService bookService;

    @Mock
    // @DataJpaTest가 없기때문에 사용불가, @Mock로 가짜 레파지토리를 만들어서 테스트한다.
    private BookRepository bookRepository;

    @Test
    public void 책등록하기_테스트() {
        // given
        BookSaveReqDto reqDto = new BookSaveReqDto();
        reqDto.setTitle("스프링1강");
        reqDto.setAuthor("메타코딩");

        // stub 행동정의 : 가설
        Mockito.when(bookRepository.save(reqDto.toEntity())).thenReturn(new Book(1L, "스프링1강", "메타코딩"));

        // when
        BookRespDto respDto = bookService.책등록하기(reqDto);

        // then
        assertEquals(1L, respDto.getId());
        assertEquals("스프링1강", respDto.getTitle());
        assertEquals("메타코딩", respDto.getAuthor());
    }

    @Test
    public void 책한건등록하기_테스트() {

        Optional<Book> bookOp = Optional.of(new Book(1L,
                "스프링1강", "메타코딩"));
        // given
        Long id = 1L;

        // stub 행동정의 : 가설
        Mockito.when(bookRepository.findById(id)).thenReturn(bookOp);

        // when
        BookRespDto respDto = bookService.책한건가져오기(id);

        // then
        assertEquals(1L, respDto.getId());
        assertEquals("스프링1강", respDto.getTitle());
        assertEquals("메타코딩", respDto.getAuthor());
    }
}
