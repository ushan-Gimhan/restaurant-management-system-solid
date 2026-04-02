package com.restaurant.controller;

import com.restaurant.model.PaymentDTO;
import com.restaurant.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentDTO save(@RequestBody PaymentDTO dto) {
        return paymentService.savePayment(dto);
    }

    @GetMapping
    public List<PaymentDTO> getAll() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public PaymentDTO getById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
