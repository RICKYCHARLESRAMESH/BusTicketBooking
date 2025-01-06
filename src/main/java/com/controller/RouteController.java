package com.controller;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.exception.CustomException;
import com.model.Route;
import com.service.RouteService;
 
/**
* RouteController is a REST controller that handles route-related operations,
* providing endpoints for creating, retrieving, updating, and managing routes.
*/
@RestController
@RequestMapping("/api/routes")
public class RouteController {
 
    @Autowired
    private RouteService routeService;
 
    /**
     * Creates a new route.
     *
     * @param route The Route object containing route details.
     * @return ResponseEntity containing a success message and HTTP status 201 Created.
     */
    @PostMapping("/add")
    public ResponseEntity<String> createRoute(@RequestBody Route route) {
        if (route == null || route.getFromCity() == null || route.getToCity() == null) {
            throw new CustomException("POSTFAILS", "Route data is missing or invalid");
        }
        routeService.save(route);  // Save the route
        String message = "Record Created Successfully";
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    /**
     * Retrieves all routes.
     *
     * @return ResponseEntity containing a list of Route objects and HTTP status 200 OK.
     * @throws CustomException if no routes are available.
     */
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.findAll();
        if (routes.isEmpty()) {
            throw new CustomException("NOTFOUND", "No routes available");
        }
        return ResponseEntity.ok(routes);
    }
 
    /**
     * Retrieves a route by its ID.
     *
     * @param routeId The ID of the route to retrieve.
     * @return ResponseEntity containing the Route object if found, or an error message if not found or invalid.
     */
    @GetMapping("/{routeId}")
    public ResponseEntity<Route> getRouteById(@PathVariable Integer routeId) {
        if (routeId <= 0) {
            throw new CustomException("INVALIDID", "Invalid route ID: " + routeId);
        }
        Optional<Route> route = routeService.findByRouteId(routeId);
        if (route.isPresent()) {
            return ResponseEntity.ok(route.get());
        } else {
            throw new CustomException("NOTFOUND", "Route not found with ID: " + routeId);
        }
    }
 
    /**
     * Retrieves routes by the from city.
     *
     * @param fromCity The name of the city from which the route originates.
     * @return ResponseEntity containing a list of Route objects that start from the specified city.
     * @throws CustomException if the from city is null or empty, or if no routes are found.
     */
    @GetMapping("/from_city/{fromCity}")
    public ResponseEntity<List<Route>> getRoutesByFromCity(@PathVariable String fromCity) {
        if (fromCity == null || fromCity.isEmpty()) {
            throw new CustomException("INVALIDPARAM", "From city cannot be null or empty");
        }
        List<Route> routes = routeService.findByFromCity(fromCity);
        if (routes.isEmpty()) {
            throw new CustomException("NOTFOUND", "No routes found for from city: " + fromCity);
        }
        return ResponseEntity.ok(routes);
    }
 
    /**
     * Retrieves routes by the to city.
     *
     * @param toCity The name of the destination city for the route.
     * @return ResponseEntity containing a list of Route objects that go to the specified city.
     * @throws CustomException if the to city is null or empty, or if no routes are found.
     */
    @GetMapping("/to_city/{toCity}")
    public ResponseEntity<List<Route>> getRoutesByToCity(@PathVariable String toCity) {
        if (toCity == null || toCity.isEmpty()) {
            throw new CustomException("INVALIDPARAM", "To city cannot be null or empty");
        }
        List<Route> routes = routeService.findByToCity(toCity);
        if (routes.isEmpty()) {
            throw new CustomException("NOTFOUND", "No routes found for to city: " + toCity);
        }
        return ResponseEntity.ok(routes);
    }
 
    /**
     * Retrieves routes by both from city and to city.
     *
     * @param from_city The starting city for the route.
     * @param to_city The destination city for the route.
     * @return ResponseEntity containing a list of Route objects matching the specified criteria.
     * @throws CustomException if either city is null or empty, or if no routes are found.
     */
    @GetMapping("/{from_city}/{to_city}")
    public ResponseEntity<List<Route>> getRouteByFromCityAndToCity(@PathVariable String from_city, @PathVariable String to_city) {
        if (from_city == null || from_city.isEmpty() || to_city == null || to_city.isEmpty()) {
            throw new CustomException("INVALIDPARAM", "Both from city and to city are required");
        }
        List<Route> routes = routeService.findByFromCityAndToCity(from_city, to_city);
        if (routes.isEmpty()) {
            throw new CustomException("NOTFOUND", "No routes found for from city: " + from_city + " and to city: " + to_city);
        }
        return ResponseEntity.ok(routes);
    }
 
    /**
     * Updates the details of an existing route.
     *
     * @param route The Route object containing updated route details.
     * @return ResponseEntity containing a success message and HTTP status 200 OK.
     * @throws CustomException if the route details or route ID are null.
     */
    @PutMapping
    public ResponseEntity<String> updateRoute(@RequestBody Route route) {
        if (route == null || route.getRouteId() == null) {
            throw new CustomException("UPDATEFAILS", "Route details or route ID cannot be null");
        }
        routeService.save(route); // Save the updated route details
        String message = "Record Updated Successfully";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}