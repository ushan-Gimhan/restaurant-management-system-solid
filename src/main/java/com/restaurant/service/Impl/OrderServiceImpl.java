package com.restaurant.service.Impl;


import com.restaurant.entity.Item;
import com.restaurant.entity.Order;
import com.restaurant.entity.Payment;
import com.restaurant.repository.ItemRepository;
import com.restaurant.repository.OrderRepository;
import com.restaurant.repository.PaymentRepository;
import com.restaurant.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public Order addOrder(Order order) {

        double totalAmount = 0;

        // 🔹 Check and update item quantity
        for (Item item : order.getItems()) {

            Item dbItem = itemRepository.findById(item.getId())
                    .orElseThrow(() -> new RuntimeException("Item not found: " + item.getId()));

            if (dbItem.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for item: " + dbItem.getName());
            }

            // 🔹 Reduce stock
            dbItem.setQuantity(dbItem.getQuantity() - item.getQuantity());
            itemRepository.save(dbItem);

            // 🔹 Calculate total
            totalAmount += dbItem.getPrice() * item.getQuantity();
        }

        // 🔹 Save Order first
        Order savedOrder = orderRepository.save(order);

        // 🔹 Create Payment
        Payment payment = Payment.builder()
                .amount(totalAmount)
                .paymentMethod("CASH") // you can pass dynamically later
                .paymentDate(LocalDateTime.now())
                .order(savedOrder)
                .build();

        paymentRepository.save(payment);

        return savedOrder;
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
