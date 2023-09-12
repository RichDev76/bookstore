package za.co.demo.bookstore.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "title", nullable = false, length = 512)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "pages", nullable = false)
    private Integer pages;

    @Column(name = "publisher", nullable = false, length = 512)
    private String publisher;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "quantity_in_stock", nullable = false)
    private Long quantityInStock;

    @Column(name = "price", nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<LineItem> lineItems = new LinkedHashSet<>();

}