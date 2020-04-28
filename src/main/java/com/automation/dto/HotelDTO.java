package com.automation.dto;

import java.time.LocalDate;

public class HotelDTO {

	private String name;
	private String stars;
	private String value;
	private String dateIn;
	private String dateOut;
	private LocalDate dateIn_LD;
	private LocalDate dateOut_LD;
	private String rooms;
	private String nights;
	private String quantityAdults;
	private String quantityChildrens;

	public HotelDTO() {
	}
	
	public HotelDTO(String name, String stars, String value, String dateIn, String dateOut, LocalDate dateIn_LD,
			LocalDate dateOut_LD, String rooms, String nights, String quantityAdults, String quantityChildrens) {

		this.name = name;
		this.stars = stars;
		this.value = value;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.dateIn_LD = dateIn_LD;
		this.dateOut_LD = dateOut_LD;
		this.rooms = rooms;
		this.nights = nights;
		this.quantityAdults = quantityAdults;
		this.quantityChildrens = quantityChildrens;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStars() {
		return stars;
	}


	public void setStars(String stars) {
		this.stars = stars;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getDateIn() {
		return dateIn;
	}


	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}


	public String getDateOut() {
		return dateOut;
	}


	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}


	public LocalDate getDateIn_LD() {
		return dateIn_LD;
	}


	public void setDateIn_LD(LocalDate dateIn_LD) {
		this.dateIn_LD = dateIn_LD;
	}


	public LocalDate getDateOut_LD() {
		return dateOut_LD;
	}


	public void setDateOut_LD(LocalDate dateOut_LD) {
		this.dateOut_LD = dateOut_LD;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.rooms = rooms;
	}

	public String getNights() {
		return nights;
	}

	public void setNights(String nights) {
		this.nights = nights;
	}

	public String getQuantityAdults() {
		return quantityAdults;
	}

	public void setQuantityAdults(String quantityAdults) {
		this.quantityAdults = quantityAdults;
	}

	public String getQuantityChildrens() {
		return quantityChildrens;
	}

	public void setQuantityChildrens(String quantityChildrens) {
		this.quantityChildrens = quantityChildrens;
	}

}
