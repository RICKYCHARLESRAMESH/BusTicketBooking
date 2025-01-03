package com.modelTest;

import com.model.Bus;
import com.model.Driver;
import com.model.Route;
import com.model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TripTest {

    private Trip trip;
    private Route route;
    private Bus bus;
    private Driver driver;

    @BeforeEach
    public void setUp() {
        // Create sample objects for Route, Bus, and Driver
        route = new Route();  // Assume Route class exists and has appropriate constructors/setters
        bus = new Bus();  // Assume Bus class exists and has appropriate constructors/setters
        driver = new Driver();  // Assume Driver class exists and has appropriate constructors/setters
        
        // Create a sample Trip object
        trip = new Trip(1, route, bus, 101, 102, LocalDateTime.now(), LocalDateTime.now().plusHours(5), driver, 50, BigDecimal.valueOf(500.00), LocalDateTime.now());
    }

    @Test
    public void testGetId() {
        // Verifying the trip ID
        assertEquals(1, trip.getId());
    }

    @Test
    public void testSetId() {
        // Setting a new ID and verifying
        trip.setId(2);
        assertEquals(2, trip.getId());
    }

    @Test
    public void testGetRoute() {
        // Verifying that the route is correctly set
        assertNotNull(trip.getRoute());
        assertEquals(route, trip.getRoute());
    }

    @Test
    public void testSetRoute() {
        // Setting a new route and verifying
        Route newRoute = new Route();  // Assuming new route instance
        trip.setRoute(newRoute);
        assertEquals(newRoute, trip.getRoute());
    }

    @Test
    public void testGetBus() {
        // Verifying the bus assignment
        assertNotNull(trip.getBus());
        assertEquals(bus, trip.getBus());
    }

    @Test
    public void testSetBus() {
        // Setting a new bus and verifying
        Bus newBus = new Bus();  // Assuming new bus instance
        trip.setBus(newBus);
        assertEquals(newBus, trip.getBus());
    }

    @Test
    public void testGetBoardingAddressId() {
        // Verifying the boarding address ID
        assertEquals(101, trip.getBoardingAddressId());
    }

    @Test
    public void testSetBoardingAddressId() {
        // Setting a new boarding address ID
        trip.setBoardingAddressId(103);
        assertEquals(103, trip.getBoardingAddressId());
    }

    @Test
    public void testGetDroppingAddressId() {
        // Verifying the dropping address ID
        assertEquals(102, trip.getDroppingAddressId());
    }

    @Test
    public void testSetDroppingAddressId() {
        // Setting a new dropping address ID
        trip.setDroppingAddressId(104);
        assertEquals(104, trip.getDroppingAddressId());
    }

    @Test
    public void testGetDepartureTime() {
        // Verifying the departure time
        assertNotNull(trip.getDepartureTime());
    }

    @Test
    public void testSetDepartureTime() {
        // Setting a new departure time
        LocalDateTime newDepartureTime = LocalDateTime.now().plusHours(1);
        trip.setDepartureTime(newDepartureTime);
        assertEquals(newDepartureTime, trip.getDepartureTime());
    }

    @Test
    public void testGetArrivalTime() {
        // Verifying the arrival time
        assertNotNull(trip.getArrivalTime());
    }

    @Test
    public void testSetArrivalTime() {
        // Setting a new arrival time
        LocalDateTime newArrivalTime = LocalDateTime.now().plusHours(6);
        trip.setArrivalTime(newArrivalTime);
        assertEquals(newArrivalTime, trip.getArrivalTime());
    }

    @Test
    public void testGetAvailableSeats() {
        // Verifying available seats
        assertEquals(50, trip.getAvailableSeats());
    }

    @Test
    public void testSetAvailableSeats() {
        // Setting new available seats
        trip.setAvailableSeats(60);
        assertEquals(60, trip.getAvailableSeats());
    }

    @Test
    public void testGetFare() {
        // Verifying the fare
        assertEquals(BigDecimal.valueOf(500.00), trip.getFare());
    }

    @Test
    public void testSetFare() {
        // Setting new fare
        trip.setFare(BigDecimal.valueOf(600.00));
        assertEquals(BigDecimal.valueOf(600.00), trip.getFare());
    }

    @Test
    public void testGetTripDate() {
        // Verifying the trip date
        assertNotNull(trip.getTripDate());
    }

    @Test
    public void testSetTripDate() {
        // Setting a new trip date
        LocalDateTime newTripDate = LocalDateTime.now().plusDays(1);
        trip.setTripDate(newTripDate);
        assertEquals(newTripDate, trip.getTripDate());
    }

    @Test
    public void testGetDriver() {
        // Verifying driver assignment
        assertNotNull(trip.getDriver());
        assertEquals(driver, trip.getDriver());
    }

    @Test
    public void testSetDriver() {
        // Setting a new driver
        Driver newDriver = new Driver();  // Assuming new driver instance
        trip.setDriver(newDriver);
        assertEquals(newDriver, trip.getDriver());
    }

    @Test
    public void testParseTripDate() {
        // Verifying the parseTripDate method
        String dateString = "2025-01-03T12:00:00";
        LocalDateTime parsedDate = trip.parseTripDate(dateString);
        assertNotNull(parsedDate);
        assertEquals(LocalDateTime.parse(dateString), parsedDate);
    }
}
