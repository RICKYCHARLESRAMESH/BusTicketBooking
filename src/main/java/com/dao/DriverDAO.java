package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Address;
import com.model.Driver;
@Repository
public interface DriverDAO extends JpaRepository<Driver,Integer> {
	
	//finding all drivers
	List<Driver>findAll();
	
	//finding driver by driver id
	Optional<Driver> findById(Integer driverId);
	
	//find drivers by agency id
	List<Driver>findByAgencyId(Integer agencyId);
	
	//finding drivers by office
	List<Driver>findByOfficeId(Integer officeId);
	
	//finding address by driver id
	Optional<Address>findAddressByDriverId(Integer driverId);
	
	//save address for the driver
	void saveAddress(Address address);

}
