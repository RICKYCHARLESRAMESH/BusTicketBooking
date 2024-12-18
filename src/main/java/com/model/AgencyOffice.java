package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "agency_offices")

public class AgencyOffice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer officeId;
	
	@ManyToOne
	@JoinColumn(name = "agency_id",nullable = false)

	
	private Agency agency;
	
	@Column(name = "office_mail",length = 100)
	private String officeMail;
	
	@Column(name = "office_contact_person_name", length = 50)
	private String officeContactPersonName;
	
	@Column(name = "office_contact_number",length = 10)
	private String officeContactNumber;
	
	@ManyToOne
	@JoinColumn(name = "office_address_id")
	private Address officeAddress;
	
	public AgencyOffice()
	{
		super();
	}

	public AgencyOffice(Integer officeId, Agency agency, String officeMail, String officeContactPersonName,
			String officeContactNumber, Address officeAddress) {
		super();
		this.officeId = officeId;
		this.agency = agency;
		this.officeMail = officeMail;
		this.officeContactPersonName = officeContactPersonName;
		this.officeContactNumber = officeContactNumber;
		this.officeAddress = officeAddress;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public String getOfficeMail() {
		return officeMail;
	}

	public void setOfficeMail(String officeMail) {
		this.officeMail = officeMail;
	}

	public String getOfficeContactPersonName() {
		return officeContactPersonName;
	}

	public void setOfficeContactPersonName(String officeContactPersonName) {
		this.officeContactPersonName = officeContactPersonName;
	}

	public String getOfficeContactNumber() {
		return officeContactNumber;
	}

	public void setOfficeContactNumber(String officeContactNumber) {
		this.officeContactNumber = officeContactNumber;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
}