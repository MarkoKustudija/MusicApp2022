package com.example.MusicApp2022.ui.response.model;

public class ReservationRest {
	
	private String reservationId;
	private Integer purchasedTickets;
	private Double totalPrice;
//	private FestivalRest festivalDetails;

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

//	public FestivalRest getFestivalDetails() {
//		return festivalDetails;
//	}
//
//	public void setFestivalDetails(FestivalRest festivalDetails) {
//		this.festivalDetails = festivalDetails;
//	}

	

}
