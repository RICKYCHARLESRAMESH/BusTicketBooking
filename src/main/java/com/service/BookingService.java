package com.service;
 
 
import com.dao.BookingDAO;
import com.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class BookingService {
 
    @Autowired
    private BookingDAO bookingDAO;
 
    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }
 
    public Booking getBookingById(Integer bookingId) {
        return bookingDAO.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + bookingId));
    }
 
    public Booking saveBooking(Booking booking) {
        return bookingDAO.save(booking);
    }
 
    public Booking updateBooking(Integer bookingId, Booking updatedBooking) {
        Booking existingBooking = getBookingById(bookingId);
        existingBooking.setSeatNumber(updatedBooking.getSeatNumber());
        existingBooking.setStatus(updatedBooking.getStatus());
        existingBooking.setTrip(updatedBooking.getTrip());
        return bookingDAO.save(existingBooking);
    }
 
    public void deleteBooking(Integer bookingId) {
        Booking booking = getBookingById(bookingId);
        bookingDAO.delete(booking);
    }
}