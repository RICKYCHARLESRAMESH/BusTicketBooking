package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Review;
import com.dao.ReviewDAO;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    public Review addReview(Review review) {
        return reviewDAO.save(review);
    }
    public List<Review> getAllReviews() {
        return reviewDAO.findAll();
    }
    public Review updateReview(Review updatedReview) {
        return reviewDAO.save(updatedReview);
    }

    public void deleteReview(int reviewId) {
        reviewDAO.deleteById(reviewId);
    }

    // Get reviews by trip ID
    public List<Review> getReviewsByTripId(int tripId) {
        return reviewDAO.findByTrip_TripId(tripId);
    }

    // Get a review by ID
    public Optional<Review> getReviewById(int reviewId) {
        return reviewDAO.findById(reviewId);
    }

    // Get reviews by customer ID
    public List<Review> getReviewsByCustomerId(int customerId) {
        return reviewDAO.findByCustomer_CustomerId(customerId);
    }

    // Get reviews by agency ID
    public List<Review> getReviewsByAgencyId(int agencyId) {
        return reviewDAO.findByTrip_Agency_AgencyId(agencyId);
    }

    // Get reviews by driver ID
    public List<Review> getReviewsByDriverId(int driverId) {
        return reviewDAO.findByTrip_Driver_DriverId(driverId);
    }

    // Get reviews by office ID
    public List<Review> getReviewsByOfficeId(int officeId) {
        return reviewDAO.findByTrip_Office_OfficeId(officeId);
    }
}
