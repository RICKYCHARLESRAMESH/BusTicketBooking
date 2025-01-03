//package com.controllerTest;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import com.controller.CustomerController;
//import com.model.Customer;
//import com.service.CustomerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//public class CustomerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private CustomerService customerService;
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
//    }
//
//    @Test
//    void testAddCustomer() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        when(customerService.addCustomer(any(Customer.class))).thenReturn("Customer added successfully");
//
//        mockMvc.perform(post("/api/customers")
//                .contentType("application/json")
//                .content("{\"name\":\"John Doe\", \"email\":\"john@example.com\", \"phone\":\"1234567890\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Customer added successfully"));
//    }
//
//    @Test
//    void testGetAllCustomers() throws Exception {
//        Customer customer1 = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        Customer customer2 = new Customer(2, "Jane Doe", "jane@example.com", "0987654321", null, null);
//        List<Customer> customers = Arrays.asList(customer1, customer2);
//
//        when(customerService.getAllCustomers()).thenReturn(customers);
//
//        mockMvc.perform(get("/api/customers"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].name").value("John Doe"))
//                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
//    }
//
//    @Test
//    void testGetCustomerById() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        when(customerService.getCustomerById(1)).thenReturn(Optional.of(customer));
//
//        mockMvc.perform(get("/api/customers/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("John Doe"))
//                .andExpect(jsonPath("$.email").value("john@example.com"));
//    }
//
//    @Test
//    void testGetCustomerByIdNotFound() throws Exception {
//        when(customerService.getCustomerById(999)).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/api/customers/999"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testGetCustomersByEmail() throws Exception {
//        Customer customer1 = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        Customer customer2 = new Customer(2, "Jane Doe", "john@example.com", "0987654321", null, null);
//        List<Customer> customers = Arrays.asList(customer1, customer2);
//
//        when(customerService.getCustomersByEmail("john@example.com")).thenReturn(customers);
//
//        mockMvc.perform(get("/api/customers/email/john@example.com"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].name").value("John Doe"))
//                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
//    }
//
//    @Test
//    void testGetCustomersByPhone() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        List<Customer> customers = Arrays.asList(customer);
//
//        when(customerService.getCustomersByPhone("1234567890")).thenReturn(customers);
//
//        mockMvc.perform(get("/api/customers/phone/1234567890"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(1))
//                .andExpect(jsonPath("$[0].name").value("John Doe"));
//    }
//
//    @Test
//    void testGetCustomersByCity() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        List<Customer> customers = Arrays.asList(customer);
//
//        when(customerService.getCustomersByCity("New York")).thenReturn(customers);
//
//        mockMvc.perform(get("/api/customers/city/New York"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(1))
//                .andExpect(jsonPath("$[0].name").value("John Doe"));
//    }
//
//    @Test
//    void testUpdateName() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        when(customerService.updateName(1, "John Smith")).thenReturn(customer);
//
//        mockMvc.perform(put("/api/customers/update/1/In")
//                .param("Name", "John Smith"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("John Smith"));
//    }
//
//    @Test
//    void testUpdateEmail() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        when(customerService.updateEmail(1, "john.smith@example.com")).thenReturn(customer);
//
//        mockMvc.perform(put("/api/customers/update/1/email")
//                .param("email", "john.smith@example.com"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.email").value("john.smith@example.com"));
//    }
//
//    @Test
//    void testUpdateCustomerDetails() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        when(customerService.updateCustomerDetails(1, customer)).thenReturn("Customer details updated successfully");
//
//        mockMvc.perform(put("/api/customers")
//                .contentType("application/json")
//                .content("{\"id\": 1, \"name\": \"John Doe\", \"email\": \"john@example.com\", \"phone\": \"1234567890\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Customer details updated successfully"));
//    }
//
//    @Test
//    void testGetCustomersByState() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        List<Customer> customers = Arrays.asList(customer);
//
//        when(customerService.getCustomersByState("California")).thenReturn(customers);
//
//        mockMvc.perform(get("/api/customers/state/California"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(1))
//                .andExpect(jsonPath("$[0].name").value("John Doe"));
//    }
//
//    @Test
//    void testGetCustomerAddressById() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        when(customerService.getCustomerAddressById(1)).thenReturn(Optional.of(customer));
//
//        mockMvc.perform(get("/api/customers/address/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("John Doe"));
//    }
//
//    @Test
//    void testUpdateCustomerAddress() throws Exception {
//        Customer customer = new Customer(1, "John Doe", "john@example.com", "1234567890", null, null);
//        when(customerService.updateCustomerAddress(1, customer)).thenReturn("Customer address updated successfully");
//
//        mockMvc.perform(put("/api/customers/address/1")
//                .contentType("application/json")
//                .content("{\"id\": 1, \"name\": \"John Doe\", \"email\": \"john@example.com\", \"phone\": \"1234567890\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Customer address updated successfully"));
//    }
//}
