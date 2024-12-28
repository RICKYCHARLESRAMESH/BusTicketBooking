package com.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dao.AddressDAO;
import com.model.Address;
import com.service.AddressService;

class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressDAO addressDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAddress() {
        Address address = new Address(1, "123 Main St", "Springfield", "IL", "62704", "USA");
        when(addressDAO.save(address)).thenReturn(address);

        Address savedAddress = addressService.saveAddress(address);

        assertNotNull(savedAddress);
        assertEquals("123 Main St", savedAddress.getAddress());
        verify(addressDAO, times(1)).save(address);
    }

    @Test
    void testGetAddressById_Found() {
        Address address = new Address(1, "123 Main St", "Springfield", "IL", "62704", "USA");
        when(addressDAO.findById(1)).thenReturn(Optional.of(address));

        Address retrievedAddress = addressService.getAddressById(1);

        assertNotNull(retrievedAddress);
        assertEquals("123 Main St", retrievedAddress.getAddress());
        verify(addressDAO, times(1)).findById(1);
    }

    @Test
    void testGetAddressById_NotFound() {
        when(addressDAO.findById(1)).thenReturn(Optional.empty());

        Address retrievedAddress = addressService.getAddressById(1);

        assertNull(retrievedAddress);
        verify(addressDAO, times(1)).findById(1);
    }

    @Test
    void testGetAllAddresses() {
        List<Address> addresses = Arrays.asList(
            new Address(1, "123 Main St", "Springfield", "IL", "62704", "USA"),
            new Address(2, "456 Elm St", "Chicago", "IL", "60614", "USA")
        );
        when(addressDAO.findAll()).thenReturn(addresses);

        List<Address> retrievedAddresses = addressService.getAllAddresses();

        assertNotNull(retrievedAddresses);
        assertEquals(2, retrievedAddresses.size());
        verify(addressDAO, times(1)).findAll();
    }

    @Test
    void testDeleteAddress_Success() {
        when(addressDAO.existsById(1)).thenReturn(true);

        boolean isDeleted = addressService.deleteAddress(1);

        assertTrue(isDeleted);
        verify(addressDAO, times(1)).existsById(1);
        verify(addressDAO, times(1)).deleteById(1);
    }

    @Test
    void testDeleteAddress_NotFound() {
        when(addressDAO.existsById(1)).thenReturn(false);

        boolean isDeleted = addressService.deleteAddress(1);

        assertFalse(isDeleted);
        verify(addressDAO, times(1)).existsById(1);
        verify(addressDAO, never()).deleteById(1);
    }
}
