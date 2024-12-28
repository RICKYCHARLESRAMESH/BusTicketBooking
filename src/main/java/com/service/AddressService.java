package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AddressDAO;
import com.model.Address;

@Service
public class AddressService {

    @Autowired
    private AddressDAO addressDAO;

    // Create or update an address
    public Address saveAddress(Address address) {
        return addressDAO.save(address);
    }

    // Retrieve an address by ID
    public Address getAddressById(Integer addressId) {
        Optional<Address> address = addressDAO.findById(addressId);
        return address.orElse(null); // Return the address if found, otherwise null
    }

    // Retrieve all addresses
    public List<Address> getAllAddresses() {
        return addressDAO.findAll();
    }

    // Delete an address by ID
    public boolean deleteAddress(Integer addressId) {
        if (addressDAO.existsById(addressId)) {
            addressDAO.deleteById(addressId);
            return true;
        }
        return false; // Address not found
    }
}
