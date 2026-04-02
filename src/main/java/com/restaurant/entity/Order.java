package com.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private String status; //"PENDING", "COMPLETED", "CANCELLED"

    // Many orders can belong to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    //order can belong to many items
    @ManyToMany
    private List<Item> items;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

}
