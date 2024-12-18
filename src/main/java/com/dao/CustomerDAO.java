package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Address;
import com.model.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer,Integer> {
	
	//find all customers
	List<Customer>findAll();
	
	//find customer by customer id
	Optional<Customer> findById(Integer customerId);
	
	//find customers by email
	List<Customer>findByEmail(String email);
	
	//find customers by phone
	List<Customer> findByPhone(String phone);
	
	//find customers by city
	List<Customer>findByCity(String city);
	
	//find customers by country
	List<Customer>findByCountry(String country);
	
	//find customers by state
	List<Customer>findByState(String state);
	
	//find address of customer by customer id
	Optional<Address>findAddressByCustomerId(Integer customerId);
	
	//save address
	void saveAddress(Address address);
}
