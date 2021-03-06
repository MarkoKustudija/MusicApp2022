package com.example.MusicApp2022.ui.request.model;

public class AddressRequestDetailsModel {

	private String city;
	private String country;
	private String streetName;
	private String postalCode;
	private String type;
	private UserRequestDetailsModel userDetails;
	
	
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
	public UserRequestDetailsModel getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserRequestDetailsModel userDetails) {
		this.userDetails = userDetails;
	}
	
	

}
