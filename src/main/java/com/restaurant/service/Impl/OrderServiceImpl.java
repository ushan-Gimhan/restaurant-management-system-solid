package com.restaurant.service.Impl;


import com.restaurant.entity.Item;
import com.restaurant.entity.Order;
import com.restaurant.repository.ItemRepository;
import com.restaurant.repository.OrderRepository;
import com.restaurant.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Order addOrder(Order order) {
        // Check and update item quantity
        for (Item item : order.getItems()) {
            Item dbItem = itemRepository.findById(item.getId())
                    .orElseThrow(() -> new RuntimeException("Item not found: " + item.getId()));

            if (dbItem.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for item: " + dbItem.getName());
            }

            // Reduce item stock
            dbItem.setQuantity(dbItem.getQuantity() - item.getQuantity());
            itemRepository.save(dbItem);
        }

        // Save order
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
