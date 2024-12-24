package com.serviceTest;

import com.dao.PaymentDAO;
import com.model.Payment;
import com.model.Payment.PaymentStatus;
import com.service.PaymentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PaymentDAO paymentRepo;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment(1, null, null, BigDecimal.valueOf(100.50), new Date(), Payment.PaymentStatus.Success);
        when(paymentRepo.save(payment)).thenReturn(payment); // Mock the save method to return the same payment

        paymentService.createPayment(payment);

        verify(paymentRepo, times(1)).save(payment); // Verify the save method was called once
    }


    @Test
    void testGetPaymentById() {
        Payment payment = new Payment(1, null, null, BigDecimal.valueOf(100.50), new Date(), PaymentStatus.Success);
        when(paymentRepo.findById(1)).thenReturn(Optional.of(payment));

        Optional<Payment> result = paymentService.getPaymentById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getPaymentId());
        verify(paymentRepo, times(1)).findById(1);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = Arrays.asList(
                new Payment(1, null, null, BigDecimal.valueOf(100.50), new Date(), PaymentStatus.Success),
                new Payment(2, null, null, BigDecimal.valueOf(200.75), new Date(), PaymentStatus.Failed)
        );
        when(paymentRepo.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(paymentRepo, times(1)).findAll();
    }

    @Test
    void testGetPaymentsByCustomerId() {
        List<Payment> payments = Arrays.asList(
                new Payment(1, null, null, BigDecimal.valueOf(100.50), new Date(), PaymentStatus.Success),
                new Payment(2, null, null, BigDecimal.valueOf(200.75), new Date(), PaymentStatus.Failed)
        );
        when(paymentRepo.findByCustomer_Id(1)).thenReturn(payments);

        List<Payment> result = paymentService.getPaymentsByCustomerId(1);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(paymentRepo, times(1)).findByCustomer_Id(1);
    }

    @Test
    void testGetPaymentByBookingId() {
        Payment payment = new Payment(1, null, null, BigDecimal.valueOf(100.50), new Date(), PaymentStatus.Success);
        when(paymentRepo.findByBooking_BookingId(1)).thenReturn(Optional.of(payment));

        Optional<Payment> result = paymentService.getPaymentByBookingId(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getPaymentId());
        verify(paymentRepo, times(1)).findByBooking_BookingId(1);
    }
}
