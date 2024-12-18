package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerDAO;
import com.model.Address;
import com.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	//getter
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	
	//setter
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO=customerDAO;
	}
	
	//adding new user
	public String save(Customer customer) {
		customerDAO.save(customer);
		return "Record Created Successfully";
	}
	
	//search all customers
	public List<Customer>findAll(){
		return customerDAO.findAll();
	}
	
	//search customer by customer_id
	public Optional<Customer>findByCustomerId(Integer customerId){
		return customerDAO.findById(customerId);
	}
	
	//search customers by email
	public List<Customer>findByEmail(String email){
		return customerDAO.findByEmail(email);
	}
	
	//search customers by phone
	public List<Customer>findByPhone(String phone){
		return customerDAO.findByPhone(phone);
	}
	
	//search customers by city
	public List<Customer>findByCity(String city){
		return customerDAO.findByCity(city);
	}
	
	//search customers by country
	public List<Customer>findByCountry(String country){
		return customerDAO.findByCountry(country);
	}
	
	//updating customers name
	public Customer updateLastName(Integer customerId, String lastName) {
		Optional<Customer>customerOptional=customerDAO.findById(customerId);
		if(customerOptional.isPresent()) {
			Customer customer=customerOptional.get();
			customer.setLastName(lastName);
			customerDAO.save(customer);
			customerDAO.save(customer);
			return customer;
		}
		return null;
	}
	
	//update email
	public Customer updateEmail(Integer customerId, String email) {
		Optional<Customer> customerOptional=customerDAO.findById(customerId);
		if(customerOptional.isPresent()) {
			Customer customer=customerOptional.get();
			customer.setEmail(email);
			customerDAO.save(customer);
			return customer;
		}
		return null;
	}
	
	//Update customer details
	public String updateCustomerDetails(Customer customer) {
		customerDAO.save(customer);
		return "Record Update Successfully";
	}
	
	//search customer by state
	public List<Customer>findByState(String state){
		return customerDAO.findByState(state);
	}
	
	//search address of a customer by customer id
	public Optional<Address>findAddressByCustomerId(Integer customerId){
		return customerDAO.findByAddressByCustomerId(customerId);
	}
	
	//updating address
	public String updateAddress(Address address) {
		customerDAO.saveAddress(address);
		return "Record Updated Successfully"; 
	}

}
