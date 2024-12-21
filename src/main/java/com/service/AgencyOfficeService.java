package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AgencyOfficeDAO;

@Service
public class AgencyOfficeService {
    
	@Autowired
	private AgencyOfficeDAO agencyOfficeDAO;
	
	 public AgencyOfficeDAO getAgencyOfficeRepo() {
		return agencyOfficeDAO;
	}

	public void setAgencyOfficeRepo(AgencyOfficeDAO agencyOfficeRepo) {
		this.agencyOfficeDAO = agencyOfficeRepo;
	}

	
	
}