package za.co.demo.bookstore.service;

import za.co.demo.bookstore.domain.dto.*;
import za.co.demo.bookstore.domain.model.Order;

import java.util.List;

public interface OrderService {

    Order getBookOrderByOrderNumber(Long orderNumber);

    Order saveOrder(Order order);

    OrderResponseDto getBookOrderResponseDtoByOrderNumber(Long orderNumber);

    List<OrderResponseDto> getBookOrderResponseDtoByCustomerDetails(String customerEmail, String orderDate);

    CompleteOrderDto createBookOrder(OrderDto bookOrder);

    OrderResponseDto updateBookOrder(Long orderNumber, OrderUpdateDto orderUpdateDto);

}
