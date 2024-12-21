package com.dao;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.model.Bus;
 
@Repository
public interface BusDAO  extends JpaRepository<Bus,Integer>{
 
	List<Bus> findByagencyOffice_OfficeId(Integer officeId);
 
	
 
}