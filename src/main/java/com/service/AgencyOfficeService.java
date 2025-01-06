package com.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.dao.AgencyOfficeDAO;
 
/**
* AgencyOfficeService is a service class responsible for managing agency office operations.
*/
@Service
public class AgencyOfficeService {
    @Autowired
    private AgencyOfficeDAO agencyOfficeDAO;
 
    /**
     * Gets the AgencyOfficeDAO instance used for database operations.
     *
     * @return The AgencyOfficeDAO instance.
     */
    public AgencyOfficeDAO getAgencyOfficeRepo() {
        return agencyOfficeDAO;
    }
 
    /**
     * Sets the AgencyOfficeDAO instance for this service.
     *
     * @param agencyOfficeRepo The AgencyOfficeDAO instance to set.
     */
    public void setAgencyOfficeRepo(AgencyOfficeDAO agencyOfficeRepo) {
        this.agencyOfficeDAO = agencyOfficeRepo;
    }
}