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
	private RouteDAO routeDAO;

	public RouteDAO getRouteDAO() {
		return routeDAO;
	}

	public void setRouteDAO(RouteDAO routeDAO) {
		this.routeDAO = routeDAO;
	}
	public Route save(Route route) {
        return routeDAO.save(route);
    }
	
	public Optional<Route> findByRouteId(Integer routeId) {
        return routeDAO.findById(routeId);
    }
	
	
	 public List<Route> findByFromCity(String fromCity) {
	        return routeDAO.findByFromCity(fromCity);
	    }
   
	 public List<Route> findByToCity(String toCity) {
	        return routeDAO.findByToCity(toCity);
	    }
	    
	  public List<Route> findByFromCityAndToCity(String fromCity, String toCity) {
	        return routeDAO.findByFromCityAndToCity(fromCity, toCity);
	    } 
}