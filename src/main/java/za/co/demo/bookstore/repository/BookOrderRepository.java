package za.co.demo.bookstore.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.demo.bookstore.domain.model.Customer;
import za.co.demo.bookstore.domain.model.Order;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookOrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findOrdersByOrderNumber(Long orderNumber);

    Optional<List<Order>> findOrdersByCustomer(Customer customer);

    Optional<List<Order>> findOrdersByCustomerAndOrderDate(Customer customer, Instant orderDate);
}
