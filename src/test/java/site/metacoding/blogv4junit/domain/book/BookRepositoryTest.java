package site.metacoding.blogv4junit.domain.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩한다.
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void db_init() {
        bookRepository.deleteAll();
        em.createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }

    // C : 유효성 검사 필요 (X)
    @Test
    @Order(1)
    public void save_test() {
        // given
        String title = "스프링부트 1강";
        String author = "짱구";
        Book book = new Book(title, author);

        // when
        Book bookEntity = bookRepository.save(book);

        // then
        assertEquals(title, bookEntity.getTitle());
        assertEquals(author, bookEntity.getAuthor());
        assertEquals(1, bookEntity.getId());
    }

    @Test
    @Order(2)
    public void findById_test() {
        // given
        String title = "스프링부트 1강";
        String author = "짱구";
        Book book = new Book(title, author);
        bookRepository.save(book);

        Long id = 1L;

        // when
        Book bookEntity = bookRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("오류 발생");
        });

        // then
        assertEquals(title, bookEntity.getTitle());
        assertEquals(author, bookEntity.getAuthor());
        assertEquals(id, bookEntity.getId());
    }
}