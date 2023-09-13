package za.co.demo.bookstore.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import za.co.demo.bookstore.domain.dto.BookDto;
import za.co.demo.bookstore.domain.dto.BookUpdateDto;

import java.util.List;

@Tag(name = "Book", description = "The Book Api")
public interface BookApi {
    @Operation(
            summary = "gets all books",
            description = "Returns a list of all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<BookDto>> getAllBooks();

    @Operation(
            summary = "find a book",
            description = "returns a book based on the supplied isbn")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "no book found")
    })
    ResponseEntity<BookDto> getBookByIsbn(@PathVariable String isbn);

    @Operation(
            summary = "adds a book",
            description = "Adds a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully added a book"),
            @ApiResponse(responseCode = "409", description = "duplicate book")
    })
    ResponseEntity<?> createBook(@Valid @RequestBody BookDto book);

    @Operation(
            summary = "update book details",
            description = "Updates some book details such as price and/or quantity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully updated book details"),
            @ApiResponse(responseCode = "404", description = "no book found")
    })
    ResponseEntity<BookDto> updateBook(@PathVariable(value = "isbn") String isbn,
                                       @Valid @RequestBody BookUpdateDto bookDetails);
}
