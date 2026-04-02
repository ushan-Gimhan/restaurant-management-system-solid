package com.restaurant.service;

import com.restaurant.model.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO savePayment(PaymentDTO dto);

    List<PaymentDTO> getAllPayments();

    PaymentDTO getPaymentById(Long id);

    void deletePayment(Long id);
}
