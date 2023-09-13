package za.co.demo.bookstore.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hibernate.validator.constraints.ISBN.Type.ANY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ISBN(type = ANY, message = "Only valid ISBN-10 and ISBN-13 values allowed")
    private String isbn;

    @NotBlank(message = "title cannot be null or empty")
    private String title;

    @NotBlank(message = "author cannot be null or empty")
    private String author;

    @Min(value = 1, message = "number of pages must be > 0")
    private int pages;

    @NotBlank(message = "title cannot be null or empty")
    private String publisher;

    @Past(message = "publication date must be in the past")
    @NotNull(message = "publication date cannot be null or empty")
    private LocalDate publicationDate;

    @DecimalMin(value = "1", message = "min value is 1")
    private BigDecimal price;

    @Min(value = 0, message = "quantity must be greater >= 0")
    private Integer quantityInStock;
}