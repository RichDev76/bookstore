package za.co.demo.bookstore.service;

import za.co.demo.bookstore.domain.dto.CompleteOrderDto;
import za.co.demo.bookstore.domain.dto.OrderDto;
import za.co.demo.bookstore.domain.dto.OrderResponseDto;
import za.co.demo.bookstore.domain.model.Order;

public interface OrderService {

    Order getBookOrderByOrderNumber(Long orderNumber);

    OrderResponseDto getBookOrderResponseDtoByOrderNumber(Long orderNumber);

    CompleteOrderDto createBookOrder(OrderDto bookOrder);

}
