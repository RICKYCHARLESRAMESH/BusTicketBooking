package com.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.DriverDAO;
import com.model.Driver;
 
import java.util.List;
import java.util.Optional;
 
/**
* DriverService provides methods to manage Driver entities,
* including CRUD operations and retrieval by specific criteria.
*/
@Service
public class DriverService {
 
    @Autowired
    private DriverDAO driverDAO;
 
    /**
     * Retrieves all drivers from the database.
     *
     * @return A list of all drivers.
     */
    public List<Driver> getAllDrivers() {
        return driverDAO.findAll();
    }
 
    /**
     * Retrieves a driver by their ID.
     *
     * @param driverId The ID of the driver.
     * @return An Optional containing the Driver if found, or empty if not.
     */
    public Optional<Driver> getDriverById(Integer driverId) {
        return driverDAO.findById(driverId);
    }
 
    /**
     * Adds a new driver to the database.
     *
     * @param driver The Driver object to be added.
     * @return The saved Driver object.
     */
    public Driver addDriver(Driver driver) {
        return driverDAO.save(driver);
    }
 
    /**
     * Updates the details of an existing driver.
     *
     * @param driverId      The ID of the driver to update.
     * @param updatedDriver The Driver object containing updated details.
     * @return The updated Driver object, or null if not found.
     */
    public Driver updateDriver(Integer driverId, Driver updatedDriver) {
        if (driverDAO.existsById(driverId)) {
            updatedDriver.setDriverId(driverId);
            return driverDAO.save(updatedDriver);
        }
        return null;
    }
 
    /**
     * Deletes a driver by their ID.
     *
     * @param driverId The ID of the driver to delete.
     * @return A message indicating the result of the operation.
     */
    public String deleteDriver(Integer driverId) {
        if (driverDAO.existsById(driverId)) {
            driverDAO.deleteById(driverId);
            return "Record Deleted Successfully";
        }
        return "Driver not found";
    }
 
    /**
     * Retrieves drivers associated with a specific agency ID.
     *
     * @param agencyId The ID of the agency.
     * @return A list of drivers linked to the specified agency.
     */
    public List<Driver> getDriversByAgencyId(Integer agencyId) {
        return driverDAO.findByAgencyOfficeAgencyAgencyId(agencyId);
    }
 
    /**
     * Retrieves drivers associated with a specific office ID.
     *
     * @param officeId The ID of the office.
     * @return A list of drivers linked to the specified office.
     */
    public List<Driver> getDriversByOfficeId(Integer officeId) {
        return driverDAO.findByAgencyOfficeOfficeId(officeId);
    }
 
    /**
     * Retrieves the address of a driver by their ID.
     *
     * @param driverId The ID of the driver.
     * @return The Driver object if found, or null if not.
     */
    public Driver getDriverAddressById(Integer driverId) {
        Optional<Driver> driver = driverDAO.findById(driverId);
        return driver.isPresent() ? driver.get() : null;
    }
 
    /**
     * Updates the address of a driver.
     *
     * @param driverId      The ID of the driver to update.
     * @param updatedDriver The Driver object containing updated address details.
     * @return A message indicating the result of the operation.
     */
    public String updateDriverAddress(Integer driverId, Driver updatedDriver) {
        if (driverDAO.existsById(driverId)) {
            updatedDriver.setDriverId(driverId);
            driverDAO.save(updatedDriver);
            return "Record Updated Successfully";
        }
        return "Driver not found";
    }
 
    /**
     * Retrieves a driver by their name.
     *
     * @param driverName The name of the driver.
     * @return An Optional containing the Driver if found, or empty if not.
     */
    public Optional<Driver> getDriverByName(String driverName) {
        // Example logic for fetching driver by name from the database
        return driverDAO.findByName(driverName); // Adjust based on your repository query methods
    }
}

