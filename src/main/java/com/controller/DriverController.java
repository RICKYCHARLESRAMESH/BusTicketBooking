package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Driver;
import com.service.DriverService;

/**
* DriverController is a RESTful controller that manages HTTP requests related to drivers.
* It provides endpoints for retrieving, adding, updating, and deleting driver records.
* 
* URL: /api/drivers
*/
@RestController
@RequestMapping("/api/drivers")
public class DriverController {
 
    @Autowired
    private DriverService driverService;
 
    /**
     * Retrieves all drivers from the system.
     *
     * @return A list of Driver objects representing all drivers.
     */
    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }
 
    /**
     * Adds a new driver to the system.
     *
     * @param driver The Driver object containing the details of the driver to be added.
     * @return A confirmation message indicating that the record has been added successfully.
     */
    @PostMapping
    public String addDriver(@RequestBody Driver driver) {
        driverService.addDriver(driver);
        return "Record Added Successfully";
    }
 
    /**
     * Retrieves a driver by their unique identifier.
     *
     * @param driverId The unique identifier of the driver to be retrieved.
     * @return A ResponseEntity containing an Optional Driver object.
     *         If the driver is found, it returns the driver; otherwise, it returns a 404 Not Found response.
     */
    @GetMapping("/{driverId}")
    public ResponseEntity<Optional<Driver>> getDriverById(@PathVariable Integer driverId) {
        Optional<Driver> driver = driverService.getDriverById(driverId); // Assume this method directly returns Driver
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
    /**
     * Retrieves a driver by their name.
     *
     * @param driverName The name of the driver to be retrieved.
     * @return A ResponseEntity containing an Optional Driver object.
     *         If the driver is found, it returns the driver; otherwise, it returns a 404 Not Found response.
     */
    @GetMapping("/name/{driverName}")
    public ResponseEntity<Optional<Driver>> getDriverByName(@PathVariable String driverName) {
        Optional<Driver> driver = driverService.getDriverByName(driverName); // Assuming this method exists in your service layer
        if (driver.isPresent()) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
    /**
     * Updates the details of an existing driver.
     *
     * @param driverId The unique identifier of the driver to be updated.
     * @param updatedDriver The Driver object containing the updated details.
     * @return A confirmation message indicating whether the update was successful or if the driver was not found.
     */
    @PutMapping("/{driverId}")
    public String updateDriver(@PathVariable Integer driverId, @RequestBody Driver updatedDriver) {
        Driver driver = driverService.updateDriver(driverId, updatedDriver);
        return driver != null ? "Record Updated Successfully" : "Driver not found";
    }
 
    /**
     * Deletes a driver from the system.
     *
     * @param driverId The unique identifier of the driver to be deleted.
     * @return A message indicating the result of the deletion operation.
     */
    @DeleteMapping("/{driverId}")
    public String deleteDriver(@PathVariable Integer driverId) {
        return driverService.deleteDriver(driverId);
    }
}