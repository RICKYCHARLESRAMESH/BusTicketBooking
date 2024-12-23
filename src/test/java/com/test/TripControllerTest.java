package com.test;
 
import com.controller.TripController;
import com.model.Trip;
import com.service.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
public class TripControllerTest {
 
    @InjectMocks
    private TripController tripController;
 
    @Mock
    private TripService tripService;
 
    private Trip trip;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
 
        trip = new Trip();
        trip.setId(1);
        trip.setAvailableSeats(40);
        trip.setFare(new BigDecimal("500.00"));
        trip.setFromCity("CityA");
        trip.setToCity("CityB");
        trip.setTripDate(LocalDateTime.of(2023, 12, 25, 10, 0));
        trip.setDepartureTime(LocalDateTime.of(2023, 12, 25, 8, 0));
        trip.setArrivalTime(LocalDateTime.of(2023, 12, 25, 12, 0));
        trip.setBoardingAddressId(101);
        trip.setDroppingAddressId(102);
        trip.setType("Luxury");
    }
    @Test
    void testCreateTrip() {
        // Arrange
        when(tripService.saveTrip(trip)).thenReturn(trip); // Mock the saveTrip method to return the provided trip object
 
        // Act
        ResponseEntity<String> response = tripController.createTrip(trip);
 
        // Assert
        assertEquals("Record Created Successfully", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).saveTrip(trip); // Verify that the saveTrip method was called once with the given trip
    }
 
//
//    @Test
//    void testCreateTrip() {
//        doNothing().when(tripService).saveTrip(trip);
//        ResponseEntity<String> response = tripController.createTrip(trip);
//        assertEquals("Record Created Successfully", response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//        verify(tripService, times(1)).saveTrip(trip);
//    }
 
    @Test
    void testGetAllTrips() {
 
        
        when(tripService.getAllTrips()).thenReturn(Arrays.asList(trip));
 
        // Call the controller method
        ResponseEntity<List<Trip>> response = tripController.getAllTrips();
 
      
        // Assertions to validate behavior
        assertEquals(1, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
 
        // Verify service interaction
        verify(tripService, times(1)).getAllTrips();
    }
 
 
	@Test
    void testGetTripById() {
        when(tripService.getTripById(1)).thenReturn(trip);
        ResponseEntity<Trip> response = tripController.getTripById(1);
        assertEquals(trip, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).getTripById(1);
    }
 
//    @Test
//    void testUpdateTrip() {
//        doNothing().when(tripService).saveTrip(trip);
//        ResponseEntity<String> response = tripController.updateTrip(trip);
//        assertEquals("Record Updated Successfully", response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//        verify(tripService, times(1)).saveTrip(trip);
//    }
    @Test
    void testUpdateTrip() {
        // Arrange
        when(tripService.saveTrip(trip)).thenReturn(trip); // Mock the saveTrip method to return the provided trip object
 
        // Act
        ResponseEntity<String> response = tripController.updateTrip(trip);
 
        // Assert
        assertEquals("Record Updated Successfully", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).saveTrip(trip); // Verify that the saveTrip method was called once with the given trip
    }
 
    @Test
    void testSearchByFromCity() {
        when(tripService.searchByFromCity("CityA")).thenReturn(Arrays.asList(trip));
        ResponseEntity<List<Trip>> response = tripController.searchByFromCity("CityA");
        assertEquals(1, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).searchByFromCity("CityA");
    }
 
    @Test
    void testSearchByToCity() {
        when(tripService.searchByToCity("CityB")).thenReturn(Arrays.asList(trip));
        ResponseEntity<List<Trip>> response = tripController.searchByToCity("CityB");
        assertEquals(1, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).searchByToCity("CityB");
    }
 
    @Test
    void testSearchByBusType() {
        when(tripService.searchByBusType("Luxury")).thenReturn(Arrays.asList(trip));
        ResponseEntity<List<Trip>> response = tripController.searchByBusType("Luxury");
        assertEquals(1, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).searchByBusType("Luxury");
    }
 
    @Test
    void testSearchByBusTypeAndDate() {
        when(tripService.searchByBusTypeAndTripDate("Luxury", LocalDate.of(2023, 12, 25)))
                .thenReturn(Arrays.asList(trip));
        ResponseEntity<List<Trip>> response = tripController.searchByBusTypeAndDate("Luxury", "2023-12-25");
        assertEquals(1, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).searchByBusTypeAndTripDate("Luxury", LocalDate.of(2023, 12, 25));
    }
 
    @Test
    void testSearchByFromToCityDateType() {
        when(tripService.searchByFromCityToCityDateType("CityA", "CityB", LocalDate.of(2023, 12, 25), "Luxury"))
                .thenReturn(Arrays.asList(trip));
        ResponseEntity<List<Trip>> response = tripController.searchByFromToCityDateType("CityA", "CityB", "2023-12-25", "Luxury");
        assertEquals(1, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).searchByFromCityToCityDateType("CityA", "CityB", LocalDate.of(2023, 12, 25), "Luxury");
    }
 
    @Test
    void testSearchByFromToCityDate() {
        when(tripService.searchByFromCityToCityDate("CityA", "CityB", LocalDate.of(2023, 12, 25)))
                .thenReturn(Arrays.asList(trip));
        ResponseEntity<List<Trip>> response = tripController.searchByFromToCityDate("CityA", "CityB", "2023-12-25");
        assertEquals(1, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).searchByFromCityToCityDate("CityA", "CityB", LocalDate.of(2023, 12, 25));
    }
 
    @Test
    void testDeleteTrip() {
        doNothing().when(tripService).deleteTrip(1);
        ResponseEntity<String> response = tripController.deleteTrip(1);
        assertEquals("Record Deleted Successfully", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).deleteTrip(1);
    }
 
    @Test
    void testSearchByTripDate() {
        when(tripService.searchByTripDate(LocalDate.of(2023, 12, 25))).thenReturn(Arrays.asList(trip));
        ResponseEntity<List<Trip>> response = tripController.searchByTripDate("2023-12-25");
        assertEquals(1, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
        verify(tripService, times(1)).searchByTripDate(LocalDate.of(2023, 12, 25));
    }
}