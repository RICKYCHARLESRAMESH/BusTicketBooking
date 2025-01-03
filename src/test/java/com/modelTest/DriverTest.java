package com.modelTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Address;
import com.model.AgencyOffice;
import com.model.Driver;

public class DriverTest {

    private Driver driver;
    private AgencyOffice agencyOffice;
    private Address address;

    @BeforeEach
    public void setup() {
        agencyOffice = new AgencyOffice();
        address = new Address();
        driver = new Driver(1, "LN12345", "John Doe", "1234567890", agencyOffice, address);
    }

    @Test
    public void testGetDriverId() {
        assertEquals(1, driver.getDriverId());
    }

    @Test
    public void testSetDriverId() {
        driver.setDriverId(2);
        assertEquals(2, driver.getDriverId());
    }

    @Test
    public void testGetLicenseNumber() {
        assertEquals("LN12345", driver.getLicenseNumber());
    }

    @Test
    public void testSetLicenseNumber() {
        driver.setLicenseNumber("LN67890");
        assertEquals("LN67890", driver.getLicenseNumber());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", driver.getName());
    }

    @Test
    public void testSetName() {
        driver.setName("Jane Doe");
        assertEquals("Jane Doe", driver.getName());
    }

    @Test
    public void testGetPhone() {
        assertEquals("1234567890", driver.getPhone());
    }

    @Test
    public void testSetPhone() {
        driver.setPhone("0987654321");
        assertEquals("0987654321", driver.getPhone());
    }

    @Test
    public void testGetAgencyOffice() {
        assertEquals(agencyOffice, driver.getAgencyOffice());
    }

    @Test
    public void testSetAgencyOffice() {
        AgencyOffice newOffice = new AgencyOffice();
        driver.setAgencyOffice(newOffice);
        assertEquals(newOffice, driver.getAgencyOffice());
    }

    @Test
    public void testGetAddress() {
        assertEquals(address, driver.getAddress());
    }

    @Test
    public void testSetAddress() {
        Address newAddress = new Address();
        driver.setAddress(newAddress);
        assertEquals(newAddress, driver.getAddress());
    }
}
