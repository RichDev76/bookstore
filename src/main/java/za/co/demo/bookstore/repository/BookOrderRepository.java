package za.co.demo.bookstore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.co.demo.bookstore.domain.model.Customer;
import za.co.demo.bookstore.domain.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookOrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findOrdersByOrderNumber(Long orderNumber);

    Optional<List<Order>> findOrdersByCustomer(Customer customer);
    @Query(nativeQuery = true, value = "SELECT * FROM orders o " +
            "where o.customer_id=:customerId and CAST(o.order_date as DATE) = :orderDate")
    Optional<List<Order>> findOrdersByCustomerAndOrderDate(@Param("customerId")long customerId,
                                                 @Param("orderDate") LocalDate orderDate);
}
