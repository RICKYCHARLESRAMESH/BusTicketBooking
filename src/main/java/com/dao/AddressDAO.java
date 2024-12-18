package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Address;

@Repository
public interface AddressDAO extends JpaRepository<Address,Integer> {

}