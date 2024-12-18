package com.model;


import jakarta.persistence.*;

@Entity
@Table(name = "buses")
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer busId;
 
	@ManyToOne
	@JoinColumn(name = "office_id", nullable = false)

	private AgencyOffice agencyOffice;
 
	@Column(name = "registration_number", nullable = false, length = 20)
	private String registrationNumber;
 
	@Column(nullable = false)
	private Integer capacity;
 
	@Column(nullable = false, length = 30)
	private String type;
 
	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public AgencyOffice getAgencyOffice() {
		return agencyOffice;
	}

	public void setAgencyOffice(AgencyOffice agencyOffice) {
		this.agencyOffice = agencyOffice;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Bus(Integer busId, AgencyOffice agencyOffice, String registrationNumber, Integer capacity, String type) {
		super();
		this.busId = busId;
		this.agencyOffice = agencyOffice;
		this.registrationNumber = registrationNumber;
		this.capacity = capacity;
		this.type = type;
	}
	
    
    
}

