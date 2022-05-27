package site.metacoding.blogv4junit.domain.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book WHERE id =:id", nativeQuery = true)
    Optional<Book> findById(@Param("id") Long id);
}
