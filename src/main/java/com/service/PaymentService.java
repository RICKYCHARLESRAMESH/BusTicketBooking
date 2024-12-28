package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookingDAO;
import com.dao.*;
import com.model.Booking;
import com.model.Payment;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentDAO paymentRepo;
    
    @Autowired
    private BookingDAO bookingDAO;
    
    @Autowired
    private CustomerDAO customerDAO;

    // Create a new payment
    public void createPayment(Payment payment) {
    	Booking existingBooking = bookingDAO.findById(payment.getBooking().getBookingId()).get();
    	Payment savePayment = new Payment();
    	savePayment.setCustomer(customerDAO.findById(payment.getCustomer().getId()).get());
    	savePayment.setAmount(payment.getAmount());
    	savePayment.setBooking(existingBooking);
        paymentRepo.save(payment);  // Save the payment to the repository
    }

    // Get a payment by its ID
    public Optional<Payment> getPaymentById(Integer paymentId) {
        return paymentRepo.findById(paymentId);  // Get payment by ID
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();  // Get all payments
    }

    // Get payments by customer ID
    public List<Payment> getPaymentsByCustomerId(Integer customerId) {
        return paymentRepo.findByCustomer_Id(customerId);  // Custom query to find payments by customer ID
    }

    // Get payments by booking ID
    public Optional<Payment> getPaymentByBookingId(Integer bookingId) {
        return paymentRepo.findByBooking_BookingId(bookingId);  // Custom query to find payments by booking ID
    }
    
  
}
