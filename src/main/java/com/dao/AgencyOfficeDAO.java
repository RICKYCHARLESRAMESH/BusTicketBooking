package com.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.AgencyOffice;

@Repository
public interface AgencyOfficeDAO extends JpaRepository<AgencyOffice, Integer> {
	
}

