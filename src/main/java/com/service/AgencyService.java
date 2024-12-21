package com.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AgencyDAO;
import com.model.Agency;
import com.model.AgencyOffice;

@Service
public class AgencyService {

	@Autowired
    private AgencyDAO agencyDAO;
	

	public AgencyDAO getAgenciesRepository() {
		return agencyDAO;
	}

	public void setAgenciesRepository(AgencyDAO agenciesRepository) {
		this.agencyDAO = agenciesRepository;
	}
	
	public String addAgency(Agency agency) {
        agencyDAO.save(agency);  // Save the new agency
        return "Record Created Successfully";
    }
	// Method to get Agency by ID
	public Optional<Agency> getAgencyById(int agencyId) {
        return agencyDAO.findById(agencyId);  // Match the return type with the interface
    }
	
	public String updateAgency(int agencyId, Agency agency) {
        if (!agencyDAO.existsById(agencyId)) {  // Match the method signature
            return "Agency not found!";
        }
        agency.setAgencyId(agencyId);  
        agencyDAO.save(agency);  
        return "Record Updated Successfully";
    }
	
	public List<AgencyOffice> getOfficesByAgencyId(int agencyId) {
        Optional<Agency> agency = agencyDAO.findById(agencyId);
        if (agency.isEmpty()) {
            return null;
        }
        return agency.get().getOffices();  // Get offices for the agency
    }
	
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
}