package com.restaurant.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private double amount;

    private String paymentMethod; // CASH, CARD, ONLINE

    private LocalDateTime paymentDate;

    // 🔗 Relationship with Order
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
