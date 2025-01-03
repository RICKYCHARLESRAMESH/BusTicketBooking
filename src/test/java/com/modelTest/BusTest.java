package com.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.model.AgencyOffice;
import com.model.Bus;
import com.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class BusTest {

    @Test
    void testDefaultConstructor() {
        Bus bus = new Bus();
        assertNotNull(bus, "The Bus object should be created successfully.");
    }

    @Test
    void testParameterizedConstructor() {
        AgencyOffice agencyOffice = new AgencyOffice();
        Bus bus = new Bus(1, agencyOffice, "AB123CD", 50, "Luxury");

        assertEquals(1, bus.getBusId(), "The bus ID should be set correctly.");
        assertEquals(agencyOffice, bus.getAgencyOffice(), "The agency office should be set correctly.");
        assertEquals("AB123CD", bus.getRegistrationNumber(), "The registration number should be set correctly.");
        assertEquals(50, bus.getCapacity(), "The capacity should be set correctly.");
        assertEquals("Luxury", bus.getType(), "The type should be set correctly.");
    }

    @Test
    void testSettersAndGetters() {
        Bus bus = new Bus();

        bus.setBusId(1);
        AgencyOffice agencyOffice = new AgencyOffice();
        bus.setAgencyOffice(agencyOffice);
        bus.setRegistrationNumber("XY987ZT");
        bus.setCapacity(40);
        bus.setType("Economy");

        List<Trip> trips = new ArrayList<>();
        trips.add(new Trip());
        bus.setTrip(trips);

        assertEquals(1, bus.getBusId(), "The bus ID should be set and retrieved correctly.");
        assertEquals(agencyOffice, bus.getAgencyOffice(), "The agency office should be set and retrieved correctly.");
        assertEquals("XY987ZT", bus.getRegistrationNumber(), "The registration number should be set and retrieved correctly.");
        assertEquals(40, bus.getCapacity(), "The capacity should be set and retrieved correctly.");
        assertEquals("Economy", bus.getType(), "The type should be set and retrieved correctly.");
        assertEquals(trips, bus.getTrip(), "The trips should be set and retrieved correctly.");
    }

    @Test
    void testNullValues() {
        Bus bus = new Bus();

        bus.setBusId(null);
        bus.setAgencyOffice(null);
        bus.setRegistrationNumber(null);
        bus.setCapacity(null);
        bus.setType(null);
        bus.setTrip(null);

        assertNull(bus.getBusId(), "The bus ID should allow null values.");
        assertNull(bus.getAgencyOffice(), "The agency office should allow null values.");
        assertNull(bus.getRegistrationNumber(), "The registration number should allow null values.");
        assertNull(bus.getCapacity(), "The capacity should allow null values.");
        assertNull(bus.getType(), "The type should allow null values.");
        assertNull(bus.getTrip(), "The trip list should allow null values.");
    }

    @Test
    void testEmptyValues() {
        Bus bus = new Bus();

        bus.setRegistrationNumber("");
        bus.setType("");

        assertEquals("", bus.getRegistrationNumber(), "The registration number should allow empty values.");
        assertEquals("", bus.getType(), "The type should allow empty values.");
    }

    @Test
    void testTripRelationship() {
        Bus bus = new Bus();
        List<Trip> trips = new ArrayList<>();
        Trip trip1 = new Trip();
        Trip trip2 = new Trip();
        trips.add(trip1);
        trips.add(trip2);

        bus.setTrip(trips);

        assertEquals(2, bus.getTrip().size(), "The trips list should contain the correct number of trips.");
        assertTrue(bus.getTrip().contains(trip1), "The trips list should contain trip1.");
        assertTrue(bus.getTrip().contains(trip2), "The trips list should contain trip2.");
    }
}
