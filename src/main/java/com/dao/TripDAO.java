package com.dao;
 
import com.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
 
@Repository
public interface TripDAO extends JpaRepository<Trip, Integer> {
 
	@Query("select t from Trip t where t.route.fromCity= :fromCity")
    List<Trip> findByFromCity(String fromCity);
	@Query("select t from Trip t where t.route.toCity= :toCity")
    List<Trip> findByToCity(String toCity);
	@Query("select t from Trip t where t.bus.type= :type")
    List<Trip> findByBusType(String type);
    @Query("SELECT t FROM Trip t WHERE t.bus.type = :type AND t.tripDate = :tripDate")
    List<Trip> findByBusTypeAndTripDate(@Param("type") String type, @Param("tripDate") LocalDateTime tripDate);
//
//    List<Trip> findByFromCityAndToCityAndTripDateAndBusType(
//            String fromCity, String toCity, LocalDateTime parsedDateTime, String busType);

 
        @Query("SELECT t FROM Trip t WHERE t.route.fromCity = :fromCity AND t.route.toCity = :toCity AND t.tripDate = :tripDate AND t.bus.type = :type")
        List<Trip> findByFromCityAndToCityAndTripDateAndBusType(
                @Param("fromCity") String fromCity,
                @Param("toCity") String toCity,
                @Param("tripDate") LocalDateTime tripDate,
                @Param("type") String type);

        @Query("SELECT t FROM Trip t WHERE t.route.fromCity = :fromCity AND t.route.toCity = :toCity AND t.tripDate = :tripDate")
    List<Trip> findByFromCityAndToCityAndTripDate(String fromCity, String toCity, LocalDateTime tripDate);
//anu
    List<Trip> findByTripDate(LocalDateTime tripDate);
 
}