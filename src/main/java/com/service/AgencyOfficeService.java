package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AgencyOfficeDAO; 
import com.model.AgencyOffice;  

@Service
public class AgencyOfficeService {

    @Autowired
    private AgencyOfficeDAO agencyOfficeDAO;

    public AgencyOfficeDAO getAgencyOfficeDAO() {
        return agencyOfficeDAO;
    }

    public void setAgencyOfficeDAO(AgencyOfficeDAO agencyOfficeDAO) {
        this.agencyOfficeDAO = agencyOfficeDAO;
    }

    public AgencyOffice save(AgencyOffice agencyOffice) {
        return agencyOfficeDAO.save(agencyOffice);
    }

    public Optional<AgencyOffice> findById(Long officeId) {
        return agencyOfficeDAO.findById(officeId);
    }

    public List<AgencyOffice> findAll() {
        return agencyOfficeDAO.findAll();
    }

    public List<AgencyOffice> findByAgencyId(Long agencyId) {
        return agencyOfficeDAO.findByAgencyId(agencyId);
    }

    public List<AgencyOffice> findByLocation(String location) {
        return agencyOfficeDAO.findByLocation(location);
    }
}

