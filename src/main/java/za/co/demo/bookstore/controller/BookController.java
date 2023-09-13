package za.co.demo.bookstore.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.demo.bookstore.controller.api.BookApi;
import za.co.demo.bookstore.domain.dto.BookDto;
import za.co.demo.bookstore.domain.dto.BookUpdateDto;
import za.co.demo.bookstore.service.BookService;
import za.co.demo.bookstore.util.Utils;

import java.util.List;

@RestController
@RequestMapping("/bookstore/api/v1")
@Slf4j
public class BookController implements BookApi {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        log.info("-- Get All Books -- ");
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @Override
    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable String isbn) {
        log.info(String.format("-- Get Book By Isbn -- isbn ['%s']", isbn));
        return ResponseEntity.ok(bookService.getBookDtoByIsbn(isbn));
    }
    @Override
    @PostMapping("/books")
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDto book) {
        log.info("-- Create Book -- ");
        log.info(Utils.toJson(book));
        BookDto createdBook = bookService.createBook(book);
        return ResponseEntity.created(Utils.getLocationUri(createdBook.getIsbn())).build();
    }
    @Override
    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable(value = "isbn") String isbn,
                                            @Valid @RequestBody BookUpdateDto bookDetails) {
        log.info(String.format("-- Update Book -- isbn ['%s']", isbn));
        log.info(Utils.toJson(bookDetails));
        BookDto updatedBook = bookService.updateBook(isbn, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }
}
