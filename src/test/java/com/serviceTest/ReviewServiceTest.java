package com.serviceTest;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import com.service.ReviewService;
 
import com.dao.ReviewDAO;
import com.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
public class ReviewServiceTest {
 
    @InjectMocks
    private ReviewService reviewService;
 
    @Mock
    private ReviewDAO reviewDAO;
 
    @BeforeEach
    void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testAddReview() {
        Review review = new Review();
        when(reviewDAO.save(any(Review.class))).thenReturn(review);
 
        Review result = reviewService.addReview(review);
 
        assertNotNull(result);
        verify(reviewDAO, times(1)).save(review);
    }
 
    @Test
    void testGetAllReviews() {
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewDAO.findAll()).thenReturn(reviews);
 
        List<Review> result = reviewService.getAllReviews();
 
        assertEquals(2, result.size());
        verify(reviewDAO, times(1)).findAll();
    }
 
    @Test
    void testUpdateReviewFound() {
        int reviewId = 1;
        Review reviewDetails = new Review();
        Review existingReview = new Review();
        when(reviewDAO.findById(anyInt())).thenReturn(Optional.of(existingReview));
        when(reviewDAO.save(any(Review.class))).thenReturn(existingReview);
 
        Review result = reviewService.updateReview(reviewId, reviewDetails);
 
        assertNotNull(result);
        verify(reviewDAO, times(1)).findById(reviewId);
        verify(reviewDAO, times(1)).save(existingReview);
    }
 
    @Test
    void testUpdateReviewNotFound() {
        int reviewId = 1;
        Review reviewDetails = new Review();
        when(reviewDAO.findById(anyInt())).thenReturn(Optional.empty());
 
        Review result = reviewService.updateReview(reviewId, reviewDetails);
 
        assertNull(result);
        verify(reviewDAO, times(1)).findById(reviewId);
        verify(reviewDAO, never()).save(any(Review.class));
    }
 
    @Test
    void testGetReviewByIdFound() {
        int reviewId = 1;
        Review review = new Review();
        when(reviewDAO.findById(anyInt())).thenReturn(Optional.of(review));
 
        Review result = reviewService.getReviewById(reviewId);
 
        assertNotNull(result);
        verify(reviewDAO, times(1)).findById(reviewId);
    }
 
    @Test
    void testGetReviewByIdNotFound() {
        int reviewId = 1;
        when(reviewDAO.findById(anyInt())).thenReturn(Optional.empty());
 
        Review result = reviewService.getReviewById(reviewId);
 
        assertNull(result);
        verify(reviewDAO, times(1)).findById(reviewId);
    }
 
    @Test
    void testDeleteReview() {
        int reviewId = 1;
        doNothing().when(reviewDAO).deleteById(anyInt());
 
        reviewService.deleteReview(reviewId);
 
        verify(reviewDAO, times(1)).deleteById(reviewId);
    }
 
    @Test
    void testGetReviewsByTripId() {
        int tripId = 1;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewDAO.findByTrip_Id(anyInt())).thenReturn(reviews);
 
        List<Review> result = reviewService.getReviewsByTripId(tripId);
 
        assertEquals(2, result.size());
        verify(reviewDAO, times(1)).findByTrip_Id(tripId);
    }
 
    @Test
    void testGetReviewsByCustomerId() {
        int customerId = 1;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewDAO.findByCustomer_Id(anyInt())).thenReturn(reviews);
 
        List<Review> result = reviewService.getReviewsByCustomerId(customerId);
 
        assertEquals(2, result.size());
        verify(reviewDAO, times(1)).findByCustomer_Id(customerId);
    }
 
    @Test
    void testGetReviewsByAgencyId() {
        int agencyId = 1;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewDAO.findByagency_AgencyId(anyInt())).thenReturn(reviews);
 
        List<Review> result = reviewService.getReviewsByAgencyId(agencyId);
 
        assertEquals(2, result.size());
        verify(reviewDAO, times(1)).findByagency_AgencyId(agencyId);
    }
 
    @Test
    void testGetReviewsByDriverId() {
        int driverId = 1;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewDAO.findBydriver_DriverId(anyInt())).thenReturn(reviews);
 
        List<Review> result = reviewService.getReviewsByDriverId(driverId);
 
        assertEquals(2, result.size());
        verify(reviewDAO, times(1)).findBydriver_DriverId(driverId);
    }
 
    @Test
    void testGetReviewsByOfficeId() {
        int officeId = 1;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewDAO.findByagencyOffice_OfficeId(anyInt())).thenReturn(reviews);
 
        List<Review> result = reviewService.getReviewsByOfficeId(officeId);
 
        assertEquals(2, result.size());
        verify(reviewDAO, times(1)).findByagencyOffice_OfficeId(officeId);
    }
}