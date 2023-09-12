package za.co.demo.bookstore.service;

import za.co.demo.bookstore.domain.dto.BookDto;
import za.co.demo.bookstore.domain.dto.BookUpdateDto;
import za.co.demo.bookstore.domain.model.Book;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto book);

    Book saveBook(Book book);

    BookDto getBookDtoByIsbn(String isbn);

    Book getBookByIsbn(String isbn);

    List<BookDto> getAllBooks();

    BookDto updateBook(String isbn, BookUpdateDto bookDetails);

}
