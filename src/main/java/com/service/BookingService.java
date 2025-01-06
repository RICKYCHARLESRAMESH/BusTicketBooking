package com.service;
 
import com.dao.BookingDAO;
import com.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
/**
* BookingService is a service class responsible for managing booking-related operations.
*/
@Service
public class BookingService {
 
    @Autowired
    private BookingDAO bookingDAO;
 
    /**
     * Retrieves all bookings from the database.
     *
     * @return A list of all bookings.
     */
    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }
 
    /**
     * Retrieves a booking by its ID.
     *
     * @param bookingId The ID of the booking.
     * @return The Booking object if found.
     * @throws IllegalArgumentException if the booking is not found.
     */
    public Booking getBookingById(Integer bookingId) {
        return bookingDAO.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + bookingId));
    }
 
    /**
     * Saves a new booking to the database.
     *
     * @param booking The booking to save.
     * @return The saved Booking object.
     */
    public Booking saveBooking(Booking booking) {
        return bookingDAO.save(booking);
    }
 
    /**
     * Updates an existing booking.
     *
     * @param bookingId The ID of the booking to update.
     * @param updatedBooking The updated booking data.
     * @return The updated Booking object.
     * @throws IllegalArgumentException if the booking is not found.
     */
    public Booking updateBooking(Integer bookingId, Booking updatedBooking) {
        Booking existingBooking = getBookingById(bookingId);
        existingBooking.setSeatNumber(updatedBooking.getSeatNumber());
        existingBooking.setStatus(updatedBooking.getStatus());
        existingBooking.setTrip(updatedBooking.getTrip());
        return bookingDAO.save(existingBooking);
    }
 
    /**
     * Deletes a booking by its ID.
     *
     * @param bookingId The ID of the booking to delete.
     * @throws IllegalArgumentException if the booking is not found.
     */
    public void deleteBooking(Integer bookingId) {
        Booking booking = getBookingById(bookingId);
        bookingDAO.delete(booking);
    }
}
