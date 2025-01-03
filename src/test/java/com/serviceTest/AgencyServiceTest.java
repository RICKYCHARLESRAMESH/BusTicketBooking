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

    @Mock
    private AgencyDAO agencyDAO;

    @InjectMocks
    private AgencyService agencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAgency() {
        Agency agency = new Agency();
        agency.setAgencyId(1);
        agency.setName("Test Agency");

        when(agencyDAO.save(agency)).thenReturn(agency);

        String result = agencyService.addAgency(agency);
        assertEquals("Record Created Successfully", result, "The agency should be added successfully.");
        verify(agencyDAO, times(1)).save(agency);
    }

    @Test
    void testGetAgencyById_Found() {
        Agency agency = new Agency();
        agency.setAgencyId(1);

        when(agencyDAO.findById(1)).thenReturn(Optional.of(agency));

        Optional<Agency> result = agencyService.getAgencyById(1);
        assertTrue(result.isPresent(), "The agency should be found.");
        assertEquals(1, result.get().getAgencyId(), "The agency ID should match.");
        verify(agencyDAO, times(1)).findById(1);
    }

    @Test
    void testGetAgencyById_NotFound() {
        when(agencyDAO.findById(1)).thenReturn(Optional.empty());

        Optional<Agency> result = agencyService.getAgencyById(1);
        assertFalse(result.isPresent(), "The agency should not be found.");
        verify(agencyDAO, times(1)).findById(1);
    }

    @Test
    void testUpdateAgency_Found() {
        Agency agency = new Agency();
        agency.setAgencyId(1);

        when(agencyDAO.existsById(1)).thenReturn(true);
        when(agencyDAO.save(agency)).thenReturn(agency);

        String result = agencyService.updateAgency(1, agency);
        assertEquals("Record Updated Successfully", result, "The agency should be updated successfully.");
        verify(agencyDAO, times(1)).save(agency);
    }

    @Test
    void testUpdateAgency_NotFound() {
        when(agencyDAO.existsById(1)).thenReturn(false);

        String result = agencyService.updateAgency(1, new Agency());
        assertEquals("Agency not found!", result, "The agency should not be updated if not found.");
        verify(agencyDAO, never()).save(any());
    }

    @Test
    void testGetOfficesByAgencyId_Found() {
        Agency agency = new Agency();
        agency.setAgencyId(1);

        AgencyOffice office1 = new AgencyOffice();
        office1.setOfficeId(101);
        AgencyOffice office2 = new AgencyOffice();
        office2.setOfficeId(102);

        List<AgencyOffice> offices = new ArrayList<>();
        offices.add(office1);
        offices.add(office2);

        agency.setOffices(offices);

        when(agencyDAO.findById(1)).thenReturn(Optional.of(agency));

        List<AgencyOffice> result = agencyService.getOfficesByAgencyId(1);
        assertNotNull(result, "The offices should be retrieved.");
        assertEquals(2, result.size(), "The number of offices should match.");
        verify(agencyDAO, times(1)).findById(1);
    }

    @Test
    void testGetOfficesByAgencyId_NotFound() {
        when(agencyDAO.findById(1)).thenReturn(Optional.empty());

        List<AgencyOffice> result = agencyService.getOfficesByAgencyId(1);
        assertNull(result, "No offices should be retrieved if the agency is not found.");
        verify(agencyDAO, times(1)).findById(1);
    }

    @Test
    void testGetOfficeById_Found() {
        Agency agency = new Agency();
        agency.setAgencyId(1);

        AgencyOffice office = new AgencyOffice();
        office.setOfficeId(101);

        List<AgencyOffice> offices = new ArrayList<>();
        offices.add(office);
        agency.setOffices(offices);

        when(agencyDAO.findById(1)).thenReturn(Optional.of(agency));

        AgencyOffice result = agencyService.getOfficeById(1, 101);
        assertNotNull(result, "The office should be retrieved.");
        assertEquals(101, result.getOfficeId(), "The office ID should match.");
        verify(agencyDAO, times(1)).findById(1);
    }

    @Test
    void testGetOfficeById_NotFound() {
        when(agencyDAO.findById(1)).thenReturn(Optional.empty());

        AgencyOffice result = agencyService.getOfficeById(1, 101);
        assertNull(result, "No office should be retrieved if the agency is not found.");
        verify(agencyDAO, times(1)).findById(1);
    }

    @Test
    void testGetAllAgencies() {
        List<Agency> agencies = new ArrayList<>();
        agencies.add(new Agency());
        agencies.add(new Agency());

        when(agencyDAO.findAll()).thenReturn(agencies);

        List<Agency> result = agencyService.getAllAgencies();
        assertNotNull(result, "The agencies should be retrieved.");
        assertEquals(2, result.size(), "The number of agencies should match.");
        verify(agencyDAO, times(1)).findAll();
    }

    @Test
    void testGetAgencyByName() {
        Agency agency = new Agency();
        agency.setName("Test Agency");

        when(agencyDAO.findByName("Test Agency")).thenReturn(Optional.of(agency));

        Optional<Agency> result = agencyService.getAgencyByName("Test Agency");
        assertTrue(result.isPresent(), "The agency should be found by name.");
        assertEquals("Test Agency", result.get().getName(), "The agency name should match.");
        verify(agencyDAO, times(1)).findByName("Test Agency");
    }

    @Test
    void testGetAgencyByContactPersonName() {
        Agency agency = new Agency();
        agency.setContactPersonName("John Doe");

        when(agencyDAO.findByContactPersonName("John Doe")).thenReturn(Optional.of(agency));

        Optional<Agency> result = agencyService.getAgencyByContactPersonName("John Doe");
        assertTrue(result.isPresent(), "The agency should be found by contact person name.");
        assertEquals("John Doe", result.get().getContactPersonName(), "The contact person name should match.");
        verify(agencyDAO, times(1)).findByContactPersonName("John Doe");
    }
}
