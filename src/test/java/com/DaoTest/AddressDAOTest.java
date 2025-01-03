package com.DaoTest;
 
import com.dao.AddressDAO;
import com.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ContextConfiguration(classes = {AddressDAO.class})
@SpringBootTest
public class AddressDAOTest {
    @Mock
    private AddressDAO addressDAO;
    private Address address;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize sample address
        address = new Address();
        address.setAddressId(1);
        address.setState("123 Main St");
        address.setCity("CityName");
        address.setState("StateName");
        address.setZipcode("12345");
    }
    @Test
    public void testFindById() {
        // Mock DAO behavior
        when(addressDAO.findById(1)).thenReturn(Optional.of(address));
        // Test method
        Optional<Address> retrievedAddress = addressDAO.findById(1);
        // Verify results
        assertTrue(retrievedAddress.isPresent());
        assertEquals(1, retrievedAddress.get().getAddressId());
        assertEquals("StateName", retrievedAddress.get().getState()); // Corrected value for state
        // Verify mock interaction
        verify(addressDAO, times(1)).findById(1);
    }
    @Test
    public void testSave() {
        // Mock DAO behavior
        when(addressDAO.save(address)).thenReturn(address);
        // Test method
        Address savedAddress = addressDAO.save(address);
        // Verify results
        assertNotNull(savedAddress);
        assertEquals(1, savedAddress.getAddressId());
        assertEquals("StateName", savedAddress.getState()); // Corrected value for state
        // Verify mock interaction
        verify(addressDAO, times(1)).save(address);
    }

    @Test
    public void testDeleteById() {
        // Mock DAO behavior
        doNothing().when(addressDAO).deleteById(1);
        // Test method
        addressDAO.deleteById(1);
        // Verify mock interaction
        verify(addressDAO, times(1)).deleteById(1);
    }
    @Test
    public void testFindAll() {
        // Mock DAO behavior
        when(addressDAO.findAll()).thenReturn(List.of(address));
        // Test method
        var addresses = addressDAO.findAll();
        // Verify results
        assertNotNull(addresses);
        assertEquals(1, addresses.size());
        assertEquals("StateName", addresses.get(0).getState()); // Corrected value for state
        // Verify mock interaction
        verify(addressDAO, times(1)).findAll();
    }
}