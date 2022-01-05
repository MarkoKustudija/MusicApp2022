package com.example.MusicApp2022.shared.dto;

import java.io.Serializable;

public class ReservationDto implements Serializable{
	
	
	private static final long serialVersionUID = 8506230817671049619L;
	
	private Long id;
	private String reservationId;
	private Integer purchasedTickets;
	private Double totalPrice;
	private FestivalDto festivalDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public Integer getPurchasedTickets() {
		return purchasedTickets;
	}

	public void setPurchasedTickets(Integer purchasedTickets) {
		this.purchasedTickets = purchasedTickets;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public FestivalDto getFestivalDetails() {
		return festivalDetails;
	}

	public void setFestivalDetails(FestivalDto festivalDetails) {
		this.festivalDetails = festivalDetails;
	}

	

}
