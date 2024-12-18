package com.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Agency;

@Repository
public interface AgencyDAO extends JpaRepository<Agency, Integer> {

	Optional<Agency> findById(Long agencyId);
	List<Agency> findByName(String name);
}
