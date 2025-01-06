package com.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.dao.AddressDAO;
import com.model.Address;
 
/**
* AddressService is a service class responsible for managing address-related operations.
*/
@Service
public class AddressService {
 
    @Autowired
    private AddressDAO addressDAO;
 
    /**
     * Creates or updates an address in the database.
     *
     * @param address The address entity to be saved.
     * @return The saved address entity.
     */
    public Address saveAddress(Address address) {
        return addressDAO.save(address);
    }
 
    /**
     * Retrieves an address by its ID.
     *
     * @param addressId The ID of the address to be retrieved.
     * @return The address entity if found, otherwise null.
     */
    public Address getAddressById(Integer addressId) {
        Optional<Address> address = addressDAO.findById(addressId);
        return address.orElse(null); // Return the address if found, otherwise null
    }
 
    /**
     * Retrieves all addresses from the database.
     *
     * @return A list of all address entities.
     */
    public List<Address> getAllAddresses() {
        return addressDAO.findAll();
    }
 
    /**
     * Deletes an address by its ID.
     *
     * @param addressId The ID of the address to be deleted.
     * @return True if the address was deleted successfully, false if not found.
     */
    public boolean deleteAddress(Integer addressId) {
        if (addressDAO.existsById(addressId)) {
            addressDAO.deleteById(addressId);
            return true;
        }
        return false; // Address not found
    }
}