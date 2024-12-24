package com.serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.dao.CustomerDAO;
import com.model.Address;
import com.model.Customer;
import com.service.CustomerService;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
class CustomerServiceTest {
 
    @InjectMocks
    private CustomerService customerService;
 
    @Mock
    private CustomerDAO customerDAO;
 
    private Customer customer;
    private Address address;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
 
        // Initialize dummy data
        address = new Address();
        address.setCity("New York");
        address.setState("NY");
 
        customer = new Customer();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhone("1234567890");
        customer.setAddress(address);
    }
 
    @Test
    void testAddCustomer() {
        when(customerDAO.save(customer)).thenReturn(customer);
 
        String result = customerService.addCustomer(customer);
 
        assertEquals("Record created Successfully", result);
        verify(customerDAO, times(1)).save(customer);
    }
 
    @Test
    void testGetAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
 
        when(customerDAO.findAll()).thenReturn(customers);
 
        List<Customer> result = customerService.getAllCustomers();
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerDAO, times(1)).findAll();
    }
 
    @Test
    void testGetCustomerById() {
        when(customerDAO.findById(1)).thenReturn(Optional.of(customer));
 
        Optional<Customer> result = customerService.getCustomerById(1);
 
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(customerDAO, times(1)).findById(1);
    }
 
    @Test
    void testGetCustomersByEmail() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
 
        when(customerDAO.findByEmail("john.doe@example.com")).thenReturn(customers);
 
        List<Customer> result = customerService.getCustomersByEmail("john.doe@example.com");
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerDAO, times(1)).findByEmail("john.doe@example.com");
    }
 
    @Test
    void testUpdateName() {
        when(customerDAO.findById(1)).thenReturn(Optional.of(customer));
        when(customerDAO.save(customer)).thenReturn(customer);
 
        Customer result = customerService.updateName(1, "Jane Doe");
 
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(customerDAO, times(1)).findById(1);
        verify(customerDAO, times(1)).save(customer);
    }
 
    @Test
    void testUpdateCustomerDetails() {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setName("Jane Doe");
        updatedCustomer.setEmail("jane.doe@example.com");
        updatedCustomer.setPhone("9876543210");
        updatedCustomer.setAddress(address);
 
        when(customerDAO.findById(1)).thenReturn(Optional.of(customer));
        when(customerDAO.save(customer)).thenReturn(customer);
 
        String result = customerService.updateCustomerDetails(1, updatedCustomer);
 
        assertEquals("Record Updated Successfully", result);
        verify(customerDAO, times(1)).findById(1);
        verify(customerDAO, times(1)).save(customer);
    }
 
    @Test
    void testGetCustomersByCity() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
 
        when(customerDAO.findByAddressCity("New York")).thenReturn(customers);
 
        List<Customer> result = customerService.getCustomersByCity("New York");
 
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerDAO, times(1)).findByAddressCity("New York");
    }
 
    @Test
    void testUpdateCustomerAddress() {
        when(customerDAO.findById(1)).thenReturn(Optional.of(customer));
        when(customerDAO.save(customer)).thenReturn(customer);
 
        String result = customerService.updateCustomerAddress(1, customer);
 
        assertEquals("Record Updated Successfully", result);
        verify(customerDAO, times(1)).findById(1);
        verify(customerDAO, times(1)).save(customer);
    }
}
 
 