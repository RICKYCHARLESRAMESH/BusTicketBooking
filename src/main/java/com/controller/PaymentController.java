package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exception.CustomException;
import com.dao.BookingDAO;
import com.dao.CustomerDAO;
import com.dao.PaymentDAO;
import com.model.*;
import com.service.PaymentService;

import java.util.List;
import java.util.Optional;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingDAO bookingDAO;
    
    @Autowired
    private CustomerDAO customerDAO;
    
    @Autowired
    private PaymentDAO paymentDAO;

   

    // Get a payment by its ID
    @GetMapping("/{payment_id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer payment_id) {
        if (payment_id <= 0) {
            throw new CustomException("INVALID ID", "Invalid payment ID: " + payment_id);
        }
        Optional<Payment> payment = paymentService.getPaymentById(payment_id);
        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        } else {
            throw new CustomException("NOTFOUND", "Payment not found with ID: " + payment_id);
        }
    }

    // Get all payments
    @GetMapping("/")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        if (payments.isEmpty()) {
            throw new CustomException("NOTFOUND", "No payments found");
        }
        return ResponseEntity.ok(payments);
    }

    // Get payments by customer ID
    @GetMapping("/customer/{customer_id}")
    public ResponseEntity<List<Payment>> getPaymentsByCustomerId(@PathVariable Integer customer_id) {
        if (customer_id <= 0) {
            throw new CustomException("INVALIDID", "Invalid customer ID: " + customer_id);
        }
        List<Payment> payments = paymentService.getPaymentsByCustomerId(customer_id);
        if (payments.isEmpty()) {
            throw new CustomException("NOTFOUND", "No payments found for customer ID: " + customer_id);
        }
        return ResponseEntity.ok(payments);
    }

    // Get payments by payment status
    @GetMapping("/status/{paymentStatus}")
    public ResponseEntity<List<Payment>> getPaymentsByStatus(@PathVariable Payment.PaymentStatus paymentStatus) {
        if (paymentStatus == null) {
            throw new CustomException("INVALIDSTATUS", "Payment status cannot be null");
        }

        List<Payment> payments = paymentService.getPaymentsByStatus(paymentStatus);
        if (payments.isEmpty()) {
            throw new CustomException("NOTFOUND", "No payments found with status: " + paymentStatus);
        }

        return ResponseEntity.ok(payments);
    }

    // Get payment by booking ID
    @GetMapping("/booking/{booking_id}")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable Integer booking_id) {
        if (booking_id <= 0) {
            throw new CustomException("INVALIDID", "Invalid booking ID: " + booking_id);
        }
        Optional<Payment> payment = paymentService.getPaymentByBookingId(booking_id);
        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        } else {
            throw new CustomException("NOTFOUND", "Payment not found with booking ID: " + booking_id);
        }
    }
}
