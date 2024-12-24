package com.serviceTest;

import com.dao.AddressDAO;
import com.model.Address;
import com.service.AddressService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private AddressDAO addressDAO;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAddress() {
        Address address = new Address(1, "123 Main St", "CityName", "StateName", "123456", "CountryName");
        when(addressDAO.save(address)).thenReturn(address);

        Address savedAddress = addressService.saveAddress(address);

        assertNotNull(savedAddress);
        assertEquals(1, savedAddress.getAddressId());
        assertEquals("123 Main St", savedAddress.getAddress());
        verify(addressDAO, times(1)).save(address);
    }

    @Test
    void testGetAllAddresses() {
        List<Address> addresses = Arrays.asList(
                new Address(1, "123 Main St", "CityName", "StateName", "123456", "CountryName"),
                new Address(2, "456 Elm St", "AnotherCity", "AnotherState", "654321", "AnotherCountry")
        );

        when(addressDAO.findAll()).thenReturn(addresses);

        List<Address> result = addressService.getAllAddresses();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(addressDAO, times(1)).findAll();
    }

    @Test
    void testGetAddressById() {
        Address address = new Address(1, "123 Main St", "CityName", "StateName", "123456", "CountryName");
        when(addressDAO.findById(1)).thenReturn(Optional.of(address));

        Optional<Address> result = addressService.getAddressById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getAddressId());
        verify(addressDAO, times(1)).findById(1);
    }

    @Test
    void testDeleteAddress() {
        int addressId = 1;

        doNothing().when(addressDAO).deleteById(addressId);

        addressService.deleteAddress(addressId);

        verify(addressDAO, times(1)).deleteById(addressId);
    }
}
