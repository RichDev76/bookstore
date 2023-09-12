package za.co.demo.bookstore.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Email(message = "Email is not valid")
    private String customerEmail;

    private LocalDate orderDate;

    @NotEmpty(message = "order item count must be > 0")
    private List<OrderLineItem> orderItems;
}
