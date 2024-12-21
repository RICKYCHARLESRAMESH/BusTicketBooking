package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.AgencyOffice;
import com.model.Address;

@Repository
public interface AgencyOfficeDAO extends JpaRepository<AgencyOffice, Integer> {

    // Find all offices belonging to a specific agency by agency ID
    @Query("SELECT ao FROM AgencyOffice ao WHERE ao.agency.agencyId = :agencyId")
    List<AgencyOffice> findAllByAgencyId(Integer agencyId);

    // Retrieve all office addresses for a specific agency by agency ID
    @Query("SELECT ao.officeAddress FROM AgencyOffice ao WHERE ao.agency.agencyId = :agencyId")
    List<Address> findAddressesByAgencyId(Integer agencyId);

    // Retrieve the address of a specific office by office ID
    @Query("SELECT ao.officeAddress FROM AgencyOffice ao WHERE ao.officeId = :officeId")
    Address findAddressByOfficeId(Integer officeId);
}
