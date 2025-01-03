package com.controllerTest;

import com.controller.AgencyController;
import com.exception.CustomException;
import com.model.Agency;
import com.model.AgencyOffice;
import com.service.AgencyService;
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
import static org.mockito.Mockito.*;

class AgencyControllerTest {

    @Mock
    private AgencyService agencyService;

    @InjectMocks
    private AgencyController agencyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAgencies() {
        List<Agency> agencies = Arrays.asList(
                new Agency(1, "Agency1", "Contact1", "email1@example.com", "1234567890", null),
                new Agency(2, "Agency2", "Contact2", "email2@example.com", "0987654321", null)
        );

        when(agencyService.getAllAgencies()).thenReturn(agencies);

        ResponseEntity<List<Agency>> response = agencyController.getAllAgencies();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(agencyService, times(1)).getAllAgencies();
    }

    @Test
    void testAddAgency_Success() {
        Agency agency = new Agency(1, "NewAgency", "ContactPerson", "email@example.com", "1234567890", null);
        when(agencyService.addAgency(agency)).thenReturn("Agency added successfully");

        ResponseEntity<String> response = agencyController.addAgency(agency);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Agency added successfully", response.getBody());
        verify(agencyService, times(1)).addAgency(agency);
    }

    @Test
    void testAddAgency_Fails_NullName() {
        Agency agency = new Agency();
        agency.setContactPersonName("ContactPerson");
        agency.setEmail("email@example.com");
        agency.setPhone("1234567890");

        Exception exception = assertThrows(CustomException.class, () -> agencyController.addAgency(agency));

        assertEquals("Agency name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testGetAgencyById_Success() {
        Agency agency = new Agency(1, "Agency1", "Contact1", "email@example.com", "1234567890", null);
        when(agencyService.getAgencyById(1)).thenReturn(Optional.of(agency));

        ResponseEntity<Agency> response = agencyController.getAgencyById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(agency, response.getBody());
        verify(agencyService, times(1)).getAgencyById(1);
    }

    @Test
    void testGetAgencyById_NotFound() {
        when(agencyService.getAgencyById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> agencyController.getAgencyById(99));

        assertEquals("Agency not found with ID: 99", exception.getMessage());
    }

    @Test
    void testGetAgencyByName_Success() {
        Agency agency = new Agency(1, "Agency1", "Contact1", "email@example.com", "1234567890", null);
        when(agencyService.getAgencyByName("Agency1")).thenReturn(Optional.of(agency));

        ResponseEntity<Agency> response = agencyController.getAgencyByName("Agency1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(agency, response.getBody());
        verify(agencyService, times(1)).getAgencyByName("Agency1");
    }

    @Test
    void testGetOfficesByAgencyId_Success() {
        List<AgencyOffice> offices = Arrays.asList(
                new AgencyOffice(),
                new AgencyOffice()
        );

        when(agencyService.getOfficesByAgencyId(1)).thenReturn(offices);

        ResponseEntity<List<AgencyOffice>> response = agencyController.getOfficesByAgencyId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(agencyService, times(1)).getOfficesByAgencyId(1);
    }

    @Test
    void testGetOfficeById_Success() {
        AgencyOffice office = new AgencyOffice();
        when(agencyService.getOfficeById(1, 2)).thenReturn(office);

        ResponseEntity<AgencyOffice> response = agencyController.getOfficeById(1, 2);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(office, response.getBody());
        verify(agencyService, times(1)).getOfficeById(1, 2);
    }

    @Test
    void testGetOfficeById_NotFound() {
        when(agencyService.getOfficeById(1, 99)).thenReturn(null);

        Exception exception = assertThrows(CustomException.class, () -> agencyController.getOfficeById(1, 99));

        assertEquals("Office not found with agency ID: 1 and office ID: 99", exception.getMessage());
    }

    @Test
    void testUpdateAgency_Success() {
        Agency agency = new Agency(1, "UpdatedAgency", "UpdatedContact", "updated@example.com", "9876543210", null);
        when(agencyService.updateAgency(1, agency)).thenReturn("Agency updated successfully");

        ResponseEntity<String> response = agencyController.updateAgency(1, agency);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Agency updated successfully", response.getBody());
        verify(agencyService, times(1)).updateAgency(1, agency);
    }

    @Test
    void testUpdateAgency_Fails_InvalidId() {
        Agency agency = new Agency();
        agency.setName("UpdatedAgency");

        Exception exception = assertThrows(CustomException.class, () -> agencyController.updateAgency(-1, agency));

        assertEquals("Invalid agency ID: -1", exception.getMessage());
    }
}
