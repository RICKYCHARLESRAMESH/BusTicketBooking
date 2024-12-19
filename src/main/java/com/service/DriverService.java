package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.DriverDAO;
import com.model.Driver;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverDAO driverDAO;

    // Get all drivers
    public List<Driver> getAllDrivers() {
        return driverDAO.findAll();
    }

    // Get driver by ID
    public Optional<Driver> getDriverById(Integer driverId) {
        return driverDAO.findById(driverId);
    }

    // Add new driver
    public Driver addDriver(Driver driver) {
        return driverDAO.save(driver);
    }

    // Update driver details
    public Driver updateDriver(Integer driverId, Driver updatedDriver) {
        if (driverDAO.existsById(driverId)) {
            updatedDriver.setDriverId(driverId);
            return driverDAO.save(updatedDriver);
        }
        return null;
    }

    // Delete driver
    public String deleteDriver(Integer driverId) {
        if (driverDAO.existsById(driverId)) {
            driverDAO.deleteById(driverId);
            return "Record Deleted Successfully";
        }
        return "Driver not found";
    }

    // Get drivers by agency ID
    public List<Driver> getDriversByAgencyId(Integer agencyId) {
        return driverDAO.findByAgencyOfficeAgencyId(agencyId);
    }

    // Get drivers by office ID
    public List<Driver> getDriversByOfficeId(Integer officeId) {
        return driverDAO.findByAgencyOfficeOfficeId(officeId);
    }

    // Get address of the driver by driver ID
    public Driver getDriverAddressById(Integer driverId) {
        Optional<Driver> driver = driverDAO.findById(driverId);
        return driver.isPresent() ? driver.get() : null;
    }

    // Update address of the driver
    public String updateDriverAddress(Integer driverId, Driver updatedDriver) {
        if (driverDAO.existsById(driverId)) {
            updatedDriver.setDriverId(driverId);
            driverDAO.save(updatedDriver);
            return "Record Updated Successfully";
        }
        return "Driver not found";
    }
}
