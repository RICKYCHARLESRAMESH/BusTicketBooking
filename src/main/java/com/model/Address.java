package com.model;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name= "addresses")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	@Column(nullable = false, length =255)
	private String address;
	@Column(nullable = false,length = 255)
	private String city;
	@Column(nullable = false,length = 255)
	private String state;
	//adding address columnn (Bhargavi)
	@Column(nullable = false, length = 255)
    private String country;
	@Column(name = "zip_code", nullable = false , length = 10)
	private String zipcode;
 
	public Integer getAddressId() {
		return addressId;
	}
 
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
 
	public String getAddress() {
		return address;
	}
 
	public void setAddress(String address) {
		this.address = address;
	}
 
	public String getCity() {
		return city;
	}
 
	public void setCity(String city) {
		this.city = city;
	}
 
	public String getState() {
		return state;
	}
 
	public void setState(String state) {
		this.state = state;
	}
 
	public String getZipcode() {
		return zipcode;
	}
 
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	//Added country (Bhargavi)
	public String getCountry() {
        return country; // Getter for country
    }

	//Added country (Bhargavi)
    public void setCountry(String country) {
        this.country = country; // Setter for country
    }
 
    public Address(Integer addressId, String address, String city, String state, String zipcode, String country) {
        super();
        this.addressId = addressId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country; // Constructor with country (Bhargavi)
    }
 
	public Address() {
		super();
	}
}