package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.Driver;

import java.util.List;

@Repository
public interface DriverDAO extends JpaRepository<Driver, Integer> {
    
    // Custom query methods
	public List<Driver>findByAgencyOfficeAgencyAgencyId(Integer agencyId);

    public List<Driver> findByAgencyOfficeOfficeId(Integer officeId);
    
}
