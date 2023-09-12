package za.co.demo.bookstore.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.demo.bookstore.domain.dto.CompleteOrderDto;
import za.co.demo.bookstore.domain.dto.OrderDto;
import za.co.demo.bookstore.domain.dto.OrderResponseDto;
import za.co.demo.bookstore.service.OrderService;

@RestController
@RequestMapping("/bookstore/api/v1")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/orders/{orderNumber}")
    public @ResponseBody OrderResponseDto getOrderByOrderNumber(@PathVariable long orderNumber) {
        return orderService.getBookOrderResponseDtoByOrderNumber(orderNumber);
    }

    @PostMapping("/orders")
    public @ResponseBody CompleteOrderDto createBookOrder(@Valid @RequestBody OrderDto bookOrder) {
        return orderService.createBookOrder(bookOrder);
    }
}
