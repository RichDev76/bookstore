package za.co.demo.bookstore.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.demo.bookstore.domain.dto.*;
import za.co.demo.bookstore.domain.model.Book;
import za.co.demo.bookstore.domain.model.Customer;
import za.co.demo.bookstore.domain.model.LineItem;
import za.co.demo.bookstore.domain.model.Order;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class GenericMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public GenericMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public BookDto mapBookToBookDto(Book book){
        return modelMapper.map(book, BookDto.class);
    }

    public Book mapBookDtoToBook(BookDto bookDto){
        return modelMapper.map(bookDto, Book.class);
    }

    public CustomerDto mapCustomerToCustomerDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }

    public Customer mapCustomerDtoToCustomer(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }

    public OrderResponseDto mapOrderToOrderResponseDto(Order bookOrder){
        OrderResponseDto orderResponseDto = modelMapper.map(bookOrder, OrderResponseDto.class);
        orderResponseDto.setOrderDate(LocalDate.ofInstant(bookOrder.getOrderDate(), ZoneId.systemDefault()));
        orderResponseDto.setLineItems(getOrderLineItems(bookOrder));
        return orderResponseDto;
    }

    private static List<OrderLineItem> getOrderLineItems(Order bookOrder) {
        List<OrderLineItem> orderLineItems = new ArrayList<>();
        for (LineItem orderedLineItem: bookOrder.getLineItems()) {
            Book book = orderedLineItem.getBook();
            OrderLineItem orderLineItem = mapOrderLineItem(orderedLineItem, book);
            orderLineItems.add(orderLineItem);
        }
        return orderLineItems;
    }

    private static OrderLineItem mapOrderLineItem(LineItem orderedLineItem, Book book) {
        return OrderLineItem.builder()
             .isbn(book.getIsbn())
             .title(book.getTitle())
             .price(orderedLineItem.getOrderedPrice())
             .quantity(orderedLineItem.getQuantity())
             .build();
    }

    public CompleteOrderDto mapBookOrderToCompletedOrderDto(Order bookOrder){
        return modelMapper.map(bookOrder, CompleteOrderDto.class);
    }
}
