package com.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.dao.AgencyDAO;
import com.model.Agency;
import com.model.AgencyOffice;
 
/**
* AgencyService is a service class responsible for managing agency-related operations.
*/
@Service
public class AgencyService {
 
    @Autowired
    private AgencyDAO agencyDAO;
 
    /**
     * Gets the AgencyDAO instance used for database operations.
     *
     * @return The AgencyDAO instance.
     */
    public AgencyDAO getAgenciesRepository() {
        return agencyDAO;
    }
 
    /**
     * Sets the AgencyDAO instance for this service.
     *
     * @param agenciesRepository The AgencyDAO instance to set.
     */
    public void setAgenciesRepository(AgencyDAO agenciesRepository) {
        this.agencyDAO = agenciesRepository;
    }
 
    /**
     * Adds a new agency.
     *
     * @param agency The agency to be added.
     * @return A success message.
     */
    public String addAgency(Agency agency) {
        agencyDAO.save(agency);  // Save the new agency
        return "Record Created Successfully";
    }
 
    /**
     * Retrieves an agency by its ID.
     *
     * @param agencyId The ID of the agency.
     * @return An Optional containing the agency if found, otherwise empty.
     */
    public Optional<Agency> getAgencyById(int agencyId) {
        return agencyDAO.findById(agencyId);  // Match the return type with the interface
    }
 
    /**
     * Updates an existing agency.
     *
     * @param agencyId The ID of the agency to update.
     * @param agency The updated agency data.
     * @return A success message or an error message if not found.
     */
    public String updateAgency(int agencyId, Agency agency) {
        if (!agencyDAO.existsById(agencyId)) {  // Match the method signature
            return "Agency not found!";
        }
        agency.setAgencyId(agencyId);  
        agencyDAO.save(agency);  
        return "Record Updated Successfully";
    }
 
    /**
     * Retrieves a list of offices associated with a given agency ID.
     *
     * @param agencyId The ID of the agency.
     * @return A list of AgencyOffice associated with the agency, or null if not found.
     */
    public List<AgencyOffice> getOfficesByAgencyId(int agencyId) {
        Optional<Agency> agency = agencyDAO.findById(agencyId);
        if (agency.isEmpty()) {
            return null;
        }
        return agency.get().getOffices();  // Get offices for the agency
    }
 
    /**
     * Retrieves a specific office by its ID within a given agency.
     *
     * @param agencyId The ID of the agency.
     * @param officeId The ID of the office.
     * @return The AgencyOffice if found, otherwise null.
     */
    public AgencyOffice getOfficeById(int agencyId, int officeId) {
        Optional<Agency> agency = agencyDAO.findById(agencyId);
        if (agency.isEmpty()) {
            return null;
        }
        return agency.get().getOffices().stream()
                .filter(office -> office.getOfficeId() == officeId)
                .findFirst()
                .orElse(null);
    }
 
    /**
     * Retrieves all agencies.
     *
     * @return A list of all agencies.
     */
    public List<Agency> getAllAgencies() {
        return agencyDAO.findAll(); // Assuming you're using JPA, this returns a list of all agencies
    }
 
    /**
     * Retrieves an agency by its name.
     *
     * @param agencyName The name of the agency.
     * @return An Optional containing the agency if found, otherwise empty.
     */
    public Optional<Agency> getAgencyByName(String agencyName) {
        return agencyDAO.findByName(agencyName);  // Assuming a JPA repository or similar.
    }
 
    /**
     * Retrieves an agency by the contact person's name.
     *
     * @param contactPersonName The name of the contact person.
     * @return An Optional containing the agency if found, otherwise empty.
     */
    public Optional<Agency> getAgencyByContactPersonName(String contactPersonName) {
        return agencyDAO.findByContactPersonName(contactPersonName);
    }
}
