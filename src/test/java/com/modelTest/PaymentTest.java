package com.modelTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Booking;
import com.model.Customer;
import com.model.Payment;

public class PaymentTest {

    private Payment payment;
    private Booking booking;
    private Customer customer;

    @BeforeEach
    public void setup() {
        // Mocks for the Booking and Customer classes
        booking = mock(Booking.class);
        customer = mock(Customer.class);

        // Creating a Payment instance
        payment = new Payment(
                1,  // paymentId
                booking,  // booking
                customer,  // customer
                BigDecimal.valueOf(150.75),  // amount
                LocalDateTime.of(2025, 1, 3, 10, 0),  // paymentDate
                Payment.PaymentStatus.Success  // paymentStatus
        );
    }

    @Test
    public void testGetPaymentId() {
        // Testing the getter for paymentId
        assertEquals(1, payment.getPaymentId());
    }

    @Test
    public void testSetPaymentId() {
        // Testing the setter for paymentId
        payment.setPaymentId(2);
        assertEquals(2, payment.getPaymentId());
    }

    @Test
    public void testGetBooking() {
        // Testing the getter for booking
        assertEquals(booking, payment.getBooking());
    }

    @Test
    public void testSetBooking() {
        // Testing the setter for booking
        Booking newBooking = mock(Booking.class);
        payment.setBooking(newBooking);
        assertEquals(newBooking, payment.getBooking());
    }

    @Test
    public void testGetCustomer() {
        // Testing the getter for customer
        assertEquals(customer, payment.getCustomer());
    }

    @Test
    public void testSetCustomer() {
        // Testing the setter for customer
        Customer newCustomer = mock(Customer.class);
        payment.setCustomer(newCustomer);
        assertEquals(newCustomer, payment.getCustomer());
    }

    @Test
    public void testGetAmount() {
        // Testing the getter for amount
        assertEquals(BigDecimal.valueOf(150.75), payment.getAmount());
    }

    @Test
    public void testSetAmount() {
        // Testing the setter for amount
        payment.setAmount(BigDecimal.valueOf(200.50));
        assertEquals(BigDecimal.valueOf(200.50), payment.getAmount());
    }

    @Test
    public void testGetPaymentDate() {
        // Testing the getter for paymentDate
        assertEquals(LocalDateTime.of(2025, 1, 3, 10, 0), payment.getPaymentDate());
    }

    @Test
    public void testSetPaymentDate() {
        // Testing the setter for paymentDate
        LocalDateTime newDate = LocalDateTime.of(2025, 1, 4, 12, 0);
        payment.setPaymentDate(newDate);
        assertEquals(newDate, payment.getPaymentDate());
    }

    @Test
    public void testGetPaymentStatus() {
        // Testing the getter for paymentStatus
        assertEquals(Payment.PaymentStatus.Success, payment.getPaymentStatus());
    }

    @Test
    public void testSetPaymentStatus() {
        // Testing the setter for paymentStatus
        payment.setPaymentStatus(Payment.PaymentStatus.Failed);
        assertEquals(Payment.PaymentStatus.Failed, payment.getPaymentStatus());
    }

    @Test
    public void testToString() {
        // Testing the toString() method
        String expectedString = "Payment [paymentId=1, booking=" + booking + ", customer=" + customer + 
            ", amount=150.75, paymentDate=2025-01-03T10:00, paymentStatus=Success]";
        assertEquals(expectedString, payment.toString());
    }
}
