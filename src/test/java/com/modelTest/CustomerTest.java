package com.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.model.Address;
import com.model.Customer;
import com.model.Payment;

public class CustomerTest {

    @Test
    void testDefaultConstructor() {
        Customer customer = new Customer();
        assertNotNull(customer, "The Customer object should be created successfully.");
    }

    @Test
    void testParameterizedConstructor() {
        Address address = new Address(1, "123 Street", "City", "State", "12345", "Country");
        Payment payment = new Payment();
        Customer customer = new Customer(1, "John Doe", "john.doe@example.com", "1234567890", address, payment);

        assertEquals(1, customer.getId(), "The ID should be set correctly.");
        assertEquals("John Doe", customer.getName(), "The name should be set correctly.");
        assertEquals("john.doe@example.com", customer.getEmail(), "The email should be set correctly.");
        assertEquals("1234567890", customer.getPhone(), "The phone should be set correctly.");
        assertEquals(address, customer.getAddress(), "The address should be set correctly.");
        assertEquals(payment, customer.getPayment(), "The payment should be set correctly.");
    }

    @Test
    void testSettersAndGetters() {
        Customer customer = new Customer();

        customer.setId(1);
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setPhone("0987654321");

        Address address = new Address(2, "456 Avenue", "Another City", "Another State", "67890", "Another Country");
        customer.setAddress(address);

        Payment payment = new Payment();
        customer.setPayment(payment);

        assertEquals(1, customer.getId(), "The ID should be set and retrieved correctly.");
        assertEquals("Jane Doe", customer.getName(), "The name should be set and retrieved correctly.");
        assertEquals("jane.doe@example.com", customer.getEmail(), "The email should be set and retrieved correctly.");
        assertEquals("0987654321", customer.getPhone(), "The phone should be set and retrieved correctly.");
        assertEquals(address, customer.getAddress(), "The address should be set and retrieved correctly.");
        assertEquals(payment, customer.getPayment(), "The payment should be set and retrieved correctly.");
    }

    @Test
    void testNullValues() {
        Customer customer = new Customer();

        customer.setName(null);
        customer.setEmail(null);
        customer.setPhone(null);
        customer.setAddress(null);
        customer.setPayment(null);

        assertNull(customer.getName(), "The name should allow null values.");
        assertNull(customer.getEmail(), "The email should allow null values.");
        assertNull(customer.getPhone(), "The phone should allow null values.");
        assertNull(customer.getAddress(), "The address should allow null values.");
        assertNull(customer.getPayment(), "The payment should allow null values.");
    }

    @Test
    void testEmptyValues() {
        Customer customer = new Customer();

        customer.setName("");
        customer.setEmail("");
        customer.setPhone("");

        assertEquals("", customer.getName(), "The name should allow empty values.");
        assertEquals("", customer.getEmail(), "The email should allow empty values.");
        assertEquals("", customer.getPhone(), "The phone should allow empty values.");
    }
}
