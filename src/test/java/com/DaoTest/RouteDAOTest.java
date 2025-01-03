package com.DaoTest;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.dao.RouteDAO;
import com.model.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.ArrayList;
import java.util.List;
 
@ExtendWith(MockitoExtension.class)
public class RouteDAOTest {
 
    @Mock
    private RouteDAO routeDAO;
 
    private Route route1;
    private Route route2;
 
    @BeforeEach
    public void setUp() {
        // Create sample Route objects
        route1 = new Route();
        route1.setRouteId(1);
        route1.setFromCity("CityA");
        route1.setToCity("CityB");
 
        route2 = new Route();
        route2.setRouteId(2);
        route2.setFromCity("CityA");
        route2.setToCity("CityC");
    }
 
    @Test
    public void testFindByFromCity() {
        // Prepare mock behavior
        List<Route> routes = new ArrayList<>();
        routes.add(route1);
        when(routeDAO.findByFromCity("CityA")).thenReturn(routes);
 
        // Test method
        List<Route> foundRoutes = routeDAO.findByFromCity("CityA");
 
        // Verify results
        assertNotNull(foundRoutes);
        assertEquals(1, foundRoutes.size());
        assertEquals(route1.getRouteId(), foundRoutes.get(0).getRouteId());
 
        // Verify mock interaction
        verify(routeDAO, times(1)).findByFromCity("CityA");
    }
 
    @Test
    public void testFindByToCity() {
        // Prepare mock behavior
        List<Route> routes = new ArrayList<>();
        routes.add(route2);
        when(routeDAO.findByToCity("CityC")).thenReturn(routes);
 
        // Test method
        List<Route> foundRoutes = routeDAO.findByToCity("CityC");
 
        // Verify results
        assertNotNull(foundRoutes);
        assertEquals(1, foundRoutes.size());
        assertEquals(route2.getRouteId(), foundRoutes.get(0).getRouteId());
 
        // Verify mock interaction
        verify(routeDAO, times(1)).findByToCity("CityC");
    }
 
    @Test
    public void testFindByFromCityAndToCity() {
        // Prepare mock behavior
        when(routeDAO.findByFromCityAndToCity("CityA", "CityB")).thenReturn(List.of(route1));
 
        // Test method
        List<Route> foundRoutes = routeDAO.findByFromCityAndToCity("CityA", "CityB");
 
        // Verify results
        assertNotNull(foundRoutes);
        assertEquals(1, foundRoutes.size());
        assertEquals(route1.getRouteId(), foundRoutes.get(0).getRouteId());
 
        // Verify mock interaction
        verify(routeDAO, times(1)).findByFromCityAndToCity("CityA", "CityB");
    }
}