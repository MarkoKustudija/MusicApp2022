package com.example.MusicApp2022.io.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "festivals")
public class FestivalEntity implements Serializable {
	
	
	private static final long serialVersionUID = -7975119743736828510L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String festivalId;
	@Column(nullable = false, unique = true)
	private String festivalName;
	@Column
	private LocalDate dateStarts;
	@Column
	private LocalDate dateEnds;
	@Column
	private Double ticketPrice;
	@Column
	private Integer numberOfTickets;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cities_id")
	private CityEntity cityDetails;

	@OneToMany(mappedBy = "festivalDetails", cascade = CascadeType.ALL)
	private List<ReservationEntity> reservations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFestivalId() {
		return festivalId;
	}

	public void setFestivalId(String festivalId) {
		this.festivalId = festivalId;
	}

	public String getFestivalName() {
		return festivalName;
	}

	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}

	public LocalDate getDateStarts() {
		return dateStarts;
	}

	public void setDateStarts(LocalDate dateStarts) {
		this.dateStarts = dateStarts;
	}

	public LocalDate getDateEnds() {
		return dateEnds;
	}

	public void setDateEnds(LocalDate dateEnds) {
		this.dateEnds = dateEnds;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Integer getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public CityEntity getCityDetails() {
		return cityDetails;
	}

	public void setCityDetails(CityEntity cityDetails) {
		this.cityDetails = cityDetails;
	}

	public List<ReservationEntity> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationEntity> reservations) {
		this.reservations = reservations;
	}

}
