package com.controller;
 
import com.model.Driver;
import com.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.Optional;
 
@RestController
@RequestMapping("/api/drivers")
public class DriverController {
 
    @Autowired
    private DriverService driverService;
 
    // Search all drivers
    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }
 
    // Add new driver details
    @PostMapping
    public String addDriver(@RequestBody Driver driver) {
        driverService.addDriver(driver);
        return "Record Added Successfully";
    }
 
 
    
    @GetMapping("/{driverId}")
    public ResponseEntity<Optional<Driver>> getDriverById(@PathVariable Integer driverId) {
        Optional<Driver> driver = driverService.getDriverById(driverId); // Assume this method directly returns Driver
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/name/{driverName}")
    public ResponseEntity<Optional<Driver>> getDriverByName(@PathVariable String driverName) {
        Optional<Driver> driver = driverService.getDriverByName(driverName); // Assuming this method exists in your service layer
        if (driver.isPresent()) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
    // Update driver details
    @PutMapping("/{driverId}")
    public String updateDriver(@PathVariable Integer driverId, @RequestBody Driver updatedDriver) {
        Driver driver = driverService.updateDriver(driverId, updatedDriver);
        return driver != null ? "Record Updated Successfully" : "Driver not found";
    }
 
    // Delete driver details
    @DeleteMapping("/{driverId}")
    public String deleteDriver(@PathVariable Integer driverId) {
        return driverService.deleteDriver(driverId);
    }
 
 
    
 
    
}
