  
package com.controllerTest;
 
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import com.controller.DriverController;
import com.model.Driver;
import com.service.DriverService;
 
 
 
public class DriverControllerTest {
	@InjectMocks
	private DriverController driverController;
	
	@Mock
	private DriverService driverService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetAllDrivers() {
		Driver driver1=new Driver();
		Driver driver2=new Driver();
		List<Driver> drivers = Arrays.asList(driver1, driver2);
        
        when(driverService.getAllDrivers()).thenReturn(drivers);
 
        List<Driver> result = driverController.getAllDrivers();
 
        assertEquals(2, result.size());
        verify(driverService, times(1)).getAllDrivers();
    }
 
    @Test
    void testAddDriver() {
        Driver driver = new Driver(); // Create a driver instance
 
        String result = driverController.addDriver(driver);
 
        assertEquals("Record Added Successfully", result);
        verify(driverService, times(1)).addDriver(driver);
    }
 
    @Test
    void testGetDriverByIdFound() {
        Driver driver = new Driver();
        when(driverService.getDriverById(anyInt())).thenReturn(Optional.of(driver));
 
        ResponseEntity<Driver> response = driverController.getDriverById(1);
 
        assertEquals(ResponseEntity.ok(driver), response);
        verify(driverService, times(1)).getDriverById(1);
    }
 
    @Test
    void testGetDriverByIdNotFound() {
        when(driverService.getDriverById(anyInt())).thenReturn(Optional.empty());
 
        ResponseEntity<Driver> response = driverController.getDriverById(1);
 
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(driverService, times(1)).getDriverById(1);
    }
 
    @Test
    void testUpdateDriverFound() {
        Driver updatedDriver = new Driver();
        when(driverService.updateDriver(anyInt(), any())).thenReturn(updatedDriver);
 
        String result = driverController.updateDriver(1, updatedDriver);
 
        assertEquals("Record Updated Successfully", result);
        verify(driverService, times(1)).updateDriver(1, updatedDriver);
    }
 
    @Test
    void testUpdateDriverNotFound() {
        Driver updatedDriver = new Driver();
        when(driverService.updateDriver(anyInt(), any())).thenReturn(null);
 
        String result = driverController.updateDriver(1, updatedDriver);
 
        assertEquals("Driver not found", result);
        verify(driverService, times(1)).updateDriver(1, updatedDriver);
    }
 
    @Test
    void testDeleteDriver() {
        when(driverService.deleteDriver(anyInt())).thenReturn("Record Deleted Successfully");
 
        String result = driverController.deleteDriver(1);
 
        assertEquals("Record Deleted Successfully", result);
        verify(driverService, times(1)).deleteDriver(1);
    }
 
    @Test
    void testGetDriversByAgencyId() {
        Driver driver1 = new Driver();
        List<Driver> drivers = Arrays.asList(driver1);
        
        when(driverService.getDriversByAgencyId(anyInt())).thenReturn(drivers);
 
        List<Driver> result = driverController.getDriversByAgencyId(1);
 
        assertEquals(1, result.size());
        verify(driverService, times(1)).getDriversByAgencyId(1);
    }
 
    @Test
    void testGetDriversByOfficeId() {
        Driver driver1 = new Driver();
        List<Driver> drivers = Arrays.asList(driver1);
        
        when(driverService.getDriversByOfficeId(anyInt())).thenReturn(drivers);
 
        List<Driver> result = driverController.getDriversByOfficeId(1);
 
        assertEquals(1, result.size());
        verify(driverService, times(1)).getDriversByOfficeId(1);
    }
 
    @Test
    void testGetDriverAddressByIdFound() {
        Driver driver = new Driver();
        when(driverService.getDriverAddressById(anyInt())).thenReturn(driver);
 
        ResponseEntity<Driver> response = driverController.getDriverAddressById(1);
 
        assertEquals(ResponseEntity.ok(driver), response);
        verify(driverService, times(1)).getDriverAddressById(1);
    }
 
    @Test
    void testGetDriverAddressByIdNotFound() {
        when(driverService.getDriverAddressById(anyInt())).thenReturn(null);
 
        ResponseEntity<Driver> response = driverController.getDriverAddressById(1);
 
        assertEquals(ResponseEntity.notFound().build(), response);
        verify(driverService, times(1)).getDriverAddressById(1);
    }
 
    @Test
    void testUpdateDriverAddress() {
        Driver updatedDriver = new Driver();
        when(driverService.updateDriverAddress(anyInt(), any())).thenReturn("Address Updated Successfully");
 
        String result = driverController.updateDriverAddress(1, updatedDriver);
 
        assertEquals("Address Updated Successfully", result);
        verify(driverService, times(1)).updateDriverAddress(1, updatedDriver);
    }
}
 