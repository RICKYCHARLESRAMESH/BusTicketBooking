package com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer driverId;

	    @Column(name = "license_number", nullable = false, length = 20)
	    private String licenseNumber;

	    @Column(nullable = false, length = 255)
	    private String name;

	    @Column(nullable = false, length = 15)
	    private String phone;

	    @ManyToOne
	    @JoinColumn(name = "office_id")
	    private AgencyOffice agencyOffice;

	    @ManyToOne
	    @JoinColumn(name = "address_id")
	    private Address address;

	    public Driver() {
	        super();
	    }

		public Integer getDriverId() {
			return driverId;
		}

		public void setDriverId(Integer driverId) {
			this.driverId = driverId;
		}

		public String getLicenseNumber() {
			return licenseNumber;
		}

		public void setLicenseNumber(String licenseNumber) {
			this.licenseNumber = licenseNumber;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public AgencyOffice getAgencyOffice() {
			return agencyOffice;
		}

		public void setAgencyOffice(AgencyOffice agencyOffice) {
			this.agencyOffice = agencyOffice;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public Driver(Integer driverId, String licenseNumber, String name, String phone, AgencyOffice agencyOffice,
				Address address) {
			super();
			this.driverId = driverId;
			this.licenseNumber = licenseNumber;
			this.name = name;
			this.phone = phone;
			this.agencyOffice = agencyOffice;
			this.address = address;
		}
	    
	    
}