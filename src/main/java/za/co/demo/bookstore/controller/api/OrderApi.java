package za.co.demo.bookstore.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import za.co.demo.bookstore.domain.dto.OrderDto;
import za.co.demo.bookstore.domain.dto.OrderResponseDto;
import za.co.demo.bookstore.domain.dto.OrderUpdateDto;

@Tag(name = "Order", description = "The Order Api")
public interface OrderApi {

    @Operation(
            summary = "find a book",
            description = "returns a book based on the supplied isbn")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "no book found")
    })
    ResponseEntity<OrderResponseDto> getOrderByOrderNumber(@PathVariable long orderNumber);

    @Operation(
            summary = "create a book order",
            description = "creates a new order of books for a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully created the order"),
            @ApiResponse(responseCode = "400", description = "a book price is invalid"),
            @ApiResponse(responseCode = "404", description = "a book in the order could not be found"),
            @ApiResponse(responseCode = "404", description = "customer placing order could not be found"),
            @ApiResponse(responseCode = "500", description = "insufficient stock available"),

    })
    ResponseEntity<?> createBookOrder(@Valid @RequestBody OrderDto bookOrder);

    @Operation(
            summary = "update order details",
            description = "Updates the status of the order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully updated order status"),
            @ApiResponse(responseCode = "404", description = "no orders found")
    })
    ResponseEntity<OrderResponseDto> updateBook(@PathVariable(value = "orderNumber") Long orderNumber,
                                                @Valid @RequestBody OrderUpdateDto orderUpdateDto);

}
