package za.co.demo.bookstore.domain.dto;

import lombok.*;
import za.co.demo.bookstore.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderResponseDto {

    private Long orderNumber;

    private String customerEmail;

    private LocalDate orderDate;

    private BigDecimal orderTotal;

    private OrderStatus orderStatus;

    private List<OrderLineItem> lineItems;

}