package com.service;
 
import com.dao.CustomerDAO;
import com.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
/**
* CustomerService is a service class responsible for managing customer-related operations.
*/
@Service
public class CustomerService {
 
    @Autowired
    private CustomerDAO customerDAO;
 
    /**
     * Adds a new customer to the database.
     *
     * @param customer The Customer object to be added.
     * @return A message indicating success.
     */
    public String addCustomer(Customer customer) {
        customerDAO.save(customer);
        return "Record created Successfully";
    }
 
    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all Customer objects.
     */
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }
 
    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return An Optional containing the Customer if found, otherwise empty.
     */
    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerDAO.findById(customerId);
    }
 
    /**
     * Retrieves customers by their email.
     *
     * @param email The email to search for.
     * @return A list of Customer objects with the specified email.
     */
    public List<Customer> getCustomersByEmail(String email) {
        return customerDAO.findByEmail(email);
    }
 
    /**
     * Retrieves customers by their phone number.
     *
     * @param phone The phone number to search for.
     * @return A list of Customer objects with the specified phone number.
     */
    public List<Customer> getCustomersByPhone(String phone) {
        return customerDAO.findByPhone(phone);
    }
 
    /**
     * Retrieves customers by their city.
     *
     * @param city The city to search for.
     * @return A list of Customer objects located in the specified city.
     */
    public List<Customer> getCustomersByCity(String city) {
        return customerDAO.findByAddressCity(city);
    }
 
    /**
     * Retrieves customers by their country.
     *
     * @param country The country to search for.
     * @return A list of Customer objects located in the specified country.
     */
    public List<Customer> getCustomersByCountry(String country) {
        return customerDAO.findByAddressCountry(country);
    }
 
    /**
     * Updates the name of a customer.
     *
     * @param customerId The ID of the customer to update.
     * @param name The new name for the customer.
     * @return The updated Customer object, or null if not found.
     */
    public Customer updateName(Integer customerId, String name) {
        Optional<Customer> customerOptional = customerDAO.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(name);
            return customerDAO.save(customer);
        }
        return null; // Or handle error gracefully if customer does not exist
    }
 
    /**
     * Updates the email of a customer.
     *
     * @param customerId The ID of the customer to update.
     * @param email The new email for the customer.
     * @return The updated Customer object, or null if not found.
     */
    public Customer updateEmail(Integer customerId, String email) {
        Optional<Customer> customerOptional = customerDAO.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setEmail(email);
            return customerDAO.save(customer);
        }
        return null; // Or handle error gracefully if customer does not exist
    }
 
    /**
     * Updates a customer's full details.
     *
     * @param customerId The ID of the customer to update.
     * @param updatedCustomer The Customer object containing the updated details.
     * @return A message indicating success or failure.
     */
    public String updateCustomerDetails(Integer customerId, Customer updatedCustomer) {
        Optional<Customer> customerOptional = customerDAO.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhone(updatedCustomer.getPhone());
            customer.setAddress(updatedCustomer.getAddress());
            customer.setPayment(updatedCustomer.getPayment());
            customerDAO.save(customer);
            return "Record Updated Successfully";
        }
        return "Customer not found"; // Or handle error gracefully if customer does not exist
    }
 
    /**
     * Retrieves customers by their state.
     *
     * @param state The state to search for.
     * @return A list of Customer objects located in the specified state.
     */
    public List<Customer> getCustomersByState(String state) {
        return customerDAO.findByAddressState(state);
    }
 
    /**
     * Retrieves a customer's address by their ID.
     *
     * @param customerId The ID of the customer.
     * @return An Optional containing the Customer if found, otherwise empty.
     */
    public Optional<Customer> getCustomerAddressById(Integer customerId) {
        return customerDAO.findById(customerId);
    }
 
    /**
     * Updates a customer's address.
     *
     * @param customerId The ID of the customer to update.
     * @param updatedCustomer The Customer object containing the updated address.
     * @return A message indicating success or failure.
     */
    public String updateCustomerAddress(Integer customerId, Customer updatedCustomer) {
        Optional<Customer> customerOptional = customerDAO.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setAddress(updatedCustomer.getAddress());
            customerDAO.save(customer);
            return "Record Updated Successfully";
        }
        return "Customer not found"; // Or handle error gracefully if customer does not exist
    }
}

