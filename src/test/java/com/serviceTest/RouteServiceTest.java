package com.serviceTest;

import com.dao.RouteDAO;
import com.model.Route;
import com.service.RouteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RouteServiceTest {

    @Mock
    private RouteDAO routeRepo;

    @InjectMocks
    private RouteService routeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRoute() {
        Route route = new Route(1, "CityA", "CityB", 2, 5);
        when(routeRepo.save(route)).thenReturn(route);

        Route result = routeService.save(route);

        assertNotNull(result);
        assertEquals(1, result.getRouteId());
        assertEquals("CityA", result.getFromCity());
        assertEquals("CityB", result.getToCity());
        verify(routeRepo, times(1)).save(route);
    }

    @Test
    void testFindByRouteId() {
        Route route = new Route(1, "CityA", "CityB", 2, 5);
        when(routeRepo.findById(1)).thenReturn(Optional.of(route));

        Optional<Route> result = routeService.findByRouteId(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getRouteId());
        verify(routeRepo, times(1)).findById(1);
    }

    @Test
    void testFindByFromCity() {
        List<Route> routes = Arrays.asList(
                new Route(1, "CityA", "CityB", 2, 5),
                new Route(2, "CityA", "CityC", 1, 3)
        );
        when(routeRepo.findByFromCity("CityA")).thenReturn(routes);

        List<Route> result = routeService.findByFromCity("CityA");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("CityA", result.get(0).getFromCity());
        verify(routeRepo, times(1)).findByFromCity("CityA");
    }

    @Test
    void testFindByToCity() {
        List<Route> routes = Arrays.asList(
                new Route(1, "CityA", "CityB", 2, 5),
                new Route(2, "CityC", "CityB", 1, 3)
        );
        when(routeRepo.findByToCity("CityB")).thenReturn(routes);

        List<Route> result = routeService.findByToCity("CityB");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("CityB", result.get(0).getToCity());
        verify(routeRepo, times(1)).findByToCity("CityB");
    }

    @Test
    void testFindByFromCityAndToCity() {
        List<Route> routes = Arrays.asList(
                new Route(1, "CityA", "CityB", 2, 5)
        );
        when(routeRepo.findByFromCityAndToCity("CityA", "CityB")).thenReturn(routes);

        List<Route> result = routeService.findByFromCityAndToCity("CityA", "CityB");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CityA", result.get(0).getFromCity());
        assertEquals("CityB", result.get(0).getToCity());
        verify(routeRepo, times(1)).findByFromCityAndToCity("CityA", "CityB");
    }

   
}
