package com.serviceTest;

import com.dao.BookingDAO;
import com.model.Booking;
import com.service.BookingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingDAO bookingDAO;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBookings() {
        Booking booking1 = new Booking();
        booking1.setBookingId(1);
        booking1.setSeatNumber(10);

        Booking booking2 = new Booking();
        booking2.setBookingId(2);
        booking2.setSeatNumber(20);

        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingDAO.findAll()).thenReturn(bookings);

        List<Booking> result = bookingService.getAllBookings();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookingDAO, times(1)).findAll();
    }

    @Test
    void testGetBookingById_Success() {
        Booking booking = new Booking();
        booking.setBookingId(1);
        booking.setSeatNumber(10);

        when(bookingDAO.findById(1)).thenReturn(Optional.of(booking));

        Booking result = bookingService.getBookingById(1);

        assertNotNull(result);
        assertEquals(1, result.getBookingId());
        assertEquals(10, result.getSeatNumber());
        verify(bookingDAO, times(1)).findById(1);
    }

    @Test
    void testGetBookingById_NotFound() {
        when(bookingDAO.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> bookingService.getBookingById(1));

        assertEquals("Booking not found with ID: 1", exception.getMessage());
        verify(bookingDAO, times(1)).findById(1);
    }

    @Test
    void testSaveBooking() {
        Booking booking = new Booking();
        booking.setSeatNumber(10);

        when(bookingDAO.save(booking)).thenReturn(booking);

        Booking result = bookingService.saveBooking(booking);

        assertNotNull(result);
        assertEquals(10, result.getSeatNumber());
        verify(bookingDAO, times(1)).save(booking);
    }

    @Test
    void testUpdateBooking_Success() {
        Booking existingBooking = new Booking();
        existingBooking.setBookingId(1);
        existingBooking.setSeatNumber(10);

        Booking updatedBooking = new Booking();
        updatedBooking.setSeatNumber(20);

        when(bookingDAO.findById(1)).thenReturn(Optional.of(existingBooking));
        when(bookingDAO.save(existingBooking)).thenReturn(existingBooking);

        Booking result = bookingService.updateBooking(1, updatedBooking);

        assertNotNull(result);
        assertEquals(20, result.getSeatNumber());
        verify(bookingDAO, times(1)).findById(1);
        verify(bookingDAO, times(1)).save(existingBooking);
    }

    @Test
    void testUpdateBooking_NotFound() {
        Booking updatedBooking = new Booking();
        updatedBooking.setSeatNumber(20);

        when(bookingDAO.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> bookingService.updateBooking(1, updatedBooking));

        assertEquals("Booking not found with ID: 1", exception.getMessage());
        verify(bookingDAO, times(1)).findById(1);
    }

    @Test
    void testDeleteBooking_Success() {
        Booking booking = new Booking();
        booking.setBookingId(1);

        when(bookingDAO.findById(1)).thenReturn(Optional.of(booking));
        doNothing().when(bookingDAO).delete(booking);

        bookingService.deleteBooking(1);

        verify(bookingDAO, times(1)).findById(1);
        verify(bookingDAO, times(1)).delete(booking);
    }

    @Test
    void testDeleteBooking_NotFound() {
        when(bookingDAO.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> bookingService.deleteBooking(1));

        assertEquals("Booking not found with ID: 1", exception.getMessage());
        verify(bookingDAO, times(1)).findById(1);
    }
}
