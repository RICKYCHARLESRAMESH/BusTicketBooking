package com.modelTest;

import com.model.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteTest {

    private Route route;

    @BeforeEach
    public void setUp() {
        // Initialize the Route object before each test
        route = new Route(1, "CityA", "CityB", 3, 120);
    }

    @Test
    public void testGetRouteId() {
        assertEquals(1, route.getRouteId());
    }

    @Test
    public void testGetFromCity() {
        assertEquals("CityA", route.getFromCity());
    }

    @Test
    public void testGetToCity() {
        assertEquals("CityB", route.getToCity());
    }

    @Test
    public void testGetBreakPoints() {
        assertEquals(3, route.getBreakPoints());
    }

    @Test
    public void testGetDuration() {
        assertEquals(120, route.getDuration());
    }

    @Test
    public void testSetRouteId() {
        route.setRouteId(2);
        assertEquals(2, route.getRouteId());
    }

    @Test
    public void testSetFromCity() {
        route.setFromCity("NewCityA");
        assertEquals("NewCityA", route.getFromCity());
    }

    @Test
    public void testSetToCity() {
        route.setToCity("NewCityB");
        assertEquals("NewCityB", route.getToCity());
    }

    @Test
    public void testSetBreakPoints() {
        route.setBreakPoints(4);
        assertEquals(4, route.getBreakPoints());
    }

    @Test
    public void testSetDuration() {
        route.setDuration(150);
        assertEquals(150, route.getDuration());
    }
}
