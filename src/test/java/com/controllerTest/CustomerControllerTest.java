package com.controllerTest;
 
import static org.junit.jupiter.api.Assertions.*;
 
import com.controller.CustomerController;
import com.model.Customer;
import com.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
 
class CustomerControllerTest {
 
    @InjectMocks
    private CustomerController customerController;
 
    @Mock
    private CustomerService customerService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testAddCustomer() {
        Customer customer = new Customer(); // Create a Customer instance
        when(customerService.addCustomer(any())).thenReturn("Record Added Successfully");
 
        String result = customerController.addCustomer(customer);
 
        assertEquals("Record Added Successfully", result);
        verify(customerService, times(1)).addCustomer(customer);
    }
 
    @Test
    void testGetAllCustomers() {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        List<Customer> customers = Arrays.asList(customer1, customer2);
 
        when(customerService.getAllCustomers()).thenReturn(customers);
 
        List<Customer> result = customerController.getAllCustomers();
 
        assertEquals(2, result.size());
        verify(customerService, times(1)).getAllCustomers();
    }
 
    @Test
    void testGetCustomerByIdFound() {
        Customer customer = new Customer();
        when(customerService.getCustomerById(anyInt())).thenReturn(Optional.of(customer));
 
        ResponseEntity<Customer> response = customerController.getCustomerById(1);
 
        assertEquals(ResponseEntity.ok(customer), response);
        verify(customerService, times(1)).getCustomerById(1);
    }
 
    @Test
    void testGetCustomerByIdNotFound() {
        when(customerService.getCustomerById(anyInt())).thenReturn(Optional.empty());
 
        ResponseEntity<Customer> response = customerController.getCustomerById(1);
 
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(customerService, times(1)).getCustomerById(1);
    }
 
    @Test
    void testGetCustomersByEmail() {
        Customer customer = new Customer();
        List<Customer> customers = Arrays.asList(customer);
 
        when(customerService.getCustomersByEmail(any())).thenReturn(customers);
 
        List<Customer> result = customerController.getCustomersByEmail("test@example.com");
 
        assertEquals(1, result.size());
        verify(customerService, times(1)).getCustomersByEmail("test@example.com");
    }
 
    @Test
    void testGetCustomersByPhone() {
        Customer customer = new Customer();
        List<Customer> customers = Arrays.asList(customer);
 
        when(customerService.getCustomersByPhone(any())).thenReturn(customers);
 
        List<Customer> result = customerController.getCustomersByPhone("1234567890");
 
        assertEquals(1, result.size());
        verify(customerService, times(1)).getCustomersByPhone("1234567890");
    }
 
    @Test
    void testGetCustomersByCity() {
        Customer customer = new Customer();
        List<Customer> customers = Arrays.asList(customer);
 
        when(customerService.getCustomersByCity(any())).thenReturn(customers);
 
        List<Customer> result = customerController.getCustomersByCity("CityName");
 
        assertEquals(1, result.size());
        verify(customerService, times(1)).getCustomersByCity("CityName");
    }
 
    @Test
    void testGetCustomersByCountry() {
        Customer customer = new Customer();
        List<Customer> customers = Arrays.asList(customer);
 
        when(customerService.getCustomersByCountry(any())).thenReturn(customers);
 
        List<Customer> result = customerController.getCustomersByCountry("CountryName");
 
        assertEquals(1, result.size());
        verify(customerService, times(1)).getCustomersByCountry("CountryName");
    }
 
    @Test
    void testUpdateNameFound() {
        Customer updatedCustomer = new Customer();
        when(customerService.updateName(anyInt(), any())).thenReturn(updatedCustomer);
 
        ResponseEntity<Customer> response = customerController.updateName(1, "New Name");
 
        assertEquals(ResponseEntity.ok(updatedCustomer), response);
        verify(customerService, times(1)).updateName(1, "New Name");
    }
 
    @Test
    void testUpdateNameNotFound() {
        when(customerService.updateName(anyInt(), any())).thenReturn(null);
 
        ResponseEntity<Customer> response = customerController.updateName(1, "New Name");
 
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(customerService, times(1)).updateName(1, "New Name");
    }
 
    @Test
    void testUpdateEmailFound() {
        Customer updatedCustomer = new Customer();
        when(customerService.updateEmail(anyInt(), any())).thenReturn(updatedCustomer);
 
        ResponseEntity<Customer> response = customerController.updateEmail(1, "newemail@example.com");
 
        assertEquals(ResponseEntity.ok(updatedCustomer), response);
        verify(customerService, times(1)).updateEmail(1, "newemail@example.com");
    }
 
    @Test
    void testUpdateEmailNotFound() {
        when(customerService.updateEmail(anyInt(), any())).thenReturn(null);
 
        ResponseEntity<Customer> response = customerController.updateEmail(1, "newemail@example.com");
 
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(customerService, times(1)).updateEmail(1, "newemail@example.com");
    }
 
    @Test
    void testUpdateCustomerDetails() {
        Customer updatedCustomer = new Customer();
        when(customerService.updateCustomerDetails(anyInt(), any())).thenReturn("Customer updated successfully");
 
        String result = customerController.updateCustomerDetails(updatedCustomer);
 
        assertEquals("Customer updated successfully", result);
        verify(customerService, times(1)).updateCustomerDetails(updatedCustomer.getId(), updatedCustomer);
    }
}
 