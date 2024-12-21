package com.dao;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.model.Review;
 
@Repository
public interface ReviewDAO extends JpaRepository<Review,Integer> {
 
	List<Review> findByCustomer_Id(int customerId);
 
	List<Review> findByTrip_Id(int tripId);
 
	List<Review> findByagency_AgencyId(int agencyId);
	List<Review> findByagencyOffice_OfficeId(int officeId);
 
	List<Review> findBydriver_DriverId(int driverId);
 
 
}