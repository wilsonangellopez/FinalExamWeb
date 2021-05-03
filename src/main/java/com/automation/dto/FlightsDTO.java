package com.automation.dto;

import java.time.LocalDate;

public class FlightsDTO {
	
	private String flightFrom; 
	private String flightTo;   
	
	private String flyFrom;
	private String flyTo;
	
	private String flightTimeInitDeparture; 
	private String flightTimeEndDeparture;
	
	private String flightDateDeparture; 
	private String flightDurationTotalDeparture; 
	
	private String flightTimeInitReturn;  
	private String flightTimeEndReturn; 
	
	private String flightDateReturn; 
	private String flightDurationTotalReturn;  
	
	private String flightTotalPrice;
	
	private LocalDate flightDateDep;
	private LocalDate flightDateRet;
	

	public FlightsDTO(String flightFrom, String flightTo, String flightTimeInitDeparture,
			String flightTimeEndDeparture, String flightDateDeparture,
			String flightDurationTotalDeparture, String flightTimeInitReturn,
			String flightTimeEndReturn, String flightDateReturn,
			String flightDurationTotalReturn, String flightTotalPrice,
			LocalDate flightDateDep, LocalDate flightDateRet,
			String flyFrom, String flyTo ) {
		
		this.flightFrom = flightFrom;
		this.flightTo = flightTo;
		this.flightTimeInitDeparture = flightTimeInitDeparture;
		this.flightTimeEndDeparture = flightTimeEndDeparture;
		this.flightDateDeparture = flightDateDeparture;
		this.flightDurationTotalDeparture = flightDurationTotalDeparture;
		this.flightTimeInitReturn = flightTimeInitReturn;
		this.flightTimeEndReturn = flightTimeEndReturn;
		this.flightDateReturn = flightDateReturn;
		this.flightDurationTotalReturn = flightDurationTotalReturn;
		this.flightTotalPrice = flightTotalPrice;
		this.flightDateDep= flightDateDep;
		this.flightDateRet = flightDateRet;
		this.flyFrom = flyFrom;
		this.flyTo= flyTo;
	}
	

	public FlightsDTO() {
	}

	public String getFlightFrom() {
		return flightFrom;
	}

	public void setFlightFrom(String flightFrom) {
		this.flightFrom = flightFrom;
	}

	public String getFlightTo() {
		return flightTo;
	}

	public void setFlightTo(String flightTo) {
		this.flightTo = flightTo;
	}

	public String getFlightTimeInitDeparture() {
		return flightTimeInitDeparture;
	}

	public void setFlightTimeInitDeparture(String flightTimeInitDeparture) {
		this.flightTimeInitDeparture = flightTimeInitDeparture;
	}

	public String getFlightTimeEndDeparture() {
		return flightTimeEndDeparture;
	}

	public void setFlightTimeEndDeparture(String flightTimeEndDeparture) {
		this.flightTimeEndDeparture = flightTimeEndDeparture;
	}

	public String getFlightDateDeparture() {
		return flightDateDeparture;
	}

	public void setFlightDateDeparture(String flightDateDeparture) {
		this.flightDateDeparture = flightDateDeparture;
	}

	public String getFlightDurationTotalDeparture() {
		return flightDurationTotalDeparture;
	}

	public void setFlightDurationTotalDeparture(String flightDurationTotalDeparture) {
		this.flightDurationTotalDeparture = flightDurationTotalDeparture;
	}

	public String getFlightTimeInitReturn() {
		return flightTimeInitReturn;
	}

	public void setFlightTimeInitReturn(String flightTimeInitReturn) {
		this.flightTimeInitReturn = flightTimeInitReturn;
	}

	public String getFlightTimeEndReturn() {
		return flightTimeEndReturn;
	}

	public void setFlightTimeEndReturn(String flightTimeEndReturn) {
		this.flightTimeEndReturn = flightTimeEndReturn;
	}

	public String getFlightDateReturn() {
		return flightDateReturn;
	}

	public void setFlightDateReturn(String flightDateReturn) {
		this.flightDateReturn = flightDateReturn;
	}

	public String getFlightDurationTotalReturn() {
		return flightDurationTotalReturn;
	}

	public void setFlightDurationTotalReturn(String flightDurationTotalReturn) {
		this.flightDurationTotalReturn = flightDurationTotalReturn;
	}


	public String getFlightTotalPrice() {
		return flightTotalPrice;
	}


	public void setFlightTotalPrice(String flightTotalPrice) {
		this.flightTotalPrice = flightTotalPrice;
	}


	public LocalDate getFlightDateDep_FH() {
		return flightDateDep;
	}


	public void setFlightDateDep_FH(LocalDate flightDateDep_FH) {
		this.flightDateDep = flightDateDep_FH;
	}


	public LocalDate getFlightDateRet() {
		return flightDateRet;
	}


	public void setFlightDateRet(LocalDate flightDateRet) {
		this.flightDateRet = flightDateRet;
	}


	public String getFlyFrom() {
		return flyFrom;
	}


	public void setFlyFrom(String flyFrom) {
		this.flyFrom = flyFrom;
	}


	public String getFlyTo() {
		return flyTo;
	}


	public void setFlyTo(String flyTo) {
		this.flyTo = flyTo;
	}

}
