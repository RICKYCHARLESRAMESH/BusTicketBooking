package com.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "trips")
public class Trip{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="trip_id")
	private int id;
	 
	@ManyToOne
	@JoinColumn(name = "route_id", nullable = false)
	private Route route;
	
	@ManyToOne
	@JoinColumn(name = "bus_id", nullable = false)
	private Bus bus;

	@Column(name = "boarding_address_id", nullable = false)
	private Integer boardingAddressId;

	@Column(name = "dropping_address_id", nullable = false)
	private Integer droppingAddressId;

	@Column(name = "departure_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime departureTime;

	@Column(name = "arrival_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime arrivalTime;

	@ManyToOne
	@JoinColumn(name = "driver1_driver_id", nullable = false)
	private Driver driver1;
	
	@ManyToOne
	@JoinColumn(name = "driver2_driver_id", nullable = false)
	private Driver driver2;

	@Column(name = "available_seats", nullable = false)
	private Integer availableSeats;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal fare;

	@Column(name = "trip_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime tripDate;

	public Trip() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Integer getBoardingAddressId() {
		return boardingAddressId;
	}

	public void setBoardingAddressId(Integer boardingAddressId) {
		this.boardingAddressId = boardingAddressId;
	}

	public Integer getDroppingAddressId() {
		return droppingAddressId;
	}

	public void setDroppingAddressId(Integer droppingAddressId) {
		this.droppingAddressId = droppingAddressId;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Driver getDriver1() {
		return driver1;
	}

	public void setDriver1(Driver driver1) {
		this.driver1 = driver1;
	}

	public Driver getDriver2() {
		return driver2;
	}

	public void setDriver2(Driver driver2) {
		this.driver2 = driver2;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public BigDecimal getFare() {
		return fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}

	public LocalDateTime getTripDate() {
		return tripDate;
	}

	public void setTripDate(LocalDateTime tripDate) {
		this.tripDate = tripDate;
	}

	public Trip(int id, Route route, Bus bus, Integer boardingAddressId, Integer droppingAddressId,
			LocalDateTime departureTime, LocalDateTime arrivalTime, Driver driver1, Driver driver2,
			Integer availableSeats, BigDecimal fare, LocalDateTime tripDate) {
		super();
		this.id = id;
		this.route = route;
		this.bus = bus;
		this.boardingAddressId = boardingAddressId;
		this.droppingAddressId = droppingAddressId;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.driver1 = driver1;
		this.driver2 = driver2;
		this.availableSeats = availableSeats;
		this.fare = fare;
		this.tripDate = tripDate;
	}

	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}

	

	public void setTripId(Integer tripId) {
		// TODO Auto-generated method stub
		
	}
//	

	public Object getDuration() {
		// TODO Auto-generated method stub
		return null;
	}
	

   }