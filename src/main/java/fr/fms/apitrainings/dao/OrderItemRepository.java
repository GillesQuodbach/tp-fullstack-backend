package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for OrderItem entities.
 * Provides CRUD operations and other query methods for OrderItem objects.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long id);
    void deleteByOrderId(Long orderId);
}
