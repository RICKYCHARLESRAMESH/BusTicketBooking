package com.controllerTest;

import com.controller.PaymentController;
import com.dao.BookingDAO;
import com.dao.CustomerDAO;
import com.dao.PaymentDAO;
import com.exception.CustomException;
import com.model.Booking;
import com.model.Customer;
import com.model.Payment;
import com.model.Payment.PaymentStatus;
import com.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private BookingDAO bookingDAO;

    @Mock
    private CustomerDAO customerDAO;

    @Mock
    private PaymentDAO paymentDAO;

    @InjectMocks
    private PaymentController paymentController;

    private Payment payment;
    private Booking booking;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        booking = new Booking();
        booking.setBookingId(1);

        customer = new Customer();
        customer.setId(1);

        payment = new Payment();
        payment.setPaymentId(1);
        payment.setBooking(booking);
        payment.setCustomer(customer);
        payment.setAmount(new BigDecimal("100.00"));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.Success);
    }

    @Test
    public void testCreatePayment_Success() {
        when(bookingDAO.getById(1)).thenReturn(booking);
        when(customerDAO.getById(1)).thenReturn(customer);
        when(paymentDAO.save(any(Payment.class))).thenReturn(payment);

        ResponseEntity<String> response = paymentController.createPayment(payment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Payment created successfully", response.getBody());
    }

    @Test
    public void testCreatePayment_BookingNotFound() {
        when(bookingDAO.getById(1)).thenReturn(null);

        ResponseEntity<String> response = paymentController.createPayment(payment);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Booking not found", response.getBody());
    }

    @Test
    public void testCreatePayment_CustomerNotFound() {
        when(bookingDAO.getById(1)).thenReturn(booking);
        when(customerDAO.getById(1)).thenReturn(null);

        ResponseEntity<String> response = paymentController.createPayment(payment);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Customer not found", response.getBody());
    }

    @Test
    public void testGetPaymentById_Success() {
        when(paymentService.getPaymentById(1)).thenReturn(Optional.of(payment));

        ResponseEntity<Payment> response = paymentController.getPaymentById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment.getPaymentId(), response.getBody().getPaymentId());
    }

    @Test
    public void testGetPaymentById_NotFound() {
        when(paymentService.getPaymentById(1)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> paymentController.getPaymentById(1));
    }

    @Test
    public void testGetAllPayments_Success() {
        when(paymentService.getAllPayments()).thenReturn(List.of(payment));

        ResponseEntity<List<Payment>> response = paymentController.getAllPayments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testGetAllPayments_NotFound() {
        when(paymentService.getAllPayments()).thenReturn(List.of());

        assertThrows(CustomException.class, () -> paymentController.getAllPayments());
    }

    @Test
    public void testGetPaymentsByCustomerId_Success() {
        when(paymentService.getPaymentsByCustomerId(1)).thenReturn(List.of(payment));

        ResponseEntity<List<Payment>> response = paymentController.getPaymentsByCustomerId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testGetPaymentsByCustomerId_NotFound() {
        when(paymentService.getPaymentsByCustomerId(1)).thenReturn(List.of());

        assertThrows(CustomException.class, () -> paymentController.getPaymentsByCustomerId(1));
    }

    @Test
    public void testGetPaymentsByStatus_Success() {
        when(paymentService.getPaymentsByStatus(PaymentStatus.Success)).thenReturn(List.of(payment));

        ResponseEntity<List<Payment>> response = paymentController.getPaymentsByStatus(PaymentStatus.Success);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testGetPaymentsByStatus_NotFound() {
        when(paymentService.getPaymentsByStatus(PaymentStatus.Success)).thenReturn(List.of());

        assertThrows(CustomException.class, () -> paymentController.getPaymentsByStatus(PaymentStatus.Success));
    }

    @Test
    public void testGetPaymentByBookingId_Success() {
        when(paymentService.getPaymentByBookingId(1)).thenReturn(Optional.of(payment));

        ResponseEntity<Payment> response = paymentController.getPaymentByBookingId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment.getPaymentId(), response.getBody().getPaymentId());
    }

    @Test
    public void testGetPaymentByBookingId_NotFound() {
        when(paymentService.getPaymentByBookingId(1)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> paymentController.getPaymentByBookingId(1));
    }
}
