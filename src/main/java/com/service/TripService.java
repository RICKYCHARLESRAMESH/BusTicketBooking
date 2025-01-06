package com.service;
 
import com.dao.TripDAO;
import com.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.time.LocalDateTime;
import java.util.List;
 
/**
* Service class responsible for handling business logic related to Trip operations.
* It provides methods for saving, retrieving, deleting, and searching trips in the system.
* This service communicates with the TripDAO (Data Access Object) to perform database operations.
*/
@Service
public class TripService {
 
    // The TripDAO object used to interact with the database.
    @Autowired
    private TripDAO tripDAO;
 
    /**
     * Saves a trip to the database. Throws an exception if the bus is null.
     * 
     * @param trip The Trip object to be saved.
     * @throws IllegalArgumentException if the bus associated with the trip is null.
     */
    public void saveTrip(Trip trip) {
        // Check if the bus is null before saving
        if (trip.getBus() == null) {
            throw new IllegalArgumentException("Bus cannot be null");
        }
        tripDAO.save(trip);  // Save the trip to the database
    }
 
    /**
     * Retrieves a trip by its ID.
     * 
     * @param id The ID of the trip to retrieve.
     * @return The Trip object associated with the provided ID.
     * @throws RuntimeException if the trip is not found.
     */
    public Trip getTripById(Integer id) {
        // If trip is not found, throw an exception
        return tripDAO.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));
    }
 
    /**
     * Retrieves all trips from the database.
     * 
     * @return A list of all Trip objects.
     */
    public List<Trip> getAllTrips() {
        return tripDAO.findAll();  // Get all trips from the database
    }
 
    /**
     * Deletes a trip by its ID.
     * 
     * @param id The ID of the trip to be deleted.
     */
    public void deleteTrip(Integer id) {
        tripDAO.deleteById(id);  // Delete the trip from the database
    }
 
    /**
     * Searches for trips that depart from a specific city.
     * 
     * @param fromCity The city from which the trip departs.
     * @return A list of trips departing from the specified city.
     */
    public List<Trip> searchByFromCity(String fromCity) {
        return tripDAO.findByFromCity(fromCity);  // Search for trips from a specific city
    }
 
    /**
     * Searches for trips that arrive in a specific city.
     * 
     * @param toCity The city where the trip arrives.
     * @return A list of trips arriving at the specified city.
     */
    public List<Trip> searchByToCity(String toCity) {
        return tripDAO.findByToCity(toCity);  // Search for trips to a specific city
    }
 
    /**
     * Searches for trips based on the bus type.
     * 
     * @param busType The type of bus used for the trip.
     * @return A list of trips using the specified bus type.
     */
    public List<Trip> searchByBusType(String busType) {
        return tripDAO.findByBusType(busType);  // Search for trips by bus type
    }
 
    /**
     * Searches for trips based on both the bus type and the trip date.
     * 
     * @param busType The type of bus used for the trip.
     * @param tripDate The date of the trip.
     * @return A list of trips matching both bus type and trip date.
     */
    public List<Trip> searchByBusTypeAndTripDate(String busType, LocalDateTime tripDate) {
        return tripDAO.findByBusTypeAndTripDate(busType, tripDate);  // Search trips by bus type and date
    }
 
    /**
     * Searches for trips based on the departure city, arrival city, trip date, and bus type.
     * 
     * @param fromCity The city from which the trip departs.
     * @param toCity The city where the trip arrives.
     * @param tripDate The date of the trip.
     * @param busType The type of bus used for the trip.
     * @return A list of trips matching all specified criteria.
     */
    public List<Trip> searchByFromCityToCityDateType(
            String fromCity, String toCity, LocalDateTime tripDate, String busType) {
        return tripDAO.findByFromCityAndToCityAndTripDateAndBusType(
                fromCity, toCity, tripDate, busType);  // Search for trips with all criteria
    }
 
    /**
     * Searches for trips based on the departure city, arrival city, and trip date.
     * 
     * @param fromCity The city from which the trip departs.
     * @param toCity The city where the trip arrives.
     * @param tripDate The date of the trip.
     * @return A list of trips that match the specified cities and trip date.
     */
    public List<Trip> searchByFromCityToCityDate(String fromCity, String toCity, LocalDateTime tripDate) {
        return tripDAO.findByFromCityAndToCityAndTripDate(fromCity, toCity, tripDate);  // Search trips by city and date
    }
 
    /**
     * Searches for trips based on the trip date.
     * 
     * @param tripDate The date of the trip.
     * @return A list of trips on the specified date.
     */
    public List<Trip> searchByTripDate(LocalDateTime tripDate) {
        return tripDAO.findByTripDate(tripDate);  // Search trips by date
    }
}
