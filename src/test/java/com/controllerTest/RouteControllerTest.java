package com.controllerTest;

import com.model.Route;
import com.service.RouteService;
import com.controller.RouteController;
import com.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RouteControllerTest {

    @InjectMocks
    private RouteController routeController;

    @Mock
    private RouteService routeService;

    private Route route;

    @BeforeEach
    public void setUp() {
        route = new Route();
        route.setRouteId(1);
        route.setFromCity("City A");
        route.setToCity("City B");
    }

    @Test
    public void createRoute_shouldReturnCreatedStatus_whenRouteIsValid() {
        // Arrange
        when(routeService.save(any(Route.class))).thenReturn(route);

        // Act
        ResponseEntity<String> response = routeController.createRoute(route);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Record Created Successfully", response.getBody());
    }

    @Test
    public void createRoute_shouldThrowException_whenRouteIsInvalid() {
        // Arrange
        Route invalidRoute = new Route();
        invalidRoute.setFromCity(null);  // Missing fromCity

        // Act and Assert
        CustomException exception = assertThrows(CustomException.class, () -> {
            routeController.createRoute(invalidRoute);
        });

        assertEquals("POSTFAILS", exception.getCode());
        assertEquals("Route data is missing or invalid", exception.getMessage());
    }

    @Test
    public void getRouteById_shouldReturnRoute_whenRouteExists() {
        // Arrange
        when(routeService.findByRouteId(1)).thenReturn(Optional.of(route));

        // Act
        ResponseEntity<Route> response = routeController.getRouteById(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(route.getRouteId(), response.getBody().getRouteId());
    }

    @Test
    public void getRouteById_shouldThrowException_whenRouteDoesNotExist() {
        // Arrange
        when(routeService.findByRouteId(1)).thenReturn(Optional.empty());

        // Act and Assert
        CustomException exception = assertThrows(CustomException.class, () -> {
            routeController.getRouteById(1);
        });

        assertEquals("NOTFOUND", exception.getCode());
        assertEquals("Route not found with ID: 1", exception.getMessage());
    }

    @Test
    public void getRoutesByFromCity_shouldReturnRoutes_whenRoutesExist() {
        // Arrange
        when(routeService.findByFromCity("City A")).thenReturn(Arrays.asList(route));

        // Act
        ResponseEntity<List<Route>> response = routeController.getRoutesByFromCity("City A");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void getRoutesByFromCity_shouldThrowException_whenNoRoutesFound() {
        // Arrange
        when(routeService.findByFromCity("City A")).thenReturn(Arrays.asList());

        // Act and Assert
        CustomException exception = assertThrows(CustomException.class, () -> {
            routeController.getRoutesByFromCity("City A");
        });

        assertEquals("NOTFOUND", exception.getCode());
        assertEquals("No routes found for from city: City A", exception.getMessage());
    }

    @Test
    public void updateRoute_shouldReturnSuccess_whenRouteIsUpdated() {
        // Arrange
        when(routeService.save(any(Route.class))).thenReturn(route);

        // Act
        ResponseEntity<String> response = routeController.updateRoute(route);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Record Updated Successfully", response.getBody());
    }

    @Test
    public void updateRoute_shouldThrowException_whenRouteDataIsInvalid() {
        // Arrange
        Route invalidRoute = new Route();
        invalidRoute.setRouteId(null);  // Missing routeId

        // Act and Assert
        CustomException exception = assertThrows(CustomException.class, () -> {
            routeController.updateRoute(invalidRoute);
        });

        assertEquals("UPDATEFAILS", exception.getCode());
        assertEquals("Route details or route ID cannot be null", exception.getMessage());
    }

    @Test
    public void deleteRoute_shouldReturnSuccess_whenRouteIsDeleted() {
        // Arrange
        doNothing().when(routeService).deleteByRouteId(1);

        // Act
        ResponseEntity<String> response = routeController.deleteRoute(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Record Deleted Successfully", response.getBody());
    }

    @Test
    public void deleteRoute_shouldThrowException_whenRouteIdIsInvalid() {
        // Act and Assert
        CustomException exception = assertThrows(CustomException.class, () -> {
            routeController.deleteRoute(-1); // Invalid route ID
        });

        assertEquals("INVALIDID", exception.getCode());
        assertEquals("Invalid route ID: -1", exception.getMessage());
    }
}
