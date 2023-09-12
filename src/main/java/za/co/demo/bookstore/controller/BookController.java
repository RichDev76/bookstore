package za.co.demo.bookstore.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.demo.bookstore.domain.dto.BookDto;
import za.co.demo.bookstore.domain.dto.BookUpdateDto;
import za.co.demo.bookstore.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/bookstore/api/v1")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookDtoByIsbn(isbn));
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable(value = "isbn") String isbn,
                                            @Valid @RequestBody BookUpdateDto bookDetails) {
        return ResponseEntity.ok(bookService.updateBook(isbn, bookDetails));
    }
}
