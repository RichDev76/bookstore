package za.co.demo.bookstore.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.demo.bookstore.domain.dto.CustomerDto;
import za.co.demo.bookstore.domain.dto.CustomerUpdateDto;
import za.co.demo.bookstore.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/bookstore/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

//    @GetMapping(path = "/customers/{id}")
//    public @ResponseBody CustomerDto getCustomer(@PathVariable long id) {
//        return customerService.getCustomerById(id);
//    }

    @GetMapping(path = "/customers/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerDtoByEmail(email));
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto Customer) {
        return ResponseEntity.ok(customerService.createCustomer(Customer));
    }

    @PutMapping("/customers/{email}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(value = "email") String email,
                                            @Valid @RequestBody CustomerUpdateDto customerDetails) {
        return ResponseEntity.ok(customerService.updateCustomer(email, customerDetails));
    }
}
