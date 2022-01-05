package com.example.MusicApp2022.ui.request.model;

public class ReservationRequestDetailsModel {

	private Integer purchasedTickets;
	private Double totalPrice;
	private FestivalRequestDetailsModel festivalDetails;

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

	public FestivalRequestDetailsModel getFestivalDetails() {
		return festivalDetails;
	}

	public void setFestivalDetails(FestivalRequestDetailsModel festivalDetails) {
		this.festivalDetails = festivalDetails;
	}

}
