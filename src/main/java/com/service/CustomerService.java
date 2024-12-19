package com.service;

import com.dao.CustomerDAO;
import com.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    // Add a new customer
    public String addCustomer(Customer customer) {
        customerDAO.save(customer);
        return "Record created Successfully";
    }

    // Search all customers
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    // Search customer by customer_id
    public Optional<Customer> getCustomerById(Integer customerId) {
        return customerDAO.findById(customerId);
    }

    // Search customers by email
    public List<Customer> getCustomersByEmail(String email) {
        return customerDAO.findByEmail(email);
    }

    // Search customers by phone
    public List<Customer> getCustomersByPhone(String phone) {
        return customerDAO.findByPhone(phone);
    }

    // Search customers by city
    public List<Customer> getCustomersByCity(String city) {
        return customerDAO.findByAddressCity(city);
    }

    // Search customers by country
    public List<Customer> getCustomersByCountry(String country) {
        return customerDAO.findByAddressCountry(country);
    }

    // Update customer's last name
    public Customer updateLastName(Integer customerId, String lastName) {
        Optional<Customer> customerOptional = customerDAO.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(lastName);
            return customerDAO.save(customer);
        }
        return null; // Or handle error gracefully if customer does not exist
    }

    // Update customer's email
    public Customer updateEmail(Integer customerId, String email) {
        Optional<Customer> customerOptional = customerDAO.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setEmail(email);
            return customerDAO.save(customer);
        }
        return null; // Or handle error gracefully if customer does not exist
    }

    // Update customer's full details
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

    // Search customers by state
    public List<Customer> getCustomersByState(String state) {
        return customerDAO.findByAddressState(state);
    }

    // Get customer address by customer ID
    public Optional<Customer> getCustomerAddressById(Integer customerId) {
        return customerDAO.findById(customerId);
    }

    // Update customer address
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
