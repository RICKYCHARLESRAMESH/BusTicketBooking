package com.DaoTest;
 
import com.dao.BookingDAO;
import com.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ContextConfiguration(classes = {BookingDAO.class})
@SpringBootTest
public class BookingDAOTest {
    @Mock
    private BookingDAO bookingDAO;
    private Booking booking;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Create sample booking data
        booking = new Booking();
        booking.setBookingId(1);
        booking.setCustomerName("John Doe");
        booking.setTotalAmount(100.50);
    }
    @Test
    public void testFindById() {
        // Mock DAO behavior
        when(bookingDAO.findById(1)).thenReturn(Optional.of(booking));
        // Test method
        Optional<Booking> retrievedBooking = bookingDAO.findById(1);
        // Verify results
        assertTrue(retrievedBooking.isPresent());
        assertEquals(1, retrievedBooking.get().getBookingId());
        // Verify mock interaction
        verify(bookingDAO, times(1)).findById(1);
    }
    @Test
    public void testSave() {
        // Mock DAO behavior
        when(bookingDAO.save(booking)).thenReturn(booking);
        // Test method
        Booking savedBooking = bookingDAO.save(booking);
        // Verify results
        assertNotNull(savedBooking);
        assertEquals(1, savedBooking.getBookingId());
        // Verify mock interaction
        verify(bookingDAO, times(1)).save(booking);
    }
    @Test
    public void testDeleteById() {
        // Perform operation
        bookingDAO.deleteById(1);
        // Verify mock interaction
        verify(bookingDAO, times(1)).deleteById(1);
    }
}