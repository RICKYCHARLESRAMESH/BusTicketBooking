package com.DaoTest;
 
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
 
import com.dao.PaymentDAO;

import com.model.Booking;

import com.model.Customer;

import com.model.Payment;

import com.model.Payment.PaymentStatus;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
 
import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.util.List;

import java.util.Optional;
 
@ExtendWith(MockitoExtension.class) // Use Mockito extension for JUnit 5

public class PaymentDAOTest {
 
    @Mock

    private PaymentDAO paymentDAO;
 
    private Payment payment;
 
    @BeforeEach

    public void setUp() {

        // Create sample data

        Booking booking = new Booking();

        booking.setBookingId(1);
 
        Customer customer = new Customer();

        customer.setCustomerId(1);
 
        payment = new Payment();

        payment.setPaymentId(1);

        payment.setBooking(booking);

        payment.setCustomer(customer);

        payment.setAmount(new BigDecimal("100.00"));

        payment.setPaymentDate(LocalDateTime.now());

        payment.setPaymentStatus(PaymentStatus.Success);

    }
 
    @Test

    public void testFindByCustomerId() {

        // Mock DAO behavior

        when(paymentDAO.findByCustomer_Id(1)).thenReturn(List.of(payment));
 
        // Test method

        List<Payment> payments = paymentDAO.findByCustomer_Id(1);
 
        // Verify results

        assertNotNull(payments);

        assertEquals(1, payments.size());

        assertEquals(payment.getPaymentId(), payments.get(0).getPaymentId());

        assertEquals(payment.getCustomer().getCustomerId(), payments.get(0).getCustomer().getCustomerId());
 
        // Verify mock interaction

        verify(paymentDAO, times(1)).findByCustomer_Id(1);

    }
 
    @Test

    public void testFindByBookingId() {

        // Mock DAO behavior

        when(paymentDAO.findByBooking_BookingId(1)).thenReturn(Optional.of(payment));
 
        // Test method

        Optional<Payment> retrievedPayment = paymentDAO.findByBooking_BookingId(1);
 
        // Verify results

        assertTrue(retrievedPayment.isPresent());

        assertEquals(payment.getPaymentId(), retrievedPayment.get().getPaymentId());

        assertEquals(payment.getBooking().getBookingId(), retrievedPayment.get().getBooking().getBookingId());
 
        // Verify mock interaction

        verify(paymentDAO, times(1)).findByBooking_BookingId(1);

    }
 
    @Test

    public void testSavePayment() {

        // Mock DAO behavior

        when(paymentDAO.save(payment)).thenReturn(payment);
 
        // Test method

        Payment savedPayment = paymentDAO.save(payment);
 
        // Verify results

        assertNotNull(savedPayment);

        assertEquals(payment.getPaymentId(), savedPayment.getPaymentId());

        assertEquals(payment.getAmount(), savedPayment.getAmount());
 
        // Verify mock interaction

        verify(paymentDAO, times(1)).save(payment);

    }
 
    @Test

    public void testDeletePayment() {

        // Test method

        paymentDAO.deleteById(payment.getPaymentId());
 
        // Verify mock interaction

        verify(paymentDAO, times(1)).deleteById(payment.getPaymentId());

    }

}

 