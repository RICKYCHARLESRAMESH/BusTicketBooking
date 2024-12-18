package com.service;

import com.dao.AddressDAO;
import com.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

	   @Autowired
    private final AddressDAO addressDAO;

 
    public AddressService(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    // Save a new address
    public Address saveAddress(Address address) {
        return addressDAO.save(address);
    }

    // Get all addresses
    public List<Address> getAllAddresses() {
        return addressDAO.findAll();
    }

    // Find address by ID
    public Optional<Address> getAddressById(Integer addressId) {
        return addressDAO.findById(addressId);
    }

    // Delete address by ID
    public void deleteAddress(Integer addressId) {
        addressDAO.deleteById(addressId);
    }
}
