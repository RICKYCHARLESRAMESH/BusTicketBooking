package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.AgencyOffice;
import com.model.Address;

@Repository
public interface AgencyOfficeDAO extends JpaRepository<AgencyOffice, Integer> {

  
}
