package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Payment;
import com.service.PaymentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Create a new payment
    @PostMapping("/add")
    public ResponseEntity<String> createPayment(@RequestBody Payment payment) {
        paymentService.createPayment(payment);  // Call service to save the payment
        return ResponseEntity.status(HttpStatus.CREATED).body("Payment record created successfully");
    }

    // Get a payment by its ID
    @GetMapping("/{payment_id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer payment_id) {
        Optional<Payment> payment = paymentService.getPaymentById(payment_id);
        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get all payments
    @GetMapping("/")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    // Get payments by customer ID
    @GetMapping("/customer/{customer_id}")
    public ResponseEntity<List<Payment>> getPaymentsByCustomerId(@PathVariable Integer customer_id) {
        List<Payment> payments = paymentService.getPaymentsByCustomerId(customer_id);
        return ResponseEntity.ok(payments);
    }

    // Get payments by booking ID
    @GetMapping("/booking/{booking_id}")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable Integer booking_id) {
        Optional<Payment> payment = paymentService.getPaymentByBookingId(booking_id);
        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
