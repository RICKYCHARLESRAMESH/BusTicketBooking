package com.controller;
import com.service.BusService;
import com.service.DriverService;
import com.service.RouteService;
import com.dao.BusDAO;
import com.dao.RouteDAO;
import com.model.Route;
import com.model.Bus;
import com.model.Driver;
import com.service.BusService;
import com.model.Trip;
import com.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
 
@RestController
@RequestMapping("/api/trips")
public class TripController {
 
    @Autowired
    private TripService tripService;
    
    @Autowired
    private RouteService routeService;
    
    @Autowired
    private BusService busService;
    
    @Autowired
    private BusDAO busDAO;
    
  
    @Autowired
    private RouteDAO routeRepo;
    
    @Autowired
    private DriverService driverService;
 
 
  
 
    public TripController(BusService busService, DriverService driverService, TripService tripService) {
        this.busService = busService;
        this.driverService = driverService;
        this.tripService = tripService;
    }
 
  
 
    @PostMapping("/add")
    public ResponseEntity<String> createTrip(@RequestBody Trip trip) {
        // Fetch the Bus entity from the database
        Bus bus = busService.getBusById(trip.getBus().getBusId());
        if (bus == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Bus ID");
        }
        trip.setBus(bus);
        
        if (trip.getRoute() != null && trip.getRoute().getRouteId() != null) {
            Optional<Route> optionalRoute = routeService.findByRouteId(trip.getRoute().getRouteId());
            if (optionalRoute.isPresent()) {
                trip.setRoute(optionalRoute.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Route ID");
            }
        } else {
            trip.setRoute(null); // Route is optional
        }
      
        
        if (trip.getDriver() != null && trip.getDriver().getDriverId() != null) {
            Optional<Driver> optionalDriver = driverService.getDriverById(trip.getDriver().getDriverId());
            if (optionalDriver.isPresent()) {
                trip.setDriver(optionalDriver.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Driver ID");
            }
        } else {
            trip.setDriver(null); // Driver is optional
        }
 
 
 
 
        // Save the trip
        tripService.saveTrip(trip);
        return ResponseEntity.ok("Record Created Successfully");
    }
 
 
    @GetMapping("/get") //
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }
 
    @GetMapping("/{trip_id}") //
    public ResponseEntity<Trip> getTripById(@PathVariable Integer trip_id) {
        return ResponseEntity.ok(tripService.getTripById(trip_id));
    }
 
    @PutMapping("/update")
    public ResponseEntity<String> updateTrip(@RequestBody Trip trip) {
        tripService.saveTrip(trip);
        return ResponseEntity.ok("Record Updated Successfully");
    }
 
 
 
    @GetMapping("/from_city/{from_city}") //
    public ResponseEntity<List<Trip>> searchByFromCity(@PathVariable String from_city) {
        return ResponseEntity.ok(tripService.searchByFromCity(from_city));
    }
 
    @GetMapping("/to_city/{to_city}") //
    public ResponseEntity<List<Trip>> searchByToCity(@PathVariable String to_city) {
        return ResponseEntity.ok(tripService.searchByToCity(to_city));
    }
 
    @GetMapping("/bus_type/{type}") //
    public ResponseEntity<List<Trip>> searchByBusType(@PathVariable String type) {
        return ResponseEntity.ok(tripService.searchByBusType(type));
    }
 
    @GetMapping("/store/bus_type/{type}/trip_date/{trip_date}") //
    public ResponseEntity<List<Trip>> searchByBusTypeAndTripDate(
            @PathVariable String type, @PathVariable String trip_date) {
    	LocalDateTime parsedDateTime = LocalDateTime.parse(trip_date);
        return ResponseEntity.ok(tripService.searchByBusTypeAndTripDate(type, parsedDateTime));
    }
 
    @GetMapping("/{from_city}/{to_city}/{trip_date}/{type}") //
    public ResponseEntity<?> searchByFromToCityDateType(
            @PathVariable String from_city,
            @PathVariable String to_city,
            @PathVariable String trip_date,
            @PathVariable String type) {
 
        try {
            // Parse trip_date as LocalDateTime
            LocalDateTime parsedDateTime = LocalDateTime.parse(trip_date);
 
            // Call the service method
            List<Trip> trips = tripService.searchByFromCityToCityDateType(
                    from_city, to_city, parsedDateTime, type);
 
            // Return the results
            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No trips found.");
            }
            return ResponseEntity.ok(trips);
 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
 
 
    @GetMapping("/{from_city}/{to_city}/{trip_date}")
   
    public ResponseEntity<List<Trip>> searchByFromToCityDate(
            @PathVariable String from_city,
            @PathVariable String to_city,
            @PathVariable String trip_date) {
//        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(trip_date); // Parse full LocalDateTime
            LocalDate date = parsedDateTime.toLocalDate(); // Extract the date
            return ResponseEntity.ok(tripService.searchByFromCityToCityDate(from_city, to_city, parsedDateTime));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null); // Handle parse error
//        }
    }
 
 
    @DeleteMapping("/{trip_id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Integer trip_id) {
        tripService.deleteTrip(trip_id);
        return ResponseEntity.ok("Record Deleted Successfully");
    }
 
    @GetMapping("/trip_date/{trip_date}")
    public ResponseEntity<List<Trip>> searchByTripDate(@PathVariable String trip_date) {
//        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(trip_date); // Parse full LocalDateTime
            return ResponseEntity.ok(tripService.searchByTripDate(parsedDateTime));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null); // Handle parse error
//        }
    }
}