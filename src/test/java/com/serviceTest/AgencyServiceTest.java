package com.serviceTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.dao.AgencyDAO;
import com.model.Agency;
import com.model.AgencyOffice;
import com.service.AgencyService;
 
class AgencyServiceTest {
 
    @InjectMocks
    private AgencyService agencyService;
 
    @Mock
    private AgencyDAO agencyDAO;
 
    private Agency agency;
    private AgencyOffice agencyOffice;
    private List<AgencyOffice> offices;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        agencyOffice = new AgencyOffice();
        agencyOffice.setOfficeId(1);
        offices = new ArrayList<>();
        offices.add(agencyOffice);
 
        agency = new Agency(1, "Test Agency", "John Doe", "test@example.com", "1234567890", offices);
    }
 
    @Test
    void testAddAgency() {
        when(agencyDAO.save(agency)).thenReturn(agency);
 
        String result = agencyService.addAgency(agency);
 
        assertEquals("Record Created Successfully", result);
        verify(agencyDAO, times(1)).save(agency);
    }
 
    @Test
    void testGetAgencyById_Found() {
        when(agencyDAO.findById(1)).thenReturn(Optional.of(agency));
 
        Optional<Agency> result = agencyService.getAgencyById(1);
 
        assertTrue(result.isPresent());
        assertEquals(agency, result.get());
        verify(agencyDAO, times(1)).findById(1);
    }
 
    @Test
    void testGetAgencyById_NotFound() {
        when(agencyDAO.findById(1)).thenReturn(Optional.empty());
 
        Optional<Agency> result = agencyService.getAgencyById(1);
 
        assertTrue(result.isEmpty());
        verify(agencyDAO, times(1)).findById(1);
    }
 
    @Test
    void testUpdateAgency_Success() {
        when(agencyDAO.existsById(1)).thenReturn(true);
        when(agencyDAO.save(agency)).thenReturn(agency);
 
        String result = agencyService.updateAgency(1, agency);
 
        assertEquals("Record Updated Successfully", result);
        verify(agencyDAO, times(1)).existsById(1);
        verify(agencyDAO, times(1)).save(agency);
    }
 
    @Test
    void testUpdateAgency_NotFound() {
        when(agencyDAO.existsById(1)).thenReturn(false);
 
        String result = agencyService.updateAgency(1, agency);
 
        assertEquals("Agency not found!", result);
        verify(agencyDAO, times(1)).existsById(1);
        verify(agencyDAO, never()).save(agency);
    }
 
    @Test
    void testGetOfficesByAgencyId_Found() {
        when(agencyDAO.findById(1)).thenReturn(Optional.of(agency));
 
        List<AgencyOffice> result = agencyService.getOfficesByAgencyId(1);
 
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(agencyOffice, result.get(0));
        verify(agencyDAO, times(1)).findById(1);
    }
 
    @Test
    void testGetOfficesByAgencyId_NotFound() {
        when(agencyDAO.findById(1)).thenReturn(Optional.empty());
 
        List<AgencyOffice> result = agencyService.getOfficesByAgencyId(1);
 
        assertNull(result);
        verify(agencyDAO, times(1)).findById(1);
    }
 
    @Test
    void testGetOfficeById_Found() {
        when(agencyDAO.findById(1)).thenReturn(Optional.of(agency));
 
        AgencyOffice result = agencyService.getOfficeById(1, 1);
 
        assertNotNull(result);
        assertEquals(agencyOffice, result);
        verify(agencyDAO, times(1)).findById(1);
    }
 
    @Test
    void testGetOfficeById_NotFound() {
        when(agencyDAO.findById(1)).thenReturn(Optional.of(agency));
 
        AgencyOffice result = agencyService.getOfficeById(1, 2);
 
        assertNull(result);
        verify(agencyDAO, times(1)).findById(1);
    }
 
    @Test
    void testGetOfficeById_AgencyNotFound() {
        when(agencyDAO.findById(1)).thenReturn(Optional.empty());
 
        AgencyOffice result = agencyService.getOfficeById(1, 1);
 
        assertNull(result);
        verify(agencyDAO, times(1)).findById(1);
    }
}
 