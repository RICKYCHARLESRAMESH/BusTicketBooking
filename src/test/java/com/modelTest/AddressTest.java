package com.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.model.Address;

public class AddressTest {

    @Test
    void testDefaultConstructor() {
        Address address = new Address();
        assertNotNull(address, "The Address object should be created successfully.");
    }

    @Test
    void testParameterizedConstructor() {
        Address address = new Address(1, "123 Main Street", "Springfield", "Illinois", "62701", "USA");

        assertNotNull(address, "The Address object should be created successfully.");
        assertEquals(1, address.getAddressId());
        assertEquals("123 Main Street", address.getAddress());
        assertEquals("Springfield", address.getCity());
        assertEquals("Illinois", address.getState());
        assertEquals("62701", address.getZipcode());
        assertEquals("USA", address.getCountry());
    }

    @Test
    void testGettersAndSetters() {
        Address address = new Address();

        address.setAddressId(10);
        address.setAddress("456 Elm Street");
        address.setCity("Metropolis");
        address.setState("New York");
        address.setZipcode("10128");
        address.setCountry("Canada");

        assertEquals(10, address.getAddressId());
        assertEquals("456 Elm Street", address.getAddress());
        assertEquals("Metropolis", address.getCity());
        assertEquals("New York", address.getState());
        assertEquals("10128", address.getZipcode());
        assertEquals("Canada", address.getCountry());
    }

    @Test
    void testInvalidZipcode() {
        Address address = new Address();
        address.setZipcode("123");
        assertEquals("123", address.getZipcode(), "The zipcode should allow values up to 10 characters.");
    }

    @Test
    void testLongAddress() {
        Address address = new Address();
        String longAddress = "12345 Long Street Name That Exceeds Normal Length";
        address.setAddress(longAddress);
        assertEquals(longAddress, address.getAddress(), "The address should accept long strings up to 255 characters.");
    }

    @Test
    void testCountryField() {
        Address address = new Address();
        address.setCountry("Germany");
        assertEquals("Germany", address.getCountry(), "The country field should be set and retrieved correctly.");
    }
}
