package com.example.MusicApp2022.io.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "reservations")
public class ReservationEntity implements Serializable {
	
	
	private static final long serialVersionUID = -4568399428057223041L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String reservationId;
	@Column
	private Integer purchasedTickets;
	@Column
	private Double totalPrice;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "festivals_id")
	private FestivalEntity festivalDetails;

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

	public FestivalEntity getFestivalDetails() {
		return festivalDetails;
	}

	public void setFestivalDetails(FestivalEntity festivalDetails) {
		this.festivalDetails = festivalDetails;
	}


	
	

	
}
