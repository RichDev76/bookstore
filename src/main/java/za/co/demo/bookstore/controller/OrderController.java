package za.co.demo.bookstore.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.demo.bookstore.controller.api.OrderApi;
import za.co.demo.bookstore.domain.dto.*;
import za.co.demo.bookstore.service.OrderService;
import za.co.demo.bookstore.util.Utils;

@RestController
@RequestMapping("/bookstore/api/v1")
@Slf4j
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @Override
    @GetMapping(path = "/orders/{orderNumber}")
    public ResponseEntity<OrderResponseDto> getOrderByOrderNumber(@PathVariable long orderNumber) {
        log.info(String.format("-- Get Order By OrderNumber -- orderNumber ['%d']",orderNumber));
        return ResponseEntity.ok(orderService.getBookOrderResponseDtoByOrderNumber(orderNumber));
    }
    @Override
    @PostMapping("/orders")
    public ResponseEntity<?> createBookOrder(@Valid @RequestBody OrderDto bookOrder) {
        log.info("-- Create BookOrder -- ");
        log.info(Utils.toJson(bookOrder));
        CompleteOrderDto completeOrder = orderService.createBookOrder(bookOrder);
        return ResponseEntity.created(Utils.getLocationUri(completeOrder.getOrderNumber().toString())).build();
    }

    @Override
    @PutMapping("/orders/{orderNumber}")
    public ResponseEntity<OrderResponseDto> updateBook(@PathVariable(value = "orderNumber") Long orderNumber,
                                              @Valid @RequestBody OrderUpdateDto orderUpdateDto) {
        log.info(String.format("-- Update Order -- orderNumber ['%d']", orderNumber));
        log.info(Utils.toJson(orderUpdateDto));
        OrderResponseDto orderResponseDto = orderService.updateBookOrder(orderNumber, orderUpdateDto);
        return ResponseEntity.ok(orderResponseDto);
    }
}
