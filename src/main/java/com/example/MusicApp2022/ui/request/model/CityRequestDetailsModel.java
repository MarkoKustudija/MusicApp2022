package com.example.MusicApp2022.ui.request.model;

import java.util.List;

public class CityRequestDetailsModel {

	private String city;
	private String country;
	private List<FestivalRequestDetailsModel> festivals;

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

	public List<FestivalRequestDetailsModel> getFestivals() {
		return festivals;
	}

	public void setFestivals(List<FestivalRequestDetailsModel> festivals) {
		this.festivals = festivals;
	}

}
