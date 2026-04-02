package com.restaurant.service;

import com.restaurant.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    //place a order
    Order addOrder(Order order);

    //updata a order
    Order updateOrder(Order order);

    //delete a order
    void deleteOrder(Long id);

    //get All orders
    List<Order> getAllOrders();

    //get order by Id
    Optional<Order> getOrderById(Long id);

}
