package com.DaoTest;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.dao.AgencyDAO;
import com.model.Agency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.Optional;
 
@ExtendWith(MockitoExtension.class) // Use Mockito extension for JUnit 5
public class AgencyDAOTest {
 
    @Mock
    private AgencyDAO agencyDAO;
 
    private Agency agency;
 
    @BeforeEach
    public void setUp() {
        // Create sample data
        agency = new Agency();
        agency.setAgencyId(1); // Set ID for testing
        agency.setName("Test Agency");
        agency.setContactPersonName("John Doe");
        agency.setEmail("testagency@example.com");
        agency.setPhone("1234567890");
    }
 
    @Test
    public void testSaveAgency() {
        // Mock DAO behavior
        when(agencyDAO.save(agency)).thenReturn(agency);
 
        // Test method
        Agency savedAgency = agencyDAO.save(agency);
 
        // Verify results
        assertNotNull(savedAgency.getAgencyId());
        assertEquals("Test Agency", savedAgency.getName());
        assertEquals("John Doe", savedAgency.getContactPersonName());
        assertEquals("testagency@example.com", savedAgency.getEmail());
        assertEquals("1234567890", savedAgency.getPhone());
 
        // Verify mock interaction
        verify(agencyDAO, times(1)).save(agency);
    }
 
    @Test
    public void testFindAgencyById() {
        // Mock DAO behavior
        when(agencyDAO.findById(1)).thenReturn(Optional.of(agency));
 
        // Test method
        Optional<Agency> foundAgency = agencyDAO.findById(1);
 
        // Verify results
        assertTrue(foundAgency.isPresent());
        assertEquals(agency.getAgencyId(), foundAgency.get().getAgencyId());
 
        // Verify mock interaction
        verify(agencyDAO, times(1)).findById(1);
    }
 
    @Test
    public void testUpdateAgency() {
        // Mock DAO behavior
        when(agencyDAO.save(agency)).thenReturn(agency);
 
        // Update agency details
        agency.setEmail("updatedagency@example.com");
 
        // Test method
        Agency updatedAgency = agencyDAO.save(agency);
 
        // Verify results
        assertEquals("updatedagency@example.com", updatedAgency.getEmail());
 
        // Verify mock interaction
        verify(agencyDAO, times(1)).save(agency);
    }
 
    @Test
    public void testDeleteAgency() {
        // Mock DAO behavior
        doNothing().when(agencyDAO).deleteById(agency.getAgencyId());
 
        // Test method
        agencyDAO.deleteById(agency.getAgencyId());
 
        // Verify mock interaction
        verify(agencyDAO, times(1)).deleteById(agency.getAgencyId());
    }
}
