package za.co.demo.bookstore.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookUpdateDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @DecimalMin(value = "1", message = "min value is 1")
    private BigDecimal price;

    @Min(value = 0, message = "quantity must be greater >= 0")
    private Long quantityInStock;
}