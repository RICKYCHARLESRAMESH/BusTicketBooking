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

    // Search driver details by driver id
    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Integer driverId) {
        Optional<Driver> driver = driverService.getDriverById(driverId);
        return driver.isPresent() ? ResponseEntity.ok(driver.get()) : ResponseEntity.notFound().build();
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

    // Search drivers by agency id
    @GetMapping("/agency/{agencyId}")
    public List<Driver> getDriversByAgencyId(@PathVariable Integer agencyId) {
        return driverService.getDriversByAgencyId(agencyId);
    }

    // Search drivers by office id
    @GetMapping("/office/{officeId}")
    public List<Driver> getDriversByOfficeId(@PathVariable Integer officeId) {
        return driverService.getDriversByOfficeId(officeId);
    }

    // Search address of the driver by driver id
    @GetMapping("/address/{driverId}")
    public ResponseEntity<Driver> getDriverAddressById(@PathVariable Integer driverId) {
        Driver driver = driverService.getDriverAddressById(driverId);
        return driver != null ? ResponseEntity.ok(driver) : ResponseEntity.notFound().build();
    }

    // Update address details of the driver
    @PutMapping("/address/{driverId}")
    public String updateDriverAddress(@PathVariable Integer driverId, @RequestBody Driver updatedDriver) {
        return driverService.updateDriverAddress(driverId, updatedDriver);
    }
}
