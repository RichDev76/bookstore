package za.co.demo.bookstore.service;

import za.co.demo.bookstore.domain.dto.CustomerDto;
import za.co.demo.bookstore.domain.dto.CustomerUpdateDto;
import za.co.demo.bookstore.domain.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer persistCustomer(Customer customer);

    CustomerDto createCustomer(CustomerDto Customer);

    CustomerDto getCustomerDtoByEmail(String email);

    Customer getCustomerByEmail(String email);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomer(String email, CustomerUpdateDto CustomerDetails);
}
