package com.controllerTest;

import com.controller.TripController;
import com.model.Bus;
import com.model.Driver;
import com.model.Route;
import com.model.Trip;
import com.service.BusService;
import com.service.DriverService;
import com.service.RouteService;
import com.service.TripService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TripControllerTest {

    @InjectMocks
    private TripController tripController;

    @Mock
    private TripService tripService;

    @Mock
    private RouteService routeService;

    @Mock
    private BusService busService;

    @Mock
    private DriverService driverService;

    public TripControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

  
    @Test
    void testCreateTrip_InvalidBus() {
        // Arrange
        Trip trip = new Trip();
        Bus bus = new Bus();
        bus.setBusId(999);
        trip.setBus(bus);

        when(busService.getBusById(999)).thenReturn(null);

        // Act
        ResponseEntity<String> response = tripController.createTrip(trip);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid Bus ID", response.getBody());
    }

    @Test
    void testGetAllTrips() {
        // Arrange
        List<Trip> trips = Arrays.asList(new Trip(), new Trip());
        when(tripService.getAllTrips()).thenReturn(trips);

        // Act
        ResponseEntity<List<Trip>> response = tripController.getAllTrips();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetTripById() {
        // Arrange
        Trip trip = new Trip();
        when(tripService.getTripById(1)).thenReturn(trip);

        // Act
        ResponseEntity<Trip> response = tripController.getTripById(1);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testSearchByFromCity() {
        // Arrange
        List<Trip> trips = Arrays.asList(new Trip(), new Trip());
        when(tripService.searchByFromCity("CityA")).thenReturn(trips);

        // Act
        ResponseEntity<List<Trip>> response = tripController.searchByFromCity("CityA");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testSearchByToCity() {
        // Arrange
        List<Trip> trips = Arrays.asList(new Trip(), new Trip());
        when(tripService.searchByToCity("CityB")).thenReturn(trips);

        // Act
        ResponseEntity<List<Trip>> response = tripController.searchByToCity("CityB");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testSearchByBusType() {
        // Arrange
        List<Trip> trips = Arrays.asList(new Trip(), new Trip());
        when(tripService.searchByBusType("AC")).thenReturn(trips);

        // Act
        ResponseEntity<List<Trip>> response = tripController.searchByBusType("AC");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testSearchByBusTypeAndTripDate() {
        // Arrange
        LocalDateTime tripDate = LocalDateTime.of(2025, 1, 1, 12, 0);
        List<Trip> trips = Arrays.asList(new Trip(), new Trip());
        when(tripService.searchByBusTypeAndTripDate("AC", tripDate)).thenReturn(trips);

        // Act
        ResponseEntity<List<Trip>> response = tripController.searchByBusTypeAndTripDate("AC", "2025-01-01T12:00:00");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testDeleteTrip() {
        // Arrange
        doNothing().when(tripService).deleteTrip(1);

        // Act
        ResponseEntity<String> response = tripController.deleteTrip(1);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Record Deleted Successfully", response.getBody());
    }
}
