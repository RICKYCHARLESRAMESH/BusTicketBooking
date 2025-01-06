package com.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.exception.CustomException;

import com.model.*;
import com.service.PaymentService;
 
import java.util.List;
import java.util.Optional;
 
/**
* PaymentController is a REST controller that manages payment-related operations,
* providing endpoints for retrieving payments by various criteria.
*/
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
 
    @Autowired
    private PaymentService paymentService;
 
   
    /**
     * Retrieves a payment by its ID.
     *
     * @param payment_id The ID of the payment to retrieve.
     * @return ResponseEntity containing the Payment object if found.
     * @throws CustomException if the payment ID is invalid or not found.
     */
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
 
    /**
     * Retrieves all payments.
     *
     * @return ResponseEntity containing a list of all Payment objects.
     * @throws CustomException if no payments are found.
     */
    @GetMapping("/")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        if (payments.isEmpty()) {
            throw new CustomException("NOTFOUND", "No payments found");
        }
        return ResponseEntity.ok(payments);
    }
 
    /**
     * Retrieves payments by customer ID.
     *
     * @param customer_id The ID of the customer whose payments to retrieve.
     * @return ResponseEntity containing a list of Payment objects for the specified customer.
     * @throws CustomException if the customer ID is invalid or no payments are found.
     */
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
 
    /**
     * Retrieves payments by payment status.
     *
     * @param paymentStatus The status of the payments to retrieve.
     * @return ResponseEntity containing a list of Payment objects with the specified status.
     * @throws CustomException if the payment status is invalid or no payments are found.
     */
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
 
    /**
     * Retrieves a payment by booking ID.
     *
     * @param booking_id The ID of the booking associated with the payment.
     * @return ResponseEntity containing the Payment object if found.
     * @throws CustomException if the booking ID is invalid or no payment is found.
     */
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
