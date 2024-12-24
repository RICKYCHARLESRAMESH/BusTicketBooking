package com.controllerTest;
 
 
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import com.controller.RouteController;
import com.model.Route;
import com.service.RouteService;
 
class RouteControllerTest {
 
    @InjectMocks
    private RouteController routeController;
 
    @Mock
    private RouteService routeService;
 
    private Route route;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        route = new Route(1, "CityA", "CityB", 2, 180);
    }
 
    @Test
//    void testCreateRoute() {
//        doNothing().when(routeService).save(route);
//
//        ResponseEntity<String> response = routeController.createRoute(route);
//
//        assertEquals(201, response.getStatusCodeValue());
//        assertEquals("Record Created Successfully", response.getBody());
//        verify(routeService, times(1)).save(route);
//    }
   
    void testCreateRoute() {
        // Mock the behavior for a non-void method
        when(routeService.save(route)).thenReturn(route);
 
        // Call the controller method
        ResponseEntity<String> response = routeController.createRoute(route);
 
        // Assertions
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Record Created Successfully", response.getBody());
 
        // Verify that save was called once
        verify(routeService, times(1)).save(route);
    }
 
 
  
    
    
    @Test
    void testGetRouteById() {
        when(routeService.findByRouteId(1)).thenReturn(Optional.of(route));
 
        ResponseEntity<Route> response = routeController.getRouteById(1);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(route, response.getBody());
        verify(routeService, times(1)).findByRouteId(1);
    }
 
    @Test
    void testGetRoutesByFromCity() {
        when(routeService.findByFromCity("CityA")).thenReturn(Arrays.asList(route));
 
        List<Route> routes = routeController.getRoutesByFromCity("CityA");
 
        assertEquals(1, routes.size());
        assertEquals(route, routes.get(0));
        verify(routeService, times(1)).findByFromCity("CityA");
    }
 
    @Test
    void testGetRoutesByToCity() {
        when(routeService.findByToCity("CityB")).thenReturn(Arrays.asList(route));
 
        List<Route> routes = routeController.getRoutesByToCity("CityB");
 
        assertEquals(1, routes.size());
        assertEquals(route, routes.get(0));
        verify(routeService, times(1)).findByToCity("CityB");
    }
 
    @Test
    void testGetRouteByFromCityAndToCity() {
        when(routeService.findByFromCityAndToCity("CityA", "CityB"))
                .thenReturn(Arrays.asList(route));
 
        ResponseEntity<List<Route>> response = routeController.getRouteByFromCityAndToCity("CityA", "CityB");
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(route, response.getBody().get(0));
        verify(routeService, times(1)).findByFromCityAndToCity("CityA", "CityB");
    }
 
    @Test
    void testUpdateRoute() {
        // Mock the behavior of the routeService.save method
        when(routeService.save(route)).thenReturn(route);
 
        // Call the controller method
        ResponseEntity<String> response = routeController.updateRoute(route);
 
        // Assertions
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Record Updated Successfully", response.getBody());
 
        // Verify that the save method was called once
        verify(routeService, times(1)).save(route);
    }
 
    @Test
    void testDeleteRoute() {
        doNothing().when(routeService).deleteByRouteId(1);
 
        ResponseEntity<String> response = routeController.deleteRoute(1);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Record Deleted Successfully", response.getBody());
        verify(routeService, times(1)).deleteByRouteId(1);
    }
}