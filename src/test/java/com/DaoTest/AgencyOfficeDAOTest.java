package com.DaoTest;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.dao.AgencyOfficeDAO;
import com.dao.AgencyDAO;
import com.model.Agency;
import com.model.AgencyOffice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.Optional;
import java.util.List;
 
@ExtendWith(MockitoExtension.class)
public class AgencyOfficeDAOTest {
 
    @Mock
    private AgencyOfficeDAO agencyOfficeDAO;
 
    @Mock
    private AgencyDAO agencyDAO; // Assuming you need to mock AgencyDAO too
 
    private Agency agency;
    private AgencyOffice agencyOffice;
 
    @BeforeEach
    public void setUp() {
        // Create and set up sample data
        agency = new Agency();
        agency.setAgencyId(1);
        agency.setName("Test Agency");
 
        agencyOffice = new AgencyOffice();
        agencyOffice.setOfficeId(1);
        agencyOffice.setAgency(agency);
        agencyOffice.setOfficeMail("office@example.com");
        agencyOffice.setOfficeContactPersonName("Alice Smith");
        agencyOffice.setOfficeContactNumber("9876543210");
    }
 
    @Test
    public void testSaveAgencyOffice() {
        // Mock DAO behavior
        when(agencyOfficeDAO.save(agencyOffice)).thenReturn(agencyOffice);
 
        // Act
        AgencyOffice savedOffice = agencyOfficeDAO.save(agencyOffice);
 
        // Assert
        assertNotNull(savedOffice);
        assertEquals(agencyOffice.getOfficeId(), savedOffice.getOfficeId());
        assertEquals(agencyOffice.getOfficeMail(), savedOffice.getOfficeMail());
        assertEquals(agencyOffice.getOfficeContactPersonName(), savedOffice.getOfficeContactPersonName());
        assertEquals(agencyOffice.getOfficeContactNumber(), savedOffice.getOfficeContactNumber());
 
        // Verify mock interaction
        verify(agencyOfficeDAO, times(1)).save(agencyOffice);
    }
 
    @Test
    public void testFindAgencyOfficeById() {
        // Mock DAO behavior
        when(agencyOfficeDAO.findById(1)).thenReturn(Optional.of(agencyOffice));
 
        // Act
        Optional<AgencyOffice> foundOffice = agencyOfficeDAO.findById(1);
 
        // Assert
        assertTrue(foundOffice.isPresent());
        assertEquals(agencyOffice.getOfficeId(), foundOffice.get().getOfficeId());
 
        // Verify mock interaction
        verify(agencyOfficeDAO, times(1)).findById(1);
    }
 
    @Test
    public void testUpdateAgencyOffice() {
        // Mock DAO behavior
        when(agencyOfficeDAO.save(agencyOffice)).thenReturn(agencyOffice);
 
        // Act
        agencyOffice.setOfficeMail("updatedoffice@example.com");
        AgencyOffice updatedOffice = agencyOfficeDAO.save(agencyOffice);
 
        // Assert
        assertEquals("updatedoffice@example.com", updatedOffice.getOfficeMail());
 
        // Verify mock interaction
        verify(agencyOfficeDAO, times(1)).save(agencyOffice);
    }
 
    @Test
    public void testDeleteAgencyOffice() {
        // Act
        agencyOfficeDAO.deleteById(agencyOffice.getOfficeId());
 
        // Verify mock interaction
        verify(agencyOfficeDAO, times(1)).deleteById(agencyOffice.getOfficeId());
    }
}