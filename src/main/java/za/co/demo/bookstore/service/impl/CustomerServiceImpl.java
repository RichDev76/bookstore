package za.co.demo.bookstore.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.demo.bookstore.domain.dto.CustomerDto;
import za.co.demo.bookstore.domain.dto.CustomerUpdateDto;
import za.co.demo.bookstore.domain.mapper.GenericMapper;
import za.co.demo.bookstore.domain.model.Customer;
import za.co.demo.bookstore.exception.EntityNotFoundException;
import za.co.demo.bookstore.repository.CustomerRepository;
import za.co.demo.bookstore.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final GenericMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, GenericMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    private CustomerDto saveCustomer(Customer customer) {
        Customer createdCustomer = persistCustomer(customer);
        return this.mapper.mapCustomerToCustomerDto(createdCustomer);
    }

    @Override
    public Customer persistCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = this.mapper.mapCustomerDtoToCustomer(customerDto);
        return saveCustomer(customer);
    }

    @Override
    public CustomerDto getCustomerDtoByEmail(String email) {
        Customer customer = getCustomerByEmail(email);
        return this.mapper.mapCustomerToCustomerDto(customer);
    }
    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class , "email", email));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return customers.stream().map(this.mapper::mapCustomerToCustomerDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(String email, CustomerUpdateDto customerDetails) {
        //TODO refactor
        Customer customer = getCustomerByEmail(email);
        if(!StringUtils.isEmpty(customerDetails.getPhoneNumber())) {
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
        }

        if(!StringUtils.isEmpty(customerDetails.getAddress())) {
            customer.setAddress(customerDetails.getAddress());
        }

        return saveCustomer(customer);
    }
}
