package com.example.MusicApp2022.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "cities")
public class CityEntity  implements Serializable{
	
	
	private static final long serialVersionUID = -3886740950793630378L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String cityId;
	@Column
	private String city;
	@Column
	private String country;
	
	@OneToMany(mappedBy = "cityDetails", cascade = CascadeType.ALL)
	private List<FestivalEntity> festivals;
	
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

	public List<FestivalEntity> getFestivals() {
		return festivals;
	}

	public void setFestivals(List<FestivalEntity> festivals) {
		this.festivals = festivals;
	}

	

}
