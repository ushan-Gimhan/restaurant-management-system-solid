package com.restaurant.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long paymentId;
    private double amount;
    private String paymentMethod;
    private Long orderId;
}
