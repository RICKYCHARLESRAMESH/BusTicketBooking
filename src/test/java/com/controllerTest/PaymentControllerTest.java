//package com.controllerTest;
// 
//import com.controller.PaymentController;
//import com.model.Payment;
//import com.service.PaymentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
// 
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
// 
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
// 
//class PaymentControllerTest {
// 
//    @InjectMocks
//    private PaymentController paymentController;
// 
//    @Mock
//    private PaymentService paymentService;
// 
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
// 
//    @Test
//    void testCreatePayment() {
//        Payment payment = new Payment(); // Create a Payment instance
//        doNothing().when(paymentService).createPayment(any());
// 
//        ResponseEntity<String> response = paymentController.createPayment(payment);
// 
//        assertEquals("Payment record created successfully", response.getBody());
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        verify(paymentService, times(1)).createPayment(payment);
//    }
// 
//    @Test
//    void testGetPaymentByIdFound() {
//        Payment payment = new Payment(); // Create a Payment instance
//        when(paymentService.getPaymentById(anyInt())).thenReturn(Optional.of(payment));
// 
//        ResponseEntity<Payment> response = paymentController.getPaymentById(1);
// 
//        assertEquals(payment, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(paymentService, times(1)).getPaymentById(1);
//    }
// 
//    @Test
//    void testGetPaymentByIdNotFound() {
//        when(paymentService.getPaymentById(anyInt())).thenReturn(Optional.empty());
// 
//        ResponseEntity<Payment> response = paymentController.getPaymentById(1);
// 
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(paymentService, times(1)).getPaymentById(1);
//    }
// 
//    @Test
//    void testGetAllPayments() {
//        Payment payment1 = new Payment();
//        Payment payment2 = new Payment();
//        List<Payment> payments = Arrays.asList(payment1, payment2);
//        when(paymentService.getAllPayments()).thenReturn(payments);
// 
//        ResponseEntity<List<Payment>> response = paymentController.getAllPayments();
// 
//        assertEquals(2, response.getBody().size());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(paymentService, times(1)).getAllPayments();
//    }
// 
//    @Test
//    void testGetPaymentsByCustomerId() {
//        Payment payment = new Payment();
//        List<Payment> payments = Arrays.asList(payment);
//        when(paymentService.getPaymentsByCustomerId(anyInt())).thenReturn(payments);
// 
//        ResponseEntity<List<Payment>> response = paymentController.getPaymentsByCustomerId(1);
// 
//        assertEquals(1, response.getBody().size());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(paymentService, times(1)).getPaymentsByCustomerId(1);
//    }
// 
//    @Test
//    void testGetPaymentByBookingIdFound() {
//        Payment payment = new Payment();
//        when(paymentService.getPaymentByBookingId(anyInt())).thenReturn(Optional.of(payment));
// 
//        ResponseEntity<Payment> response = paymentController.getPaymentByBookingId(1);
// 
//        assertEquals(payment, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(paymentService, times(1)).getPaymentByBookingId(1);
//    }
// 
//    @Test
//    void testGetPaymentByBookingIdNotFound() {
//        when(paymentService.getPaymentByBookingId(anyInt())).thenReturn(Optional.empty());
// 
//        ResponseEntity<Payment> response = paymentController.getPaymentByBookingId(1);
// 
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(paymentService, times(1)).getPaymentByBookingId(1);
//    }
//}
// 