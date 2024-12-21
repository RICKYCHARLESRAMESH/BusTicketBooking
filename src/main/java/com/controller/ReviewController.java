package com.controller;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.Review;
import com.service.ReviewService;
 
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
 
    @Autowired
    private ReviewService reviewService;
 
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody Review review) {
        reviewService.addReview(review);
        return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
    }
 
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
 
    @PutMapping("/{review_id}")
    public ResponseEntity<String> updateReview(@PathVariable("review_id") int reviewId, @RequestBody Review reviewDetails) {
        Review updatedReview = reviewService.updateReview(reviewId, reviewDetails);
        if (updatedReview != null) {
            return new ResponseEntity<>("Record Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Not Found", HttpStatus.NOT_FOUND);
    }
 
    @DeleteMapping("/{review_id}")
    public ResponseEntity<String> deleteReview(@PathVariable("review_id") int reviewId) {
        if (reviewService.getReviewById(reviewId) != null) {
            reviewService.deleteReview(reviewId);
            return new ResponseEntity<>("Record Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Not Found", HttpStatus.NOT_FOUND);
    }
 
    @GetMapping("/tripid/{trip_id}")
    public ResponseEntity<List<Review>> getReviewsByTripId(@PathVariable("trip_id") int tripId) {
        List<Review> reviews = reviewService.getReviewsByTripId(tripId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
 
    @GetMapping("/customerid/{customer_id}")
    public ResponseEntity<List<Review>> getReviewsByCustomerId(@PathVariable("customer_id") int customerId) {
        List<Review> reviews = reviewService.getReviewsByCustomerId(customerId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
 
    @GetMapping("/{review_id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("review_id") int reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
 
   @GetMapping("/agency/{agency_id}")
    public ResponseEntity<List<Review>> getReviewsByAgency(@PathVariable("agency_id") int agencyId) {
        List<Review> reviews = reviewService.getReviewsByAgencyId(agencyId);
       return new ResponseEntity<>(reviews, HttpStatus.OK);
   }
 
   @GetMapping("/driver/{driver_id}")
   public ResponseEntity<List<Review>> getReviewsByDriver(@PathVariable("driver_id") int driverId) {
       List<Review> reviews = reviewService.getReviewsByDriverId(driverId);
       return new ResponseEntity<>(reviews, HttpStatus.OK);
   }
 
 
    @GetMapping("/office/{office_id}")
    public ResponseEntity<List<Review>> getReviewsByOffice(@PathVariable("office_id") int officeId) {
        List<Review> reviews = reviewService.getReviewsByOfficeId(officeId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}