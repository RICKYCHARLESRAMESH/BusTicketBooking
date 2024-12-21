package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "agencies")
public class Agency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer agencyId;
	
	@Column(nullable = false, length = 255)
	private String name;
	
	@Column(name = "contact_person_name",nullable = false, length = 30)
	private String contactPersonName;
	
	@Column(nullable = false, length =255)
	private String email;
	
	@Column(nullable = false, length = 15)
	private String phone;
	
	@OneToMany(mappedBy = "agency", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<AgencyOffice> offices;
	
	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<AgencyOffice> getOffices() {
		return offices;
	}

	public void setOffices(List<AgencyOffice> offices) {
		this.offices = offices;
	}

	public Agency()
	{
		super();
	}

	public Agency(Integer agencyId, String name, String contactPersonName, String email, String phone,
			List<AgencyOffice> offices) {
		super();
		this.agencyId = agencyId;
		this.name = name;
		this.contactPersonName = contactPersonName;
		this.email = email;
		this.phone = phone;
		this.offices = offices;
	}
	
	

}


