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

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;
    
    // Create a new route
    @PostMapping("/add")
    public ResponseEntity<String> createRoute(@RequestBody Route route) {
        if (route == null || route.getFromCity() == null || route.getToCity() == null) {
            throw new CustomException("POSTFAILS", "Route data is missing or invalid");
        }
        routeService.save(route);  // Save the route
        String message = "Record Created Successfully";
        return ResponseEntity.status(HttpStatus.CREATED).body(message); // Return success message
    }
    
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.findAll();
        if (routes.isEmpty()) {
            throw new CustomException("NOTFOUND", "No routes available");
        }
        return ResponseEntity.ok(routes);
    }

    // Get a route by its ID
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

    // Get routes by from city
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

    // Get routes by to city
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

    // Get routes by from city and to city
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

    // Update route details
    @PutMapping
    public ResponseEntity<String> updateRoute(@RequestBody Route route) {
        if (route == null || route.getRouteId() == null) {
            throw new CustomException("UPDATEFAILS", "Route details or route ID cannot be null");
        }
        routeService.save(route); // Save the updated route details
        String message = "Record Updated Successfully";
        return ResponseEntity.status(HttpStatus.OK).body(message); // Return success message
    }

   
}
