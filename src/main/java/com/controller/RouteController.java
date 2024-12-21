package com.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Route;
import com.service.RouteService;


@RestController

@RequestMapping("/api/routes")
public class RouteController {
	
	@Autowired
	private RouteService routeService;

	public RouteService getRouteservice() {
		return routeService;
	}

	public void setRouteservice(RouteService routeservice) {
		this.routeService = routeservice;
	}
	
	@PostMapping("/add")//--------------------------------1------------------------------
    public ResponseEntity<String> createRoute(@RequestBody Route route) {
       
        routeService.save(route);  // Save the route
        String message = "Record Created Successfully";
        return ResponseEntity.status(HttpStatus.CREATED).body(message); // Return success message
    }
	
	@GetMapping("/{routeId}")//---------------------------2----------------------------
    public ResponseEntity<Route> getRouteById(@PathVariable Integer routeId) {
        Optional<Route> route = routeService.findByRouteId(routeId);
        
        return ResponseEntity.ok(route.get());
    }
	

	@GetMapping("/from_city/{fromCity}")//--------------------------4--------------------------
	public List<Route> getRoutesByFromCity(@PathVariable String fromCity) {
	    List<Route> routes = routeService.findByFromCity(fromCity);
	    
	    return routes;
	}

	// 7. Get routes by toCity
	@GetMapping("/to_city/{toCity}")
	public List<Route> getRoutesByToCity(@PathVariable String toCity) {
	    List<Route> routes = routeService.findByToCity(toCity);
	   
	    return routes;
	}

	// 8. Search Route by from_city and to_city
	@GetMapping("/{from_city}/{to_city}")
	public ResponseEntity<List<Route>> getRouteByFromCityAndToCity(@PathVariable String from_city, @PathVariable String to_city) {
	    List<Route> routes = routeService.findByFromCityAndToCity(from_city, to_city);
	    
	    return ResponseEntity.ok(routes);
	}
	
	
	  @PutMapping // --------------------------UPDATE Route Details--------------------------
	    public ResponseEntity<String> updateRoute(@RequestBody Route route) {
	        routeService.save(route); // Save the updated route details
	        String message = "Record Updated Successfully";
	        return ResponseEntity.status(HttpStatus.OK).body(message); // Return success message
	    }

	    @DeleteMapping("/{route_id}") // --------------------------DELETE Route--------------------------
	    public ResponseEntity<String> deleteRoute(@PathVariable Integer route_id) {
	        routeService.deleteByRouteId(route_id); // Delete the route
	        String message = "Record Deleted Successfully";
	        return ResponseEntity.status(HttpStatus.OK).body(message); // Return success message
	    }
}