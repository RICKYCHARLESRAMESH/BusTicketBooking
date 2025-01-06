package com.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.dao.RouteDAO;
import com.model.Route;
 
/**
* RouteService provides methods to manage Route entities,
* including saving, finding, and filtering routes based on cities.
*/
@Service
public class RouteService {
 
    @Autowired
    private RouteDAO routeRepo;
 
    /**
     * Gets the RouteDAO instance.
     *
     * @return the RouteDAO instance.
     */
    public RouteDAO getRouteRepo() {
        return routeRepo;
    }
 
    /**
     * Sets the RouteDAO instance.
     *
     * @param routeRepo the RouteDAO instance to set.
     */
    public void setRouteRepo(RouteDAO routeRepo) {
        this.routeRepo = routeRepo;
    }
 
    /**
     * Saves a new or existing route.
     *
     * @param route The Route object to save.
     * @return The saved Route object.
     */
    public Route save(Route route) {
        return routeRepo.save(route);
    }
 
    /**
     * Finds all routes.
     *
     * @return A list of all Route objects.
     */
    public List<Route> findAll() {
        return routeRepo.findAll();
    }
 
    /**
     * Finds a route by its ID.
     *
     * @param routeId The ID of the route to find.
     * @return An Optional containing the Route object if found, or empty if not found.
     */
    public Optional<Route> findByRouteId(Integer routeId) {
        return routeRepo.findById(routeId);
    }
 
    /**
     * Finds routes based on the departure city.
     *
     * @param fromCity The city from which the route departs.
     * @return A list of Route objects that depart from the specified city.
     */
    public List<Route> findByFromCity(String fromCity) {
        return routeRepo.findByFromCity(fromCity);
    }
 
    /**
     * Finds routes based on the destination city.
     *
     * @param toCity The city to which the route arrives.
     * @return A list of Route objects that arrive at the specified city.
     */
    public List<Route> findByToCity(String toCity) {
        return routeRepo.findByToCity(toCity);
    }
 
    /**
     * Finds routes based on both the departure and destination cities.
     *
     * @param fromCity The city from which the route departs.
     * @param toCity   The city to which the route arrives.
     * @return A list of Route objects that match the specified departure and destination cities.
     */
    public List<Route> findByFromCityAndToCity(String fromCity, String toCity) {
        return routeRepo.findByFromCityAndToCity(fromCity, toCity);
    }
}
