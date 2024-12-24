package com.controllerTest;
import com.controller.AgencyController;
import com.model.Agency;
import com.model.AgencyOffice;
import com.service.AgencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
 
public class AgencyControllerTest {
 
    @InjectMocks
    private AgencyController agencyController;
 
    @Mock
    private AgencyService agencyService;
 
    private Agency agency;
    private AgencyOffice agencyOffice;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
 
        agency = new Agency(1, "Test Agency", "Test Contact", "test@example.com", "1234567890", new ArrayList<>());
        agencyOffice = new AgencyOffice();
    }
 
    @Test
    public void testAddAgency() {
        // Arrange
        when(agencyService.addAgency(agency)).thenReturn("Agency added successfully");
 
        // Act
        ResponseEntity<String> response = agencyController.addAgency(agency);
 
        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Agency added successfully", response.getBody());
        verify(agencyService, times(1)).addAgency(agency);
    }
 
    @Test
    public void testGetAgencyById() {
        // Arrange
        when(agencyService.getAgencyById(1)).thenReturn(Optional.of(agency));
 
        // Act
        ResponseEntity<Agency> response = agencyController.getAgencyById(1);
 
        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(agency, response.getBody());
        verify(agencyService, times(1)).getAgencyById(1);
    }
 
    @Test
    public void testUpdateAgency() {
        // Arrange
        when(agencyService.updateAgency(1, agency)).thenReturn("Agency updated successfully");
 
        // Act
        ResponseEntity<String> response = agencyController.updateAgency(1, agency);
 
        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Agency updated successfully", response.getBody());
        verify(agencyService, times(1)).updateAgency(1, agency);
    }
 
    @Test
    public void testGetOfficesByAgencyId() {
        // Arrange
        List<AgencyOffice> offices = new ArrayList<>();
        offices.add(agencyOffice);
        when(agencyService.getOfficesByAgencyId(1)).thenReturn(offices);
 
        // Act
        ResponseEntity<List<AgencyOffice>> response = agencyController.getOfficesByAgencyId(1);
 
        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(offices, response.getBody());
        verify(agencyService, times(1)).getOfficesByAgencyId(1);
    }
 
    @Test
    public void testGetOfficeById() {
        // Arrange
        when(agencyService.getOfficeById(1, 1)).thenReturn(agencyOffice);
 
        // Act
        ResponseEntity<AgencyOffice> response = agencyController.getOfficeById(1, 1);
 
        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(agencyOffice, response.getBody());
        verify(agencyService, times(1)).getOfficeById(1, 1);
    }
}
 
