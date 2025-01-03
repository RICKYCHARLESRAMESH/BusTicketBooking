package com.DaoTest;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.dao.CustomerDAO;
import com.model.Customer;
import com.model.Address;  // Ensure you have this import
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.ArrayList;
import java.util.List;
 
@ExtendWith(MockitoExtension.class)
public class CustomerDAOTest {
 
    @Mock
    private CustomerDAO customerDAO;
 
    private Customer customer1;
    private Customer customer2;
 
    @BeforeEach
    public void setUp() {
        // Create sample Customer objects
        customer1 = new Customer();
        customer1.setId(1);
        customer1.setEmail("customer1@example.com");
        customer1.setPhone("1234567890");
 
        // Initialize Address object
        Address address1 = new Address();
        address1.setCity("CityA");
        address1.setCountry("CountryA");
        address1.setState("StateA");
        customer1.setAddress(address1);
 
        customer2 = new Customer();
        customer2.setId(2);
        customer2.setEmail("customer2@example.com");
        customer2.setPhone("0987654321");
 
        // Initialize Address object
        Address address2 = new Address();
        address2.setCity("CityB");
        address2.setCountry("CountryB");
        address2.setState("StateB");
        customer2.setAddress(address2);
    }
 
    @Test
    public void testFindByEmail() {
        // Prepare mock behavior
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        when(customerDAO.findByEmail("customer1@example.com")).thenReturn(customers);
 
        // Test method
        List<Customer> foundCustomers = customerDAO.findByEmail("customer1@example.com");
 
        // Verify results
        assertNotNull(foundCustomers);
        assertEquals(1, foundCustomers.size());
        assertEquals(customer1.getId(), foundCustomers.get(0).getId());
 
        // Verify mock interaction
        verify(customerDAO, times(1)).findByEmail("customer1@example.com");
    }
 
    @Test
    public void testFindByPhone() {
        // Prepare mock behavior
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        when(customerDAO.findByPhone("1234567890")).thenReturn(customers);
 
        // Test method
        List<Customer> foundCustomers = customerDAO.findByPhone("1234567890");
 
        // Verify results
        assertNotNull(foundCustomers);
        assertEquals(1, foundCustomers.size());
        assertEquals(customer1.getId(), foundCustomers.get(0).getId());
 
        // Verify mock interaction
        verify(customerDAO, times(1)).findByPhone("1234567890");
    }
 
    @Test
    public void testFindByAddressCity() {
        // Prepare mock behavior
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        when(customerDAO.findByAddressCity("CityA")).thenReturn(customers);
 
        // Test method
        List<Customer> foundCustomers = customerDAO.findByAddressCity("CityA");
 
        // Verify results
        assertNotNull(foundCustomers);
        assertEquals(1, foundCustomers.size());
        assertEquals(customer1.getId(), foundCustomers.get(0).getId());
 
        // Verify mock interaction
        verify(customerDAO, times(1)).findByAddressCity("CityA");
    }
 
    @Test
    public void testFindByAddressCountry() {
        // Prepare mock behavior
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        when(customerDAO.findByAddressCountry("CountryA")).thenReturn(customers);
 
        // Test method
        List<Customer> foundCustomers = customerDAO.findByAddressCountry("CountryA");
 
        // Verify results
        assertNotNull(foundCustomers);
        assertEquals(1, foundCustomers.size());
        assertEquals(customer1.getId(), foundCustomers.get(0).getId());
 
        // Verify mock interaction
        verify(customerDAO, times(1)).findByAddressCountry("CountryA");
    }
 
    @Test
    public void testFindByAddressState() {
        // Prepare mock behavior
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        when(customerDAO.findByAddressState("StateA")).thenReturn(customers);
 
        // Test method
        List<Customer> foundCustomers = customerDAO.findByAddressState("StateA");
 
        // Verify results
        assertNotNull(foundCustomers);
        assertEquals(1, foundCustomers.size());
        assertEquals(customer1.getId(), foundCustomers.get(0).getId());
 
        // Verify mock interaction
        verify(customerDAO, times(1)).findByAddressState("StateA");
    }
}