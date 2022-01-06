package com.example.MusicApp2022.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "addresses")
public class AddressEntity {
	
	
	@Id
	@GeneratedValue
	private Long id;
    @Column
	private String addressesId;
    @Column
	private String city;
    @Column
	private String country;
    @Column
	private String streetName;
    @Column
	private String postalCode;
    @Column
	private String type;
    
    @ManyToOne
    @JoinColumn(name = "users_id")
	private UserEntity userDetails;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddressesId() {
		return addressesId;
	}
	public void setAddressesId(String addressesId) {
		this.addressesId = addressesId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserEntity getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}
	
	

}
