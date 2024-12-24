package com.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.Arrays;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import com.controller.ReviewController;
import com.model.Review;
import com.service.ReviewService;
 
public class ReviewControllerTest {
 
    @InjectMocks
    private ReviewController reviewController;
 
    @Mock
    private ReviewService reviewService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testCreateReview() {
        Review review = new Review(); // Create a review instance
 
        ResponseEntity<String> response = reviewController.createReview(review);
 
        assertEquals("Record Created Successfully", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(reviewService, times(1)).addReview(review);
    }
 
    @Test
    void testGetAllReviews() {
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
 
        when(reviewService.getAllReviews()).thenReturn(reviews);
 
        ResponseEntity<List<Review>> response = reviewController.getAllReviews();
 
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reviewService, times(1)).getAllReviews();
    }
 
    @Test
    void testUpdateReviewFound() {
        Review updatedReview = new Review();
        when(reviewService.updateReview(anyInt(), any())).thenReturn(updatedReview);
 
        ResponseEntity<String> response = reviewController.updateReview(1, updatedReview);
 
        assertEquals("Record Updated Successfully", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reviewService, times(1)).updateReview(1, updatedReview);
    }
 
    @Test
    void testUpdateReviewNotFound() {
        Review updatedReview = new Review();
        when(reviewService.updateReview(anyInt(), any())).thenReturn(null);
 
        ResponseEntity<String> response = reviewController.updateReview(1, updatedReview);
 
        assertEquals("Record Not Found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(reviewService, times(1)).updateReview(1, updatedReview);
    }
 
    @Test
    void testDeleteReviewFound() {
        when(reviewService.getReviewById(anyInt())).thenReturn(new Review());
 
        ResponseEntity<String> response = reviewController.deleteReview(1);
 
        assertEquals("Record Deleted Successfully", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reviewService, times(1)).deleteReview(1);
    }
 
    @Test
    void testDeleteReviewNotFound() {
        when(reviewService.getReviewById(anyInt())).thenReturn(null);
 
        ResponseEntity<String> response = reviewController.deleteReview(1);
 
        assertEquals("Record Not Found", response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(reviewService, times(0)).deleteReview(1);
    }
 
    @Test
    void testGetReviewsByTripId() {
        Review review1 = new Review();
        List<Review> reviews = Arrays.asList(review1);
 
        when(reviewService.getReviewsByTripId(anyInt())).thenReturn(reviews);
 
        ResponseEntity<List<Review>> response = reviewController.getReviewsByTripId(1);
 
        assertEquals(1, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reviewService, times(1)).getReviewsByTripId(1);
    }
 
    @Test
    void testGetReviewsByCustomerId() {
        Review review1 = new Review();
        List<Review> reviews = Arrays.asList(review1);
 
        when(reviewService.getReviewsByCustomerId(anyInt())).thenReturn(reviews);
 
        ResponseEntity<List<Review>> response = reviewController.getReviewsByCustomerId(1);
 
        assertEquals(1, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reviewService, times(1)).getReviewsByCustomerId(1);
    }
 
    @Test
    void testGetReviewByIdFound() {
        Review review = new Review();
        when(reviewService.getReviewById(anyInt())).thenReturn(review);
 
        ResponseEntity<Review> response = reviewController.getReviewById(1);
 
        assertEquals(ResponseEntity.ok(review), response);
        verify(reviewService, times(1)).getReviewById(1);
    }
 
    @Test
    void testGetReviewByIdNotFound() {
         when(reviewService.getReviewById(anyInt())).thenReturn(null);
         ResponseEntity<Review> response = reviewController.getReviewById(1);
         assertEquals(ResponseEntity.notFound().build(), response);
         verify(reviewService, times(1)).getReviewById(1);
     }
 
     @Test
     void testGetReviewsByAgency() {
         Review review1 = new Review();
         List<Review> reviews = Arrays.asList(review1);
         when(reviewService.getReviewsByAgencyId(anyInt())).thenReturn(reviews);
         ResponseEntity<List<Review>> response = reviewController.getReviewsByAgency(1);
         assertEquals(1, response.getBody().size());
         assertEquals(HttpStatus.OK, response.getStatusCode());
         verify(reviewService, times(1)).getReviewsByAgencyId(1);
     }
     @Test
     void testGetReviewsByDriver() {
         Review review1 = new Review();
         List<Review> reviews = Arrays.asList(review1);
         when(reviewService.getReviewsByDriverId(anyInt())).thenReturn(reviews);
         ResponseEntity<List<Review>> response = reviewController.getReviewsByDriver(1);
         assertEquals(1, response.getBody().size());
         assertEquals(HttpStatus.OK, response.getStatusCode());
         verify(reviewService, times(1)).getReviewsByDriverId(1);
     }
     @Test
     void testGetReviewsByOffice() {
         Review review1 = new Review();
         List<Review> reviews = Arrays.asList(review1);
         when(reviewService.getReviewsByOfficeId(anyInt())).thenReturn(reviews);
         ResponseEntity<List<Review>> response = reviewController.getReviewsByOffice(1);
         assertEquals(1, response.getBody().size());
         assertEquals(HttpStatus.OK, response.getStatusCode());
         verify(reviewService, times(1)).getReviewsByOfficeId(1);
     }
}