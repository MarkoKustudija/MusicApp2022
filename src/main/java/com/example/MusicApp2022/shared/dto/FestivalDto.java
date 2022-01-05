package com.example.MusicApp2022.shared.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class FestivalDto implements Serializable{

	
	
	private static final long serialVersionUID = 5305250177656119167L;
	
	private Long id;
	private String festivalId;
	private String festivalName;
	private LocalDate dateStarts;
	private LocalDate dateEnds;
	private Double ticketPrice;
	private Integer numberOfTickets;
	private CityDto cityDetails;
	private List<ReservationDto> reservations;

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

	public CityDto getCityDetails() {
		return cityDetails;
	}

	public void setCityDetails(CityDto cityDetails) {
		this.cityDetails = cityDetails;
	}

	public List<ReservationDto> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationDto> reservations) {
		this.reservations = reservations;
	}

	

}
