package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DriverDAO;
import com.model.Driver;
import com.model.Address;

@Service
public class DriverService {
	
	@Autowired
	private DriverDAO driverDAO;
	
	//getter
	public DriverDAO getDriverDAO() {
		return driverDAO;
	}
	
	//setter
	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO=driverDAO;
	}
	
	//adding a new driver
	public String save(Driver driver) {
		driverDAO.save(driver);
		return "Record Added Successfully";
	}
	
	//searching all drivers
	public List<Driver> findAll(){
		return driverDAO.findAll();
	}
	
	//searching driver by driver id
	public Optional<Driver>findByDriverId(Integer driverId){
		return driverDAO.findById(driverId);
	}
	
	//updating driver details
	public String updateDriver(Driver driver) {
		driverDAO.save(driver);
		return "Record Updated Successfully";
	}
	
	//Delete driver
	public String deleteDriver(Integer driverId) {
		driverDAO.deleteById(driverId);
		return "Record Deleted Successfully";
	}
	
	//searching drivers by agency id
	public List<Driver>findByAgencyId(Integer agenyId){
		return driverDAO.findByAgencyId(agencyId);
	}
	
	//searching drivers by office id
	public List<Driver>findByOfficeId(Integer officeId){
		return driverDAO.findByOfficeId(officeId);
	}
	
	//searching address of the driver by driver id
	public Optional<Address> findAddressByDriverId(Integer driverId){
		return driverDAO.findAddressByDriverId(driverId);
	}
	
	//update address of the driver
	public String updateAddress(Address address) {
		driverDAO.saveAddress(address);
		return "Record Updated Successfully";
	}

}
