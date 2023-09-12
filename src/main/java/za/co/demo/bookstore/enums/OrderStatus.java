package za.co.demo.bookstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATED,
    CANCELLED,
    FULFILLED,
    DELIVERED,
    IN_TRANSIT,
    PROBLEM,
    PROCESSING,
    RETURNED,
    SHIPPED
}
