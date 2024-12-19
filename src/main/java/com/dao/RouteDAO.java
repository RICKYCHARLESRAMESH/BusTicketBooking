package com.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Route;

@Repository
public interface RouteDAO extends JpaRepository<Route, Integer> {
	
    List<Route> findByFromCity(String fromCity);

    List<Route> findByToCity(String toCity);
    
    List<Route> findByFromCityAndToCity(String fromCity, String toCity);
}
