package com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer routeId;

	@Column(name = "from_city", nullable = false, length = 255)
	private String fromCity;

	@Column(name = "to_city", nullable = false, length = 255)
	private String toCity;

	@Column(name = "break_points")
	private Integer breakPoints;

	@Column(nullable = false)
	private Integer duration;

	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Route(Integer routeId, String fromCity, String toCity, Integer breakPoints, Integer duration) {
		super();
		this.routeId = routeId;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.breakPoints = breakPoints;
		this.duration = duration;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Integer getBreakPoints() {
		return breakPoints;
	}

	public void setBreakPoints(Integer breakPoints) {
		this.breakPoints = breakPoints;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	
}
