package com.example.MusicApp2022.ui.response.model;

import java.time.LocalDate;
import java.util.List;

public class FestivalRest {
	
	private String festivalId;
	private String festivalName;
	private LocalDate dateStarts;
	private LocalDate dateEnds;
	private Double ticketPrice;
	private Integer numberOfTickets;
	private CityRest cityDetails;
	private List<ReservationRest> reservations;

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

	public CityRest getCityDetails() {
		return cityDetails;
	}

	public void setCityDetails(CityRest cityDetails) {
		this.cityDetails = cityDetails;
	}

	public List<ReservationRest> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationRest> reservations) {
		this.reservations = reservations;
	}



}
