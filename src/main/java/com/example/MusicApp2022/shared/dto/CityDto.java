package com.example.MusicApp2022.shared.dto;

import java.io.Serializable;
import java.util.List;

public class CityDto implements Serializable{

	
	private static final long serialVersionUID = 4408990807026346214L;
	
	private Long id;
	private String cityId;
	private String city;
	private String country;
	private List<FestivalDto> festivals;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
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

	public List<FestivalDto> getFestivals() {
		return festivals;
	}

	public void setFestivals(List<FestivalDto> festivals) {
		this.festivals = festivals;
	}


	

}
