package com.model;
 
 
import jakarta.persistence.*;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;



 
 
@Entity
@Table(name = "trips")
public class Trip{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="trip_id")
	private int id;
	
	@Column(name = "departure_time", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Define the expected format
	 
	private LocalDateTime departureTime;
	
	@Column(name = "available_seats", nullable = false)
	private Integer availableSeats;
 
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal fare;
	
	@Column(name = "boarding_address_id", nullable = false)
	private Integer boardingAddressId;
 
	@Column(name = "dropping_address_id", nullable = false)
	private Integer droppingAddressId;
 
	@Column(name = "arrival_time", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)

	 
	private LocalDateTime arrivalTime;
 
	@Column(name = "trip_date", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)

	 
	private LocalDateTime tripDate;
 
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "route_id", nullable = false,referencedColumnName="route_id")
	//@JsonIgnore
	private Route route;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "bus_id", nullable = false,referencedColumnName="bus_id")
	//@JsonIgnore
	private Bus bus;
 
//    @ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="driver_id",nullable=false,referencedColumnName="driver_id")
//    //@JsonIgnore
//	private Driver driver;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id", nullable = true, referencedColumnName = "driver_id") // nullable = true for optional driver
	private Driver driver;
 
 
//anu
public LocalDateTime parseTripDate(String tripDate) {
    return LocalDateTime.parse(tripDate); // Parses ISO-8601 by default
}
 
 
	
 
	
 
	public Trip() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public int getId() {
		return id;
	}
 
	
 
 
	
 
 
	public Driver getDriver() {
		return driver;
	}
 
 
 
 
 
 
	public void setDriver(Driver driver) {
		this.driver = driver;
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
			LocalDateTime departureTime, LocalDateTime arrivalTime, Driver driver,
			Integer availableSeats, BigDecimal fare, LocalDateTime tripDate) {
		super();
		this.id = id;
		this.route = route;
		this.bus = bus;
		this.boardingAddressId = boardingAddressId;
		this.droppingAddressId = droppingAddressId;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.driver=driver;
		this.availableSeats = availableSeats;
		this.fare = fare;
		this.tripDate = tripDate;
	}
 

 
   }