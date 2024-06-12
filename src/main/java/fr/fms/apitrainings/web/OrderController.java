package fr.fms.apitrainings.web;

import fr.fms.apitrainings.business.IBusinessImpl;
import fr.fms.apitrainings.entities.Customer;
import fr.fms.apitrainings.entities.Order;
import fr.fms.apitrainings.entities.OrderItem;
import fr.fms.apitrainings.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * Controller class for handling order-related requests.
 */
@CrossOrigin("*")
@RestController
public class OrderController {

    @Autowired
    private IBusinessImpl iBusiness;


    /**
     * Endpoint for saving a customer.
     *
     * @param customer the customer to save.
     * @return ResponseEntity containing the saved customer.
     */
    @PostMapping("/customers")
    public ResponseEntity<Customer> savCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = iBusiness.saveCustomer(customer);
        if (Objects.isNull(savedCustomer)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedCustomer);
    }

    /**
     * Endpoint for retrieving a customer by ID.
     *
     * @param id the ID of the customer to retrieve.
     * @return the retrieved customer.
     * @throws RecordNotFoundException if the customer with the given ID does not exist.
     */
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return iBusiness.getCustomerById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id de client " + id + " n'existe pas"));
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        return iBusiness.getOrderById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id de commande" + id + "n'exsite pas"));
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return iBusiness.getOrders();
    }

    /**
     * Endpoint for saving an order.
     *
     * @param order the order to save.
     * @return ResponseEntity containing the saved order.
     */
    @PostMapping("/orders")
    public ResponseEntity<Order> savCustomer(@RequestBody Order order) {
        Order savedOrder = iBusiness.saveOrder(order);
        if (Objects.isNull(savedOrder)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedOrder);
    }

    /**
     * Endpoint for saving an order item.
     *
     * @param orderItem the order item to save.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PostMapping("/ordersitems")
    public ResponseEntity<OrderItem> savCustomer(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = iBusiness.saveOrderItem(orderItem);
        if (Objects.isNull(savedOrderItem)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrderItem.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/ordersitems/{id}")
    public void deleteOrderItem(@PathVariable("id") Long id) {
        iBusiness.deleteOrderItemByOrderId(id);
    }


    /*ALE*/
    @GetMapping("/ordersitems/{id}")
    public List<OrderItem> getOrderItemById(@PathVariable("id") Long id) {
        return iBusiness.getOrderItemByOrderId(id);
    }


    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody String status){
        Order order = iBusiness.getOrderById(id).orElseThrow(()-> new RecordNotFoundException("Id de Formation " + id + " n'existe pas"));
        order.setStatus(status); // on s'assure que le status existe sur cette order

        Order updatedOrder = iBusiness.saveOrder(order);

        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrderById(@PathVariable("id") Long id) { iBusiness.deleteOrderById(id); }

}

