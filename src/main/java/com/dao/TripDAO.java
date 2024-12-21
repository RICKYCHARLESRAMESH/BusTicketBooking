package com.dao;
 
import com.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.time.LocalDate;
import java.util.List;
 
@Repository
public interface TripDAO extends JpaRepository<Trip, Integer> {
 
    List<Trip> findByFromCity(String fromCity);
 
    List<Trip> findByToCity(String toCity);
 
    List<Trip> findByBusType(String busType);
 
    List<Trip> findByBusTypeAndTripDate(String busType, LocalDate tripDate);
 
    List<Trip> findByFromCityAndToCityAndTripDateAndBusType(
            String fromCity, String toCity, LocalDate tripDate, String busType);
 
    List<Trip> findByFromCityAndToCityAndTripDate(String fromCity, String toCity, LocalDate tripDate);
 
    List<Trip> findByTripDate(LocalDate tripDate);
}