package za.co.demo.bookstore.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static org.hibernate.validator.constraints.ISBN.Type.ANY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ISBN(type = ANY)
    private String isbn;

    @NotBlank(message = "title cannot be null or empty")
    private String title;

    @NotBlank(message = "title cannot be null or empty")
    private String author;

    @Min(value = 1, message = "number of pages must be greater >= 0")
    private long quantity;

    private BigDecimal price;
}
