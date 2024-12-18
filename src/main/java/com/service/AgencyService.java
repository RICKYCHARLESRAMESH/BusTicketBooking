package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AgencyDAO;  
import com.model.Agency;   

@Service
public class AgencyService 
{
    @Autowired
    private AgencyDAO agencyDAO;

    public AgencyDAO getAgencyDAO() {
        return agencyDAO;
    }

    public void setAgencyDAO(AgencyDAO agencyDAO) {
        this.agencyDAO = agencyDAO;
    }

    public Agency save(Agency agency) {
        return agencyDAO.save(agency);
    }

    public Optional<Agency> findById(Long agencyId) {
        return agencyDAO.findById(agencyId);
    }

    public List<Agency> findAll() {
        return agencyDAO.findAll();
    }

    public List<Agency> findByName(String name) {
        return agencyDAO.findByName(name);
    }
}
