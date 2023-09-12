package za.co.demo.bookstore.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "line_item")
public class LineItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id",  referencedColumnName = "id")
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_id",  referencedColumnName = "id")
    private Book book;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "order_price", nullable = false, precision = 5, scale = 2)
    private BigDecimal orderedPrice;

}