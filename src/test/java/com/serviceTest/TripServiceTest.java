package com.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.dao.TripDAO;
import com.model.Trip;
import com.service.TripService;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
class TripServiceTest {
 
    @InjectMocks
    private TripService tripService;
 
    @Mock
    private TripDAO tripDAO;
 
    private Trip trip;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trip = new Trip();
        trip.setId(1);
        trip.setAvailableSeats(30);
        trip.setFare(new BigDecimal("500.00"));
        trip.setTripDate(LocalDateTime.now());
        trip.setFromCity("CityA");
        trip.setToCity("CityB");
        trip.setType("Luxury");
    }
 
    @Test
    void testSaveTrip() {
        // Arrange
        when(tripDAO.save(trip)).thenReturn(trip);
 
        // Act
        Trip result = tripService.saveTrip(trip);
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(tripDAO, times(1)).save(trip);
    }
 
    @Test
    void testGetTripById() {
        // Arrange
        when(tripDAO.findById(1)).thenReturn(Optional.of(trip));
 
        // Act
        Trip result = tripService.getTripById(1);
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(tripDAO, times(1)).findById(1);
    }
 
    @Test
    void testGetTripByIdNotFound() {
        // Arrange
        when(tripDAO.findById(1)).thenReturn(Optional.empty());
 
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> tripService.getTripById(1));
        assertEquals("Trip not found", exception.getMessage());
        verify(tripDAO, times(1)).findById(1);
    }
 
    @Test
    void testGetAllTrips() {
        // Arrange
        when(tripDAO.findAll()).thenReturn(Arrays.asList(trip));
 
        // Act
        List<Trip> result = tripService.getAllTrips();
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tripDAO, times(1)).findAll();
    }
 
    @Test
    void testDeleteTrip() {
        // Act
        tripService.deleteTrip(1);
 
        // Assert
        verify(tripDAO, times(1)).deleteById(1);
    }
 
    @Test
    void testSearchByFromCity() {
        // Arrange
        when(tripDAO.findByFromCity("CityA")).thenReturn(Arrays.asList(trip));
 
        // Act
        List<Trip> result = tripService.searchByFromCity("CityA");
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tripDAO, times(1)).findByFromCity("CityA");
    }
 
    @Test
    void testSearchByToCity() {
        // Arrange
        when(tripDAO.findByToCity("CityB")).thenReturn(Arrays.asList(trip));
 
        // Act
        List<Trip> result = tripService.searchByToCity("CityB");
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tripDAO, times(1)).findByToCity("CityB");
    }
 
    @Test
    void testSearchByBusType() {
        // Arrange
        when(tripDAO.findByBusType("Luxury")).thenReturn(Arrays.asList(trip));
 
        // Act
        List<Trip> result = tripService.searchByBusType("Luxury");
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tripDAO, times(1)).findByBusType("Luxury");
    }
 
    @Test
    void testSearchByBusTypeAndTripDate() {
        // Arrange
        LocalDate tripDate = LocalDate.now();
        when(tripDAO.findByBusTypeAndTripDate("Luxury", tripDate)).thenReturn(Arrays.asList(trip));
 
        // Act
        List<Trip> result = tripService.searchByBusTypeAndTripDate("Luxury", tripDate);
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tripDAO, times(1)).findByBusTypeAndTripDate("Luxury", tripDate);
    }
 
    @Test
    void testSearchByFromCityToCityDateType() {
        // Arrange
        LocalDate tripDate = LocalDate.now();
        when(tripDAO.findByFromCityAndToCityAndTripDateAndBusType("CityA", "CityB", tripDate, "Luxury"))
                .thenReturn(Arrays.asList(trip));
 
        // Act
        List<Trip> result = tripService.searchByFromCityToCityDateType("CityA", "CityB", tripDate, "Luxury");
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tripDAO, times(1)).findByFromCityAndToCityAndTripDateAndBusType("CityA", "CityB", tripDate, "Luxury");
    }
 
    @Test
    void testSearchByTripDate() {
        // Arrange
        LocalDate tripDate = LocalDate.now();
        when(tripDAO.findByTripDate(tripDate)).thenReturn(Arrays.asList(trip));
 
        // Act
        List<Trip> result = tripService.searchByTripDate(tripDate);
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tripDAO, times(1)).findByTripDate(tripDate);
    }
}
 
 