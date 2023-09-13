package za.co.demo.bookstore.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import za.co.demo.bookstore.domain.dto.CustomerDto;
import za.co.demo.bookstore.domain.dto.CustomerUpdateDto;

import java.util.List;

@Tag(name = "Customer", description = "The Customer Api")
public interface CustomerApi {
    @Operation(
            summary = "gets all customers",
            description = "Returns a list of all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<List<CustomerDto>> getAllCustomers();

    @Operation(
            summary = "find a customer",
            description = "returns a customer based on the supplied email address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "no customer found")
    })
    ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email);

    @Operation(
            summary = "adds a customer",
            description = "Adds a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully added a customer"),
            @ApiResponse(responseCode = "409", description = "duplicate customer")
    })
    ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto Customer);

    @Operation(
            summary = "update customer details",
            description = "Updates some customer details such as customer's phone number and/or address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully updated customer details"),
            @ApiResponse(responseCode = "404", description = "no customer found")
    })
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable(value = "email") String email,
                                               @Valid @RequestBody CustomerUpdateDto customerDetails);
}
