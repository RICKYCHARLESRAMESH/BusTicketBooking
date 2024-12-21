package com.controller;
 
import com.model.Customer;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.Optional;
 
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
 
    @Autowired
    private CustomerService customerService;
 
    // Add a new customer
    @PostMapping
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
 
    // Search all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
 
    // Search customer by customer_id
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }
        return ResponseEntity.notFound().build();
    }
 
    // Search customers by email
    @GetMapping("/email/{email}")
    public List<Customer> getCustomersByEmail(@PathVariable String email) {
        return customerService.getCustomersByEmail(email);
    }
 
    // Search customers by phone
    @GetMapping("/phone/{phone}")
    public List<Customer> getCustomersByPhone(@PathVariable String phone) {
        return customerService.getCustomersByPhone(phone);
    }
 
    // Search customers by city
    @GetMapping("/city/{city}")
    public List<Customer> getCustomersByCity(@PathVariable String city) {
        return customerService.getCustomersByCity(city);
    }
 
    // Search customers by country
    @GetMapping("/country/{country}")
    public List<Customer> getCustomersByCountry(@PathVariable String country) {
        return customerService.getCustomersByCountry(country);
    }
 
    // Update name of customer
    @PutMapping("/update/{customerId}/In")
    public ResponseEntity<Customer> updateName(@PathVariable Integer customerId, @RequestParam String Name) {
        Customer updatedCustomer = customerService.updateName(customerId, Name);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }
 
    // Update email of customer
    @PutMapping("/update/{customerId}/email")
    public ResponseEntity<Customer> updateEmail(@PathVariable Integer customerId, @RequestParam String email) {
        Customer updatedCustomer = customerService.updateEmail(customerId, email);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }
 
    // Update customer details
    @PutMapping
    public String updateCustomerDetails(@RequestBody Customer updatedCustomer) {
        return customerService.updateCustomerDetails(updatedCustomer.getId(), updatedCustomer);
    }
 
    // Search customers by state
    @GetMapping("/state/{state}")
    public List<Customer> getCustomersByState(@PathVariable String state) {
        return customerService.getCustomersByState(state);
    }
 
    // Get customer address by customer ID
    @GetMapping("/address/{customerId}")
    public ResponseEntity<Customer> getCustomerAddressById(@PathVariable Integer customerId) {
        Optional<Customer> customer = customerService.getCustomerAddressById(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }
        return ResponseEntity.notFound().build();
    }
 
    // Update customer address
    @PutMapping("/address/{customerId}")
    public String updateCustomerAddress(@PathVariable Integer customerId, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomerAddress(customerId, updatedCustomer);
    }
}