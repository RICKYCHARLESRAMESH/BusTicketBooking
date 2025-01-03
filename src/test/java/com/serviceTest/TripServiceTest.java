//package com.serviceTest;
//
//import com.dao.TripDAO;
//import com.model.Trip;
//import com.service.TripService;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TripServiceTest {
//
//    @Mock
//    private TripDAO tripDAO;
//
//    @InjectMocks
//    private TripService tripService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testSaveTrip() {
//        Trip trip = new Trip();
////        trip.setBus(new Object()); // Replace with appropriate Bus object
//
////        tripService.saveTrip(trip);
//        verify(tripDAO, times(1)).save(trip);
//    }
//
//    @Test
//    void testSaveTripThrowsExceptionWhenBusIsNull() {
//        Trip trip = new Trip();
//        assertThrows(IllegalArgumentException.class, () -> tripService.saveTrip(trip));
//    }
//
//    @Test
//    void testGetTripById() {
//        Trip trip = new Trip();
//        trip.setId(1); // Assuming there's a getId() method
//        when(tripDAO.findById(1)).thenReturn(Optional.of(trip));
//
//        Trip result = tripService.getTripById(1);
//        assertNotNull(result);
//        assertEquals(1, result.getId());
//    }
//
//    @Test
//    void testGetTripByIdThrowsExceptionWhenNotFound() {
//        when(tripDAO.findById(1)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> tripService.getTripById(1));
//    }
//
//    @Test
//    void testGetAllTrips() {
//        Trip trip1 = new Trip();
//        Trip trip2 = new Trip();
//        when(tripDAO.findAll()).thenReturn(Arrays.asList(trip1, trip2));
//
//        List<Trip> result = tripService.getAllTrips();
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    void testDeleteTrip() {
//        tripService.deleteTrip(1);
//        verify(tripDAO, times(1)).deleteById(1);
//    }
//
//    @Test
//    void testSearchByFromCity() {
//        Trip trip = new Trip();
//        when(tripDAO.findByFromCity("City1")).thenReturn(Arrays.asList(trip));
//
//        List<Trip> result = tripService.searchByFromCity("City1");
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void testSearchByToCity() {
//        Trip trip = new Trip();
//        when(tripDAO.findByToCity("City2")).thenReturn(Arrays.asList(trip));
//
//        List<Trip> result = tripService.searchByToCity("City2");
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void testSearchByBusType() {
//        Trip trip = new Trip();
//        when(tripDAO.findByBusType("Luxury")).thenReturn(Arrays.asList(trip));
//
//        List<Trip> result = tripService.searchByBusType("Luxury");
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void testSearchByBusTypeAndTripDate() {
//        Trip trip = new Trip();
//        LocalDateTime tripDate = LocalDateTime.now();
//        when(tripDAO.findByBusTypeAndTripDate("Luxury", tripDate)).thenReturn(Arrays.asList(trip));
//
//        List<Trip> result = tripService.searchByBusTypeAndTripDate("Luxury", tripDate);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void testSearchByFromCityToCityDateType() {
//        Trip trip = new Trip();
//        LocalDateTime tripDate = LocalDateTime.now();
//        when(tripDAO.findByFromCityAndToCityAndTripDateAndBusType("City1", "City2", tripDate, "Luxury"))
//                .thenReturn(Arrays.asList(trip));
//
//        List<Trip> result = tripService.searchByFromCityToCityDateType("City1", "City2", tripDate, "Luxury");
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void testSearchByFromCityToCityDate() {
//        Trip trip = new Trip();
//        LocalDateTime tripDate = LocalDateTime.now();
//        when(tripDAO.findByFromCityAndToCityAndTripDate("City1", "City2", tripDate)).thenReturn(Arrays.asList(trip));
//
//        List<Trip> result = tripService.searchByFromCityToCityDate("City1", "City2", tripDate);
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void testSearchByTripDate() {
//        Trip trip = new Trip();
//        LocalDateTime tripDate = LocalDateTime.now();
//        when(tripDAO.findByTripDate(tripDate)).thenReturn(Arrays.asList(trip));
//
//        List<Trip> result = tripService.searchByTripDate(tripDate);
//        assertEquals(1, result.size());
//    }
//}
