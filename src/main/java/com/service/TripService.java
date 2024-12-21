package com.service;
 
import com.dao.TripDAO;
import com.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.time.LocalDate;
import java.util.List;
 
@Service
public class TripService {
 
    @Autowired
    private TripDAO tripDAO;
 
    public Trip saveTrip(Trip trip) {
    	
 
    	
 
    	
        return tripDAO.save(trip);
    }
 
    public Trip getTripById(Integer id) {
        return tripDAO.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));
    }
 
    public List<Trip> getAllTrips() {
        return tripDAO.findAll();
    }
 
    public void deleteTrip(Integer id) {
        tripDAO.deleteById(id);
    }
 
    public List<Trip> searchByFromCity(String fromCity) {
        return tripDAO.findByFromCity(fromCity);
    }
 
    public List<Trip> searchByToCity(String toCity) {
        return tripDAO.findByToCity(toCity);
    }
 
    public List<Trip> searchByBusType(String busType) {
        return tripDAO.findByBusType(busType);
    }
 
    public List<Trip> searchByBusTypeAndTripDate(String busType, LocalDate tripDate) {
        return tripDAO.findByBusTypeAndTripDate(busType, tripDate);
    }
 
    public List<Trip> searchByFromCityToCityDateType(
            String fromCity, String toCity, LocalDate tripDate, String busType) {
        return tripDAO.findByFromCityAndToCityAndTripDateAndBusType(fromCity, toCity, tripDate, busType);
    }
 
    public List<Trip> searchByFromCityToCityDate(String fromCity, String toCity, LocalDate tripDate) {
        return tripDAO.findByFromCityAndToCityAndTripDate(fromCity, toCity, tripDate);
    }
 
    public List<Trip> searchByTripDate(LocalDate tripDate) {
        return tripDAO.findByTripDate(tripDate);
    }
}