//package com.DaoTest;
// 
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
// 
//import com.dao.ReviewDAO;
//import com.model.Agency;
//import com.model.AgencyOffice;
//import com.model.Customer;
//import com.model.Driver;
//import com.model.Review;
//import com.model.Trip;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
// 
//import java.util.Date;
//import java.util.List;
// 
//@ExtendWith(MockitoExtension.class)
//public class ReviewDAOTest {
// 
//    @Mock
//    private ReviewDAO reviewDAO;
// 
//    private Customer customer;
//    private Trip trip;
//    private Agency agency;
//    private AgencyOffice agencyOffice;
//    private Driver driver;
//    private Review review;
// 
//    @BeforeEach
//    public void setUp() {
//        // Create sample entities needed for testing
//        customer = new Customer();
//        customer.setId(1);
// 
//        trip = new Trip();
//        trip.setId(1);
// 
//        agency = new Agency();
//        agency.setAgencyId(1);
// 
//        agencyOffice = new AgencyOffice();
//        agencyOffice.setOfficeId(1);
// 
//        driver = new Driver();
//        driver.setDriverId(1);
// 
//        // Create a default review
//        review = new Review();
//        review.setCustomer(customer);
//        review.setTrip(trip);
//        review.setAgency(agency);
//        review.setAgency(agencyOffice);
//        review.setDriver(driver);
//        review.setRating(5);
//        review.setComment("Excellent service!");
//        review.setReviewDate(new Date());
//    }
// 
//    @Test
//    public void testSaveReview() {
//        // Set a mock ID for the review (if it's expected to be generated)
//        review.setReviewId(1); // Assuming you can set this for the test
// 
//        // Mock DAO behavior
//        when(reviewDAO.save(any(Review.class))).thenAnswer(invocation -> {
//            Review savedReview = invocation.getArgument(0);
//            savedReview.setReviewId(1); // Set the ID as part of the mock response
//            return savedReview;
//        });
// 
//        // Test method
//        Review savedReview = reviewDAO.save(review);
// 
//        // Verify results
//        assertNotNull(savedReview);
//        assertEquals(1, savedReview.getReviewId()); // Check that the ID is set correctly
// 
//        // Verify mock interaction
//        verify(reviewDAO, times(1)).save(review);
//    }
// 
// 
//    @Test
//    public void testFindByCustomerId() {
//        // Mock DAO behavior
//        when(reviewDAO.findByCustomer_Id(customer.getId())).thenReturn(List.of(review));
// 
//        // Test method
//        List<Review> reviews = reviewDAO.findByCustomer_Id(customer.getId());
// 
//        // Verify results
//        assertNotNull(reviews);
//        assertFalse(reviews.isEmpty());
//        assertEquals(1, reviews.size());
//        assertEquals(review.getReviewId(), reviews.get(0).getReviewId());
// 
//        // Verify mock interaction
//        verify(reviewDAO, times(1)).findByCustomer_Id(customer.getId());
//    }
// 
//    @Test
//    public void testFindByTripId() {
//        // Mock DAO behavior
//        when(reviewDAO.findByTrip_Id(trip.getId())).thenReturn(List.of(review));
// 
//        // Test method
//        List<Review> reviews = reviewDAO.findByTrip_Id(trip.getId());
// 
//        // Verify results
//        assertNotNull(reviews);
//        assertFalse(reviews.isEmpty());
//        assertEquals(1, reviews.size());
//        assertEquals(review.getReviewId(), reviews.get(0).getReviewId());
// 
//        // Verify mock interaction
//        verify(reviewDAO, times(1)).findByTrip_Id(trip.getId());
//    }
// 
//    @Test
//    public void testFindByAgencyId() {
//        // Mock DAO behavior
//        when(reviewDAO.findByagency_AgencyId(agency.getAgencyId())).thenReturn(List.of(review));
// 
//        // Test method
//        List<Review> reviews = reviewDAO.findByagency_AgencyId(agency.getAgencyId());
// 
//        // Verify results
//        assertNotNull(reviews);
//        assertFalse(reviews.isEmpty());
//        assertEquals(1, reviews.size());
//        assertEquals(review.getReviewId(), reviews.get(0).getReviewId());
// 
//        // Verify mock interaction
//        verify(reviewDAO, times(1)).findByagency_AgencyId(agency.getAgencyId());
//    }
// 
//    @Test
//    public void testFindByAgencyOfficeId() {
//        // Mock DAO behavior
//        when(reviewDAO.findByagency_AgencyId(agencyOffice.getOfficeId())).thenReturn(List.of(review));
// 
//        // Test method
//        List<Review> reviews = reviewDAO.findByagency_AgencyId(agencyOffice.getOfficeId());
// 
//        // Verify results
//        assertNotNull(reviews);
//        assertFalse(reviews.isEmpty());
//        assertEquals(1, reviews.size());
//        assertEquals(review.getReviewId(), reviews.get(0).getReviewId());
// 
//        // Verify mock interaction
//        verify(reviewDAO, times(1)).findByagency_AgencyId(agencyOffice.getOfficeId());
//    }
// 
//    @Test
//    public void testFindByDriverId() {
//        // Mock DAO behavior
//        when(reviewDAO.findBydriver_DriverId(driver.getDriverId())).thenReturn(List.of(review));
// 
//        // Test method
//        List<Review> reviews = reviewDAO.findBydriver_DriverId(driver.getDriverId());
// 
//        // Verify results
//        assertNotNull(reviews);
//        assertFalse(reviews.isEmpty());
//        assertEquals(1, reviews.size());
//        assertEquals(review.getReviewId(), reviews.get(0).getReviewId());
// 
//        // Verify mock interaction
//        verify(reviewDAO, times(1)).findBydriver_DriverId(driver.getDriverId());
//    }
//}