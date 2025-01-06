package com.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.dao.BookingDAO;
import com.dao.CustomerDAO;
import com.dao.PaymentDAO;
import com.model.Booking;
import com.model.Payment;
 
import java.util.List;
import java.util.Optional;
 
/**
* PaymentService provides methods to manage Payment entities,
* including CRUD operations and retrieval by specific criteria.
*/
@Service
public class PaymentService {
 
    @Autowired
    private PaymentDAO paymentRepo;
 
    @Autowired
    private BookingDAO bookingDAO;
 
    @Autowired
    private CustomerDAO customerDAO;
 
    /**
     * Creates a new payment and associates it with the relevant booking and customer.
     *
     * @param payment The Payment object to be created.
     */
    public void createPayment(Payment payment) {
        Booking existingBooking = bookingDAO.findById(payment.getBooking().getBookingId()).get();
        Payment savePayment = new Payment();
        savePayment.setCustomer(customerDAO.findById(payment.getCustomer().getId()).get());
        savePayment.setAmount(payment.getAmount());
        savePayment.setBooking(existingBooking);
        paymentRepo.save(savePayment);  // Save the payment to the repository
    }
 
    /**
     * Retrieves a payment by its ID.
     *
     * @param paymentId The ID of the payment.
     * @return An Optional containing the Payment if found, or empty if not.
     */
    public Optional<Payment> getPaymentById(Integer paymentId) {
        return paymentRepo.findById(paymentId);  // Get payment by ID
    }
 
    /**
     * Retrieves all payments from the database.
     *
     * @return A list of all payments.
     */
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();  // Get all payments
    }
 
    /**
     * Retrieves payments associated with a specific customer ID.
     *
     * @param customerId The ID of the customer.
     * @return A list of payments linked to the specified customer.
     */
    public List<Payment> getPaymentsByCustomerId(Integer customerId) {
        return paymentRepo.findByCustomer_Id(customerId);  // Custom query to find payments by customer ID
    }
 
    /**
     * Retrieves a payment by its associated booking ID.
     *
     * @param bookingId The ID of the booking.
     * @return An Optional containing the Payment if found, or empty if not.
     */
    public Optional<Payment> getPaymentByBookingId(Integer bookingId) {
        return paymentRepo.findByBooking_BookingId(bookingId);  // Custom query to find payments by booking ID
    }
 
    /**
     * Retrieves payments filtered by their status.
     *
     * @param paymentStatus The status of the payment to filter by.
     * @return A list of payments with the specified status.
     */
    public List<Payment> getPaymentsByStatus(Payment.PaymentStatus paymentStatus) {
        return paymentRepo.findByPaymentStatus(paymentStatus);
    }
}

