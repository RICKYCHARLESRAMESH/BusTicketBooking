
package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RouteDAO;
import com.model.Route;


@Service
public class RouteService {

	@Autowired
	private RouteDAO routeRepo;

	public RouteDAO getRouteRepo() {
		return routeRepo;
	}

	public void setRouteRepo(RouteDAO routeRepo) {
		this.routeRepo = routeRepo;
	}
	public Route save(Route route) {
        return routeRepo.save(route);
    }
	
	public Optional<Route> findByRouteId(Integer routeId) {
        return routeRepo.findById(routeId);
    }
	
	
	 public List<Route> findByFromCity(String fromCity) {
	        return routeRepo.findByFromCity(fromCity);
	    }

	   
	    public List<Route> findByToCity(String toCity) {
	        return routeRepo.findByToCity(toCity);
	    }
	    
	
	    public List<Route> findByFromCityAndToCity(String fromCity, String toCity) {
	        return routeRepo.findByFromCityAndToCity(fromCity, toCity);
	    }
	    
	    // Delete a route by ID
	    public void deleteByRouteId(Integer routeId) {
	        routeRepo.deleteById(routeId);


}
	    }