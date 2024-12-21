package com.controller;
 
import com.model.Trip;
import com.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.time.LocalDate;
import java.util.List;
 
@RestController
@RequestMapping("/api/trips")
public class TripController {
 
    @Autowired
    private TripService tripService;
 
    @PostMapping("/add")
    public ResponseEntity<String> createTrip(@RequestBody Trip trip) {
        tripService.saveTrip(trip);
        return ResponseEntity.ok("Record Created Successfully");
    }
 
    @GetMapping("/get")
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }
 
    @GetMapping("/{trip_id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Integer trip_id) {
        return ResponseEntity.ok(tripService.getTripById(trip_id));
    }
 
    @PutMapping("/update")
    public ResponseEntity<String> updateTrip(@RequestBody Trip trip) {
        tripService.saveTrip(trip);
        return ResponseEntity.ok("Record Updated Successfully");
    }
 
    @GetMapping("/from_city/{from_city}")
    public ResponseEntity<List<Trip>> searchByFromCity(@PathVariable String from_city) {
        return ResponseEntity.ok(tripService.searchByFromCity(from_city));
    }
 
    @GetMapping("/to_city/{to_city}")
    public ResponseEntity<List<Trip>> searchByToCity(@PathVariable String to_city) {
        return ResponseEntity.ok(tripService.searchByToCity(to_city));
    }
 
    @GetMapping("/bus_type/{type}")
    public ResponseEntity<List<Trip>> searchByBusType(@PathVariable String type) {
        return ResponseEntity.ok(tripService.searchByBusType(type));
    }
 
    @GetMapping("/store/bus_type/{type}/trip_date/{trip_date}")
    public ResponseEntity<List<Trip>> searchByBusTypeAndDate(
            @PathVariable String type, @PathVariable String trip_date) {
        return ResponseEntity.ok(tripService.searchByBusTypeAndTripDate(type, LocalDate.parse(trip_date)));
    }
 
    @GetMapping("/{from_city}/{to_city}/{trip_date}/{bus_type}")
    public ResponseEntity<List<Trip>> searchByFromToCityDateType(
            @PathVariable String from_city, @PathVariable String to_city,
            @PathVariable String trip_date, @PathVariable String bus_type) {
        return ResponseEntity.ok(tripService.searchByFromCityToCityDateType(
                from_city, to_city, LocalDate.parse(trip_date), bus_type));
    }
 
    @GetMapping("/{from_city}/{to_city}/{trip_date}")
    public ResponseEntity<List<Trip>> searchByFromToCityDate(
            @PathVariable String from_city, @PathVariable String to_city, @PathVariable String trip_date) {
        return ResponseEntity.ok(tripService.searchByFromCityToCityDate(from_city, to_city, LocalDate.parse(trip_date)));
    }
 
    @DeleteMapping("/{trip_id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Integer trip_id) {
        tripService.deleteTrip(trip_id);
        return ResponseEntity.ok("Record Deleted Successfully");
    }
 
    @GetMapping("/trip_date/{trip_date}")
    public ResponseEntity<List<Trip>> searchByTripDate(@PathVariable String trip_date) {
        return ResponseEntity.ok(tripService.searchByTripDate(LocalDate.parse(trip_date)));
    }
}
 