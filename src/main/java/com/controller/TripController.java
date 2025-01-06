package com.controller;
 
import com.service.BusService;
import com.service.DriverService;
import com.service.RouteService;
import com.dao.BusDAO;
import com.dao.RouteDAO;
import com.model.Route;
import com.model.Bus;
import com.model.Driver;
import com.model.Trip;
import com.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
 
/**
* TripController is a REST controller that manages trip-related operations,
* providing endpoints for creating, retrieving, updating, and deleting trips.
*/
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
 
    /**
     * Creates a new trip.
     *
     * @param trip The Trip object containing trip details.
     * @return ResponseEntity containing a success message or error information.
     */
    @PostMapping("/add")
    public ResponseEntity<String> createTrip(@RequestBody Trip trip) {
        // Validate and set Bus
        Bus bus = busService.getBusById(trip.getBus().getBusId());
        if (bus == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Bus ID");
        }
        trip.setBus(bus);
 
        // Validate and set Route
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
 
        // Validate and set Driver
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
 
    /**
     * Retrieves all trips.
     *
     * @return ResponseEntity containing a list of Trip objects.
     */
    @GetMapping("/get")
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }
 
    /**
     * Retrieves a trip by its ID.
     *
     * @param trip_id The ID of the trip to retrieve.
     * @return ResponseEntity containing the Trip object if found.
     */
    @GetMapping("/{trip_id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Integer trip_id) {
        return ResponseEntity.ok(tripService.getTripById(trip_id));
    }
 
    /**
     * Updates the details of an existing trip.
     *
     * @param trip The Trip object containing updated trip details.
     * @return ResponseEntity containing a success message.
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateTrip(@RequestBody Trip trip) {
        tripService.saveTrip(trip);
        return ResponseEntity.ok("Record Updated Successfully");
    }
 
    /**
     * Searches for trips by the departure city.
     *
     * @param from_city The city from which the trip originates.
     * @return ResponseEntity containing a list of matching Trip objects.
     */
    @GetMapping("/from_city/{from_city}")
    public ResponseEntity<List<Trip>> searchByFromCity(@PathVariable String from_city) {
        return ResponseEntity.ok(tripService.searchByFromCity(from_city));
    }
 
    /**
     * Searches for trips by the destination city.
     *
     * @param to_city The city to which the trip is heading.
     * @return ResponseEntity containing a list of matching Trip objects.
     */
    @GetMapping("/to_city/{to_city}")
    public ResponseEntity<List<Trip>> searchByToCity(@PathVariable String to_city) {
        return ResponseEntity.ok(tripService.searchByToCity(to_city));
    }
 
    /**
     * Searches for trips by bus type.
     *
     * @param type The type of the bus.
     * @return ResponseEntity containing a list of matching Trip objects.
     */
    @GetMapping("/bus_type/{type}")
    public ResponseEntity<List<Trip>> searchByBusType(@PathVariable String type) {
        return ResponseEntity.ok(tripService.searchByBusType(type));
    }
 
    /**
     * Searches for trips by bus type and trip date.
     *
     * @param type      The type of the bus.
     * @param trip_date The date of the trip in ISO format.
     * @return ResponseEntity containing a list of matching Trip objects.
     */
    @GetMapping("/store/bus_type/{type}/trip_date/{trip_date}")
    public ResponseEntity<List<Trip>> searchByBusTypeAndTripDate(
            @PathVariable String type, @PathVariable String trip_date) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(trip_date);
        return ResponseEntity.ok(tripService.searchByBusTypeAndTripDate(type, parsedDateTime));
    }
 
    /**
     * Searches for trips based on departure city, destination city, date, and bus type.
     *
     * @param from_city The city from which the trip originates.
     * @param to_city   The city to which the trip is heading.
     * @param trip_date The date of the trip in ISO format.
     * @param type      The type of the bus.
     * @return ResponseEntity containing a list of matching Trip objects or an error message.
     */
    @GetMapping("/{from_city}/{to_city}/{trip_date}/{type}")
    public ResponseEntity<?> searchByFromToCityDateType(
            @PathVariable String from_city,
            @PathVariable String to_city,
            @PathVariable String trip_date,
            @PathVariable String type) {
 
        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(trip_date);
            List<Trip> trips = tripService.searchByFromCityToCityDateType(
                    from_city, to_city, parsedDateTime, type);
 
            if (trips.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No trips found.");
            }
            return ResponseEntity.ok(trips);
 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
 
    /**
     * Searches for trips based on departure city, destination city, and trip date.
     *
     * @param from_city The city from which the trip originates.
     * @param to_city   The city to which the trip is heading.
     * @param trip_date The date of the trip in ISO format.
     * @return ResponseEntity containing a list of matching Trip objects.
     */
    @GetMapping("/{from_city}/{to_city}/{trip_date}")
    public ResponseEntity<List<Trip>> searchByFromToCityDate(
            @PathVariable String from_city,
            @PathVariable String to_city,
            @PathVariable String trip_date) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(trip_date); // Parse full LocalDateTime
        return ResponseEntity.ok(tripService.searchByFromCityToCityDate(from_city, to_city, parsedDateTime));
    }
 
    /**
     * Deletes a trip by its ID.
     *
     * @param trip_id The ID of the trip to delete.
     * @return ResponseEntity containing a success message.
     */
    @DeleteMapping("/{trip_id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Integer trip_id) {
        tripService.deleteTrip(trip_id);
        return ResponseEntity.ok("Record Deleted Successfully");
    }
 
    /**
     * Searches for trips by trip date.
     *
     * @param trip_date The date of the trip in ISO format.
     * @return ResponseEntity containing a list of matching Trip objects.
     */
    @GetMapping("/trip_date/{trip_date}")
    public ResponseEntity<List<Trip>> searchByTripDate(@PathVariable String trip_date) {
        LocalDateTime parsedDateTime = LocalDateTime.parse(trip_date); // Parse full LocalDateTime
        return ResponseEntity.ok(tripService.searchByTripDate(parsedDateTime));
    }
}
