package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Category;
import fr.fms.apitrainings.entities.Order;
import fr.fms.apitrainings.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Order entities.
 * Provides CRUD operations and other query methods for Order objects.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAll();

    public Optional<Order> findOrderById(Long id);
}
