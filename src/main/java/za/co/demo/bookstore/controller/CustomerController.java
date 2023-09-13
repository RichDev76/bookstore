package za.co.demo.bookstore.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.demo.bookstore.controller.api.CustomerApi;
import za.co.demo.bookstore.domain.dto.CustomerDto;
import za.co.demo.bookstore.domain.dto.CustomerUpdateDto;
import za.co.demo.bookstore.service.CustomerService;
import za.co.demo.bookstore.util.Utils;

import java.util.List;

@RestController
@RequestMapping("/bookstore/api/v1")
@Slf4j
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        log.info("-- Get All Customers --");
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    @GetMapping(path = "/customers/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email) {
        log.info(String.format("-- Get Customer By Email -- email ['%s']", email));
        return ResponseEntity.ok(customerService.getCustomerDtoByEmail(email));
    }

    @Override
    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto Customer) {
        log.info("-- Create Customer -- ");
        log.info(Utils.toJson(Customer));
        customerService.createCustomer(Customer);
        return ResponseEntity.created(Utils.getLocationUri(Customer.getEmail())).build();
    }

    @Override
    @PutMapping("/customers/{email}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(value = "email") String email,
                                            @Valid @RequestBody CustomerUpdateDto customerDetails) {
        log.info(String.format("-- Update Customer -- email ['%s']", email));
        return ResponseEntity.ok(customerService.updateCustomer(email, customerDetails));
    }
}
