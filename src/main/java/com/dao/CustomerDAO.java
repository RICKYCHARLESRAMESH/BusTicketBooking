package com.dao;
 
import com.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    
    // Search customers by email
    List<Customer> findByEmail(String email);
    
    // Search customers by phone
    List<Customer> findByPhone(String phone);
    
    // Search customers by city
    List<Customer> findByAddressCity(String city);
    
    // Search customers by country
    List<Customer> findByAddressCountry(String country);
    
    // Search customers by state
    List<Customer> findByAddressState(String state);
}
 