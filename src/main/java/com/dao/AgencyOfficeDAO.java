package com.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.AgencyOffice;

@Repository
public interface AgencyOfficeDAO extends JpaRepository<AgencyOffice, Integer> {

	Optional<AgencyOffice> findById(Long officeId);
	List<AgencyOffice> findByAgencyId(Long agencyId);
	List<AgencyOffice> findByLocation(String location);
	
}

