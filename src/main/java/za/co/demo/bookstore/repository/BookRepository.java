package za.co.demo.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.demo.bookstore.domain.model.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findBookByIsbn(String isbn);
 }
