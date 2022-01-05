package com.example.MusicApp2022.ui.request.model;

import java.time.LocalDate;
import java.util.List;

public class FestivalRequestDetailsModel {

	private String festivalName;
	private LocalDate dateStarts;
	private LocalDate dateEnds;
	private Double ticketPrice;
	private Integer numberOfTickets;
	private CityRequestDetailsModel cityDetails;
	private List<ReservationRequestDetailsModel> reservations;

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

	public CityRequestDetailsModel getCityDetails() {
		return cityDetails;
	}

	public void setCityDetails(CityRequestDetailsModel cityDetails) {
		this.cityDetails = cityDetails;
	}

	public List<ReservationRequestDetailsModel> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationRequestDetailsModel> reservations) {
		this.reservations = reservations;
	}

}
