 package com.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Review;

@Repository
public interface ReviewDAO extends JpaRepository<Review,Integer> {

	List<Review> findByCustomer_CustomerId(int customerId);

	List<Review> findByTrip_TripId(int tripId);

	List<Review> findByTrip_Agency_AgencyId(int agencyId);

	List<Review> findByTrip_Driver_DriverId(int driverId);

	List<Review> findByTrip_Office_OfficeId(int officeId);



}
