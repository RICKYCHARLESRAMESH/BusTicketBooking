package com.serviceTest;

import com.dao.TripDAO;
import com.model.Trip;
import com.service.TripService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TripServiceTest {

    @Mock
    private TripDAO tripDAO;

    @InjectMocks
    private TripService tripService;

    private Trip trip;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trip = new Trip();
        trip.setId(1);
        trip.setDepartureTime(LocalDateTime.of(2025, 1, 5, 10, 0));
        trip.setArrivalTime(LocalDateTime.of(2025, 1, 5, 16, 0));
        trip.setAvailableSeats(30);
    }

 

    @Test
    void saveTrip_shouldThrowExceptionWhenBusIsNull() {
        trip.setBus(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tripService.saveTrip(trip);
        });

        assertEquals("Bus cannot be null", exception.getMessage());
        verify(tripDAO, never()).save(any());
    }

    @Test
    void getTripById_shouldReturnTripWhenFound() {
        when(tripDAO.findById(1)).thenReturn(Optional.of(trip));

        Trip result = tripService.getTripById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(tripDAO, times(1)).findById(1);
    }

    @Test
    void getTripById_shouldThrowExceptionWhenNotFound() {
        when(tripDAO.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            tripService.getTripById(1);
        });

        assertEquals("Trip not found", exception.getMessage());
        verify(tripDAO, times(1)).findById(1);
    }

    @Test
    void getAllTrips_shouldReturnAllTrips() {
        List<Trip> trips = Arrays.asList(trip, new Trip());
        when(tripDAO.findAll()).thenReturn(trips);

        List<Trip> result = tripService.getAllTrips();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(tripDAO, times(1)).findAll();
    }

    @Test
    void deleteTrip_shouldDeleteTripById() {
        doNothing().when(tripDAO).deleteById(1);

        tripService.deleteTrip(1);

        verify(tripDAO, times(1)).deleteById(1);
    }

    @Test
    void searchByFromCity_shouldReturnTrips() {
        List<Trip> trips = Arrays.asList(trip, new Trip());
        when(tripDAO.findByFromCity("CityA")).thenReturn(trips);

        List<Trip> result = tripService.searchByFromCity("CityA");

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(tripDAO, times(1)).findByFromCity("CityA");
    }

    @Test
    void searchByBusTypeAndTripDate_shouldReturnTrips() {
        LocalDateTime date = LocalDateTime.of(2025, 1, 5, 10, 0);
        List<Trip> trips = Arrays.asList(trip);
        when(tripDAO.findByBusTypeAndTripDate("Luxury", date)).thenReturn(trips);

        List<Trip> result = tripService.searchByBusTypeAndTripDate("Luxury", date);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tripDAO, times(1)).findByBusTypeAndTripDate("Luxury", date);
    }

    @Test
    void searchByFromCityToCityDateType_shouldReturnTrips() {
        LocalDateTime date = LocalDateTime.of(2025, 1, 5, 10, 0);
        List<Trip> trips = Arrays.asList(trip);
        when(tripDAO.findByFromCityAndToCityAndTripDateAndBusType(
                "CityA", "CityB", date, "Luxury"))
                .thenReturn(trips);

        List<Trip> result = tripService.searchByFromCityToCityDateType(
                "CityA", "CityB", date, "Luxury");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tripDAO, times(1)).findByFromCityAndToCityAndTripDateAndBusType("CityA", "CityB", date, "Luxury");
    }
}
