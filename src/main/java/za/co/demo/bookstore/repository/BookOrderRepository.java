package za.co.demo.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.demo.bookstore.domain.model.Order;

import java.util.Optional;

@Repository
public interface BookOrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findOrdersByOrderNumber(Long orderNumber);
}
