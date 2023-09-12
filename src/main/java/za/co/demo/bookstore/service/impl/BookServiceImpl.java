package za.co.demo.bookstore.service.impl;

import org.springframework.stereotype.Service;
import za.co.demo.bookstore.domain.dto.BookDto;
import za.co.demo.bookstore.domain.dto.BookUpdateDto;
import za.co.demo.bookstore.domain.mapper.GenericMapper;
import za.co.demo.bookstore.domain.model.Book;
import za.co.demo.bookstore.exception.EntityNotFoundException;
import za.co.demo.bookstore.repository.BookRepository;
import za.co.demo.bookstore.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenericMapper mapper;

    public BookServiceImpl(BookRepository bookRepository,
                           GenericMapper modelMapper){
        this.bookRepository = bookRepository;
        this.mapper = modelMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = this.mapper.mapBookDtoToBook(bookDto);
        return this.mapper.mapBookToBookDto(saveBook(book));
    }
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public BookDto getBookDtoByIsbn(String isbn) {
        return this.mapper.mapBookToBookDto(getBookByIsbn(isbn));
    }
    @Override
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException(Book.class , "isbn", isbn));
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books.stream().map(this.mapper::mapBookToBookDto).collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(String isbn, BookUpdateDto bookDetails) {
        Book book = getBookByIsbn(isbn);
        if(bookDetails.getPrice() != null) {
            book.setPrice(bookDetails.getPrice());
        }

        if(bookDetails.getQuantityInStock() != null) {
            book.setQuantityInStock(bookDetails.getQuantityInStock());
        }

        return mapper.mapBookToBookDto(saveBook(book));
    }
}
