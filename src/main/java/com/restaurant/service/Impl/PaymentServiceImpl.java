package com.restaurant.service.Impl;

import com.restaurant.entity.Order;
import com.restaurant.entity.Payment;
import com.restaurant.model.PaymentDTO;
import com.restaurant.repository.OrderRepository;
import com.restaurant.repository.PaymentRepository;
import com.restaurant.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public PaymentDTO savePayment(PaymentDTO dto) {

        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = Payment.builder()
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .paymentDate(LocalDateTime.now())
                .order(order)
                .build();

        Payment saved = paymentRepository.save(payment);

        return new PaymentDTO(
                saved.getPaymentId(),
                saved.getAmount(),
                saved.getPaymentMethod(),
                saved.getOrder().getId()
        );
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream().map(p ->
                new PaymentDTO(
                        p.getPaymentId(),
                        p.getAmount(),
                        p.getPaymentMethod(),
                        p.getOrder().getId()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return new PaymentDTO(
                p.getPaymentId(),
                p.getAmount(),
                p.getPaymentMethod(),
                p.getOrder().getId()
        );
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
