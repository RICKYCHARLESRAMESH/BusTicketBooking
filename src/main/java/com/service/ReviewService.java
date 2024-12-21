package com.service;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Review;
import com.dao.ReviewDAO;
 
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
 
    public Review updateReview(int reviewId, Review reviewDetails) {
        Review existingReview = reviewDAO.findById(reviewId).orElse(null);
        if (existingReview != null) {
            existingReview.setCustomer(reviewDetails.getCustomer());
            existingReview.setTrip(reviewDetails.getTrip());
            existingReview.setRating(reviewDetails.getRating());
            existingReview.setComment(reviewDetails.getComment());
            existingReview.setReviewDate(reviewDetails.getReviewDate());
            return reviewDAO.save(existingReview);
        }
        return null;
    }
 
    public Review getReviewById(int reviewId) {
        return reviewDAO.findById(reviewId).orElse(null);
    }
 
    public void deleteReview(int reviewId) {
        reviewDAO.deleteById(reviewId);
    }
 
    public List<Review> getReviewsByTripId(int tripId) {
        return reviewDAO.findByTrip_Id(tripId);
    }
 
    public List<Review> getReviewsByCustomerId(int customerId) {
        return reviewDAO.findByCustomer_Id(customerId);
    }
 
   public List<Review> getReviewsByAgencyId(int agencyId) {
      return reviewDAO.findByagency_AgencyId(agencyId);
   }
   public List<Review> getReviewsByDriverId(int driverId) {
       return reviewDAO.findBydriver_DriverId(driverId);
    }

 
    public List<Review> getReviewsByOfficeId(int officeId) {
        return reviewDAO.findByagencyOffice_OfficeId(officeId);
    }
}