package com.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Agency;

@Repository
public interface AgencyDAO extends JpaRepository<Agency, Integer> {
	
}
