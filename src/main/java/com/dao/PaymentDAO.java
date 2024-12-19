package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Payment;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentDAO extends JpaRepository<Payment, Integer> {

    // Find payments by customer ID
    List<Payment> findByCustomer_Id(Integer customerId);

    // Find payments by booking ID
    Optional<Payment> findByBooking_BookingId(Integer bookingId);
}
