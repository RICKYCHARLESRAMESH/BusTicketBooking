package com.serviceTest;
 
import static org.junit.jupiter.api.Assertions.*;
import com.dao.DriverDAO;
import com.model.Driver;
import com.service.DriverService;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
 
import org.junit.jupiter.api.Test;
 
class DriverServiceTest {
	@InjectMocks
    private DriverService driverService;
 
    @Mock
    private DriverDAO driverDAO;
 
    private Driver driver;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        driver = new Driver();
        driver.setDriverId(1);
        // Set other properties of driver as needed
    }
 
    @Test
    void testGetAllDrivers() {
        when(driverDAO.findAll()).thenReturn(Arrays.asList(driver));
 
        List<Driver> drivers = driverService.getAllDrivers();
        assertNotNull(drivers);
        assertEquals(1, drivers.size());
        assertEquals(driver, drivers.get(0));
    }
 
    @Test
    void testGetDriverById() {
        when(driverDAO.findById(1)).thenReturn(Optional.of(driver));
 
        Optional<Driver> foundDriver = driverService.getDriverById(1);
        assertTrue(foundDriver.isPresent());
        assertEquals(driver, foundDriver.get());
    }
 
    @Test
    void testAddDriver() {
        when(driverDAO.save(any(Driver.class))).thenReturn(driver);
 
        Driver addedDriver = driverService.addDriver(driver);
        assertNotNull(addedDriver);
        assertEquals(driver, addedDriver);
    }
 
    @Test
    void testUpdateDriver_Success() {
        when(driverDAO.existsById(1)).thenReturn(true);
        when(driverDAO.save(any(Driver.class))).thenReturn(driver);
 
        Driver updatedDriver = driverService.updateDriver(1, driver);
        assertNotNull(updatedDriver);
        assertEquals(driver.getDriverId(), updatedDriver.getDriverId());
    }
 
    @Test
    void testUpdateDriver_Failure() {
        when(driverDAO.existsById(1)).thenReturn(false);
 
        Driver updatedDriver = driverService.updateDriver(1, driver);
        assertNull(updatedDriver);
    }
 
    @Test
    void testDeleteDriver_Success() {
        when(driverDAO.existsById(1)).thenReturn(true);
 
        String response = driverService.deleteDriver(1);
        assertEquals("Record Deleted Successfully", response);
        verify(driverDAO).deleteById(1);
    }
 
    @Test
    void testDeleteDriver_Failure() {
        when(driverDAO.existsById(1)).thenReturn(false);
 
        String response = driverService.deleteDriver(1);
        assertEquals("Driver not found", response);
        verify(driverDAO, never()).deleteById(anyInt());
    }
 
    @Test
    void testGetDriversByAgencyId() {
        when(driverDAO.findByAgencyOfficeAgencyAgencyId(1)).thenReturn(Arrays.asList(driver));
 
        List<Driver> drivers = driverService.getDriversByAgencyId(1);
        assertNotNull(drivers);
        assertEquals(1, drivers.size());
        assertEquals(driver, drivers.get(0));
    }
 
    @Test
    void testGetDriversByOfficeId() {
        when(driverDAO.findByAgencyOfficeOfficeId(1)).thenReturn(Arrays.asList(driver));
 
        List<Driver> drivers = driverService.getDriversByOfficeId(1);
        assertNotNull(drivers);
        assertEquals(1, drivers.size());
        assertEquals(driver, drivers.get(0));
    }
 
    @Test
    void testGetDriverAddressById() {
        when(driverDAO.findById(1)).thenReturn(Optional.of(driver));
 
        Driver foundDriver = driverService.getDriverAddressById(1);
        assertNotNull(foundDriver);
        assertEquals(driver.getDriverId(), foundDriver.getDriverId());
    }
 
    @Test
    void testGetDriverAddressById_NotFound() {
        when(driverDAO.findById(1)).thenReturn(Optional.empty());
 
        Driver foundDriver = driverService.getDriverAddressById(1);
        assertNull(foundDriver);
    }
 
    @Test
    void testUpdateDriverAddress_Success() {
        when(driverDAO.existsById(1)).thenReturn(true);
        when(driverDAO.save(any(Driver.class))).thenReturn(driver);
 
        String response = driverService.updateDriverAddress(1, driver);
        assertEquals("Record Updated Successfully", response);
    }
 
    @Test
    void testUpdateDriverAddress_Failure() {
        when(driverDAO.existsById(1)).thenReturn(false);
 
        String response = driverService.updateDriverAddress(1, driver);
        assertEquals("Driver not found", response);
    }
}