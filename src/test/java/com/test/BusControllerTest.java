package com.test;

import com.controller.BusController;
import com.model.Bus;
import com.service.BusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
 
class BusControllerTest {
 
    @InjectMocks
    private BusController busController;
 
    @Mock
    private BusService busService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testCreateBus() {
        Bus bus = new Bus(); // Create a Bus instance
 
        ResponseEntity<String> response = busController.createBus(bus);
 
        assertEquals("Record Created Successfully", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(busService, times(1)).addBus(bus);
    }
 
    @Test
    void testGetAllBuses() {
        Bus bus1 = new Bus();
        Bus bus2 = new Bus();
        List<Bus> buses = Arrays.asList(bus1, bus2);
 
        when(busService.getAllBuses()).thenReturn(buses);
 
        ResponseEntity<List<Bus>> response = busController.getAllBuses();
 
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(busService, times(1)).getAllBuses();
    }
 
    @Test
    void testUpdateBusFound() {
        Bus updatedBus = new Bus();
        when(busService.updateBus(anyInt(), any())).thenReturn(updatedBus);
 
        ResponseEntity<String> response = busController.updateBus(1, updatedBus);
 
        assertEquals("Record Updated Successfully", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(busService, times(1)).updateBus(1, updatedBus);
    }
 
    @Test
    void testUpdateBusNotFound() {
        Bus updatedBus = new Bus();
        when(busService.updateBus(anyInt(), any())).thenReturn(null);
 
        ResponseEntity<String> response = busController.updateBus(1, updatedBus);
 
        assertEquals("Record Not Found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(busService, times(1)).updateBus(1, updatedBus);
    }
 
    @Test
    void testGetBusByIdFound() {
        Bus bus = new Bus();
        when(busService.getBusById(anyInt())).thenReturn(bus);
 
        ResponseEntity<Bus> response = busController.getBusById(1);
 
        assertEquals(bus, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(busService, times(1)).getBusById(1);
    }
 
    @Test
    void testGetBusByIdNotFound() {
        when(busService.getBusById(anyInt())).thenReturn(null);
 
        ResponseEntity<Bus> response = busController.getBusById(1);
 
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(busService, times(1)).getBusById(1);
    }
 
    @Test
    void testDeleteBusFound() {
        Bus bus = new Bus();
        when(busService.getBusById(anyInt())).thenReturn(bus);
 
        ResponseEntity<String> response = busController.deleteBus(1);
 
        assertEquals("Record Deleted Successfully", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(busService, times(1)).deleteBus(1);
    }
 
    @Test
    void testDeleteBusNotFound() {
        when(busService.getBusById(anyInt())).thenReturn(null);
 
        ResponseEntity<String> response = busController.deleteBus(1);
 
        assertEquals("Record Not Found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(busService, times(0)).deleteBus(1);
    }
 
    @Test
    void testGetBusesByOfficeId() {
        Bus bus1 = new Bus();
        List<Bus> buses = Arrays.asList(bus1);
 
        when(busService.getBusesByOfficeId(anyInt())).thenReturn(buses);
 
        ResponseEntity<List<Bus>> response = busController.getBusesByOfficeId(1);
 
        assertEquals(1, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(busService, times(1)).getBusesByOfficeId(1);
    }
}
