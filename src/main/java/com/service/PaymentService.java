package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PaymentDAO;
import com.model.Payment;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    // Save or update a payment
    public Payment saveOrUpdatePayment(Payment payment) {
        return paymentDAO.save(payment);
    }

    // Find a payment by ID
    public Optional<Payment> getPaymentById(Integer paymentId) {
        return paymentDAO.findById(paymentId);
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentDAO.findAll();
    }

    // Delete a payment by ID
    public void deletePayment(Integer paymentId) {
        paymentDAO.deleteById(paymentId);
    }
}
