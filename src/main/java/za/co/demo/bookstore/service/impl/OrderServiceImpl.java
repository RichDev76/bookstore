package za.co.demo.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.demo.bookstore.domain.dto.*;
import za.co.demo.bookstore.domain.mapper.GenericMapper;
import za.co.demo.bookstore.domain.model.Book;
import za.co.demo.bookstore.domain.model.Customer;
import za.co.demo.bookstore.domain.model.LineItem;
import za.co.demo.bookstore.domain.model.Order;
import za.co.demo.bookstore.enums.OrderStatus;
import za.co.demo.bookstore.exception.EntityNotFoundException;
import za.co.demo.bookstore.exception.InsufficientStockException;
import za.co.demo.bookstore.repository.BookOrderRepository;
import za.co.demo.bookstore.service.BookService;
import za.co.demo.bookstore.service.CustomerService;
import za.co.demo.bookstore.service.OrderService;
import za.co.demo.bookstore.util.Utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerService customerService;

    private final BookService bookService;

    private final BookOrderRepository bookOrderRepository;

    private final GenericMapper mapper;

    @Autowired
    public OrderServiceImpl(CustomerService customerService,
                            BookService bookService,
                            BookOrderRepository bookOrderRepository,
                            GenericMapper mapper){
        this.customerService = customerService;
        this.bookService = bookService;
        this.bookOrderRepository = bookOrderRepository;
        this.mapper = mapper;
    }

    @Override
    public Order getBookOrderByOrderNumber(Long orderNumber) {
        return bookOrderRepository.findOrdersByOrderNumber(orderNumber)
                .orElseThrow(() -> new EntityNotFoundException(Order.class , "orderNumber", orderNumber.toString()));
    }

    @Override
    public OrderResponseDto getBookOrderResponseDtoByOrderNumber(Long orderNumber) {
        return this.mapper.mapOrderToOrderResponseDto(getBookOrderByOrderNumber(orderNumber));
    }

    @Override
    public Order saveOrder(Order order) {
        return bookOrderRepository.save(order);
    }

    @Override
    public CompleteOrderDto createBookOrder(OrderDto order) {
        return this.mapper.mapBookOrderToCompletedOrderDto(processBookOrder(order));
    }

    @Override
    public OrderResponseDto updateBookOrder(Long orderNumber, OrderUpdateDto orderUpdateDto) {
        Order order = getBookOrderByOrderNumber(orderNumber);
        if(orderUpdateDto.getOrderStatus() != null) {
            order.setOrderStatus(orderUpdateDto.getOrderStatus());
            order.setDateUpdated(Instant.now());
        }
        return mapper.mapOrderToOrderResponseDto(saveOrder(order));
    }

    private Order processBookOrder(OrderDto order) {
        Customer customer = this.customerService.getCustomerByEmail(order.getCustomerEmail());
        List<LineItem> lineItems = new ArrayList<>();
        LineItem createdOrderLineItem;
        BigDecimal orderTotal = new BigDecimal(0);
        for(OrderLineItem orderlineItem : order.getOrderItems()) {
            Book book = this.bookService.getBookByIsbn(orderlineItem.getIsbn());
            validateOrderLineItem(orderlineItem, book);
            createdOrderLineItem = createOrderedLineItem(orderlineItem, book);
            lineItems.add(createdOrderLineItem);
            updateBookStock(orderlineItem, book);
            orderTotal = orderTotal.add(getLineItemTotal(book.getPrice(), orderlineItem.getQuantity()));
        }

        return saveOrder(buildOrder(customer, lineItems, OrderStatus.CREATED, orderTotal));
    }

    private static LineItem createOrderedLineItem(OrderLineItem lineItem, Book book) {
        return LineItem.builder()
                .book(book)
                .quantity(lineItem.getQuantity())
                .orderedPrice(lineItem.getPrice())
                .build();
    }

    private void updateBookStock(OrderLineItem lineItem, Book book) {
        book.setQuantityInStock(book.getQuantityInStock() - lineItem.getQuantity());
        bookService.saveBook(book);
    }

    private static void validateOrderLineItem(OrderLineItem lineItem, Book book) {
        validateOrderQuantity(lineItem, book);
        validateLineItemPrice(lineItem, book);
    }

    private static void validateLineItemPrice(OrderLineItem lineItem, Book book) {
        if(book.getPrice().compareTo(lineItem.getPrice()) != 0){
            throw new InsufficientStockException(String.format("Invalid item price specified for item with isbn :: %s .", book.getIsbn()));
        }
    }

    private static void validateOrderQuantity(OrderLineItem lineItem, Book book) {
        if(book.getQuantityInStock() < lineItem.getQuantity()){
            throw new InsufficientStockException(String.format("Insufficient quantity of books with isbn :: %s in stock.", book.getIsbn()));
        }
    }

    private static BigDecimal getLineItemTotal(BigDecimal price, long quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    private static Order buildOrder(Customer customer, List<LineItem> lineItems,
                                    OrderStatus orderStatus, BigDecimal orderTotal) {
        return Order.builder()
                .orderDate(Timestamp.from(Instant.now()).toInstant())
                .orderNumber(Utils.random())
                .customer(customer)
                .orderTotal(orderTotal)
                .orderStatus(orderStatus)
                .lineItems(lineItems)
                .build();
    }
}
