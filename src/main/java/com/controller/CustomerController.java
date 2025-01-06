package com.controller;
 
import com.model.Customer;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.Optional;
 
/**
* CustomerController is a REST controller that manages customer-related operations.
* It provides endpoints to create, retrieve, update, and manage customer records.
*/
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
 
    @Autowired
    private CustomerService customerService;
 
    /**
     * Adds a new customer.
     *
     * @param customer The Customer object to be added.
     * @return A string message indicating the result of the operation.
     */
    @PostMapping
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }
 
    /**
     * Retrieves all customers.
     *
     * @return A list of Customer objects.
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
 
    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return ResponseEntity containing the Customer object if found, or HTTP status 404 (Not Found) if not found.
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }
        return ResponseEntity.notFound().build();
    }
 
    /**
     * Retrieves customers by their email.
     *
     * @param email The email of the customers to retrieve.
     * @return A list of Customer objects that match the email.
     */
    @GetMapping("/email/{email}")
    public List<Customer> getCustomersByEmail(@PathVariable String email) {
        return customerService.getCustomersByEmail(email);
    }
 
    /**
     * Retrieves customers by their phone number.
     *
     * @param phone The phone number of the customers to retrieve.
     * @return A list of Customer objects that match the phone number.
     */
    @GetMapping("/phone/{phone}")
    public List<Customer> getCustomersByPhone(@PathVariable String phone) {
        return customerService.getCustomersByPhone(phone);
    }
 
    /**
     * Updates the name of a customer.
     *
     * @param customerId The ID of the customer to update.
     * @param Name The new name of the customer.
     * @return ResponseEntity containing the updated Customer object if successful, or HTTP status 404 (Not Found) if not found.
     */
    @PutMapping("/update/{customerId}/In")
    public ResponseEntity<Customer> updateName(@PathVariable Integer customerId, @RequestParam String Name) {
        Customer updatedCustomer = customerService.updateName(customerId, Name);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }
 
    /**
     * Updates the email of a customer.
     *
     * @param customerId The ID of the customer to update.
     * @param email The new email of the customer.
     * @return ResponseEntity containing the updated Customer object if successful, or HTTP status 404 (Not Found) if not found.
     */
    @PutMapping("/update/{customerId}/email")
    public ResponseEntity<Customer> updateEmail(@PathVariable Integer customerId, @RequestParam String email) {
        Customer updatedCustomer = customerService.updateEmail(customerId, email);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }
 
    /**
     * Updates customer details.
     *
     * @param updatedCustomer The Customer object containing updated details.
     * @return A string message indicating the result of the operation.
     */
    @PutMapping
    public String updateCustomerDetails(@RequestBody Customer updatedCustomer) {
        return customerService.updateCustomerDetails(updatedCustomer.getId(), updatedCustomer);
    }
 
    /**
     * Retrieves the address of a customer by their ID.
     *
     * @param customerId The ID of the customer whose address is to be retrieved.
     * @return ResponseEntity containing the Customer object if found, or HTTP status 404 (Not Found) if not found.
     */
    @GetMapping("/address/{customerId}")
    public ResponseEntity<Customer> getCustomerAddressById(@PathVariable Integer customerId) {
        Optional<Customer> customer = customerService.getCustomerAddressById(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }
        return ResponseEntity.notFound().build();
    }
 
    /**
     * Updates the address of a customer.
     *
     * @param customerId The ID of the customer whose address is to be updated.
     * @param updatedCustomer The Customer object containing updated address details.
     * @return A string message indicating the result of the operation.
     */
    @PutMapping("/address/{customerId}")
    public String updateCustomerAddress(@PathVariable Integer customerId, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomerAddress(customerId, updatedCustomer);
    }
}
