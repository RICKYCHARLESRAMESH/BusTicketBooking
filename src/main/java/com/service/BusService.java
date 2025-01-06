package com.service;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Bus;
import com.dao.BusDAO;
 
/**
* BusService is a service class responsible for managing bus-related operations.
*/
@Service
public class BusService {
 
    @Autowired
    private BusDAO busDAO;
 
    /**
     * Adds a new bus to the database.
     *
     * @param bus The Bus object to be added.
     * @return The saved Bus object.
     */
    public Bus addBus(Bus bus) {
        return busDAO.save(bus);
    }
 
    /**
     * Retrieves all buses from the database.
     *
     * @return A list of all Bus objects.
     */
    public List<Bus> getAllBuses() {
        return busDAO.findAll();
    }
 
    /**
     * Updates an existing bus's details.
     *
     * @param busId The ID of the bus to update.
     * @param busDetails The new details for the bus.
     * @return The updated Bus object, or null if the bus was not found.
     */
    public Bus updateBus(Integer busId, Bus busDetails) {
        Bus existingBus = busDAO.findById(busId).orElse(null);
        if (existingBus != null) {
            existingBus.setAgencyOffice(busDetails.getAgencyOffice());
            existingBus.setRegistrationNumber(busDetails.getRegistrationNumber());
            existingBus.setCapacity(busDetails.getCapacity());
            existingBus.setType(busDetails.getType());
            return busDAO.save(existingBus);
        }
        return null;
    }
 
    /**
     * Retrieves a bus by its ID.
     *
     * @param busId The ID of the bus to retrieve.
     * @return The Bus object if found, or null if not found.
     */
    public Bus getBusById(Integer busId) {
        return busDAO.findById(busId).orElse(null);
    }
 
    /**
     * Deletes a bus by its ID.
     *
     * @param busId The ID of the bus to delete.
     */
    public void deleteBus(Integer busId) {
        busDAO.deleteById(busId);
    }
 
    /**
     * Retrieves all buses associated with a specific office ID.
     *
     * @param officeId The ID of the office to retrieve buses for.
     * @return A list of Bus objects associated with the specified office.
     */
    public List<Bus> getBusesByOfficeId(Integer officeId) {
        return busDAO.findByagencyOffice_OfficeId(officeId);
    }
}