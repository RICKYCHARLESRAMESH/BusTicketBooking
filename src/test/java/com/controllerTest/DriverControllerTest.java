//package com.controllerTest;
//
//import com.controller.DriverController;
//import com.model.Driver;
//import com.service.DriverService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class DriverControllerTest {
//
//    @Mock
//    private DriverService driverService;
//
//    @InjectMocks
//    private DriverController driverController;
//
//    private Driver driver;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        driver = new Driver();
//        driver.setDriverId(1);
//        driver.setLicenseNumber("XYZ123");
//        driver.setName("John Doe");
//        driver.setPhone("1234567890");
//    }
//
//    @Test
//    public void testGetDriverById_Success() {
//        when(driverService.getDriverById(1)).thenReturn(Optional.of(driver));
//
//        ResponseEntity<Optional<Driver>> response = driverController.getDriverById(1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(response.getBody().isPresent());
//        assertEquals(driver.getDriverId(), response.getBody().get().getDriverId());
//    }
//
//    @Test
//    public void testGetDriverById_NotFound() {
//        when(driverService.getDriverById(1)).thenReturn(Optional.empty());
//
//        ResponseEntity<Optional<Driver>> response = driverController.getDriverById(1);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    public void testAddDriver() {
//        when(driverService.addDriver(driver)).thenReturn("Record Added Successfully");
//
//        String response = driverController.addDriver(driver);
//
//        assertEquals("Record Added Successfully", response);
//    }
//
//    @Test
//    public void testUpdateDriver_Success() {
//        when(driverService.updateDriver(1, driver)).thenReturn(driver);
//
//        String response = driverController.updateDriver(1, driver);
//
//        assertEquals("Record Updated Successfully", response);
//    }
//
//    @Test
//    public void testUpdateDriver_NotFound() {
//        when(driverService.updateDriver(1, driver)).thenReturn(null);
//
//        String response = driverController.updateDriver(1, driver);
//
//        assertEquals("Driver not found", response);
//    }
//
//    @Test
//    public void testDeleteDriver() {
//        when(driverService.deleteDriver(1)).thenReturn("Driver Deleted Successfully");
//
//        String response = driverController.deleteDriver(1);
//
//        assertEquals("Driver Deleted Successfully", response);
//    }
//
//    @Test
//    public void testGetDriversByAgencyId() {
//        when(driverService.getDriversByAgencyId(1)).thenReturn(List.of(driver));
//
//        List<Driver> drivers = driverController.getDriversByAgencyId(1);
//
//        assertNotNull(drivers);
//        assertFalse(drivers.isEmpty());
//        assertEquals(1, drivers.size());
//    }
//
//    @Test
//    public void testGetDriversByOfficeId() {
//        when(driverService.getDriversByOfficeId(1)).thenReturn(List.of(driver));
//
//        List<Driver> drivers = driverController.getDriversByOfficeId(1);
//
//        assertNotNull(drivers);
//        assertFalse(drivers.isEmpty());
//        assertEquals(1, drivers.size());
//    }
//
//    @Test
//    public void testGetDriverAddressById_Success() {
//        when(driverService.getDriverAddressById(1)).thenReturn(driver);
//
//        ResponseEntity<Driver> response = driverController.getDriverAddressById(1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//    }
//
//    @Test
//    public void testGetDriverAddressById_NotFound() {
//        when(driverService.getDriverAddressById(1)).thenReturn(null);
//
//        ResponseEntity<Driver> response = driverController.getDriverAddressById(1);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    public void testUpdateDriverAddress() {
//        when(driverService.updateDriverAddress(1, driver)).thenReturn("Driver Address Updated");
//
//        String response = driverController.updateDriverAddress(1, driver);
//
//        assertEquals("Driver Address Updated", response);
//    }
//}
