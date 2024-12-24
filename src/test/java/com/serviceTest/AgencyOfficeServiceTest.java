package com.serviceTest;
 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.dao.AgencyOfficeDAO;
import com.service.AgencyOfficeService;
 
class AgencyOfficeServiceTest {
 
    private AgencyOfficeService agencyOfficeService;
 
    @Mock
    private AgencyOfficeDAO agencyOfficeDAO;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        agencyOfficeService = new AgencyOfficeService();
        agencyOfficeService.setAgencyOfficeRepo(agencyOfficeDAO);
    }
 
    @Test
    void testGetAgencyOfficeRepo() {
        assertNotNull(agencyOfficeService.getAgencyOfficeRepo());
        assertEquals(agencyOfficeDAO, agencyOfficeService.getAgencyOfficeRepo());
    }
 
    @Test
    void testSetAgencyOfficeRepo() {
        AgencyOfficeDAO newAgencyOfficeDAO = mock(AgencyOfficeDAO.class);
        agencyOfficeService.setAgencyOfficeRepo(newAgencyOfficeDAO);
        assertEquals(newAgencyOfficeDAO, agencyOfficeService.getAgencyOfficeRepo());
    }
 
    @Test
    void testDAOInteraction() {
        // Example of DAO interaction test
        agencyOfficeService.getAgencyOfficeRepo().findAll(); // Call to DAO method
        verify(agencyOfficeDAO, times(1)).findAll(); // Verify if DAO method is called
    }
}
