package com.automation.dto;

import java.time.LocalDate;

public class FlightsDTO {
	
	private String flightFrom; //lax
	private String flightTo;   //las
	
	private String flightTimeInitDeparture; //5:25pm
	private String flightTimeEndDeparture;// 6:35pm
	
	private String flightDateDeparture; //Thu, Apr 16
	private String flightDurationTotalDeparture; // 1h11m
	
	private String flightTimeInitReturn; // 5:57pm 
	private String flightTimeEndReturn; // 9:32pm
	
	private String flightDateReturn; // Wed, Jun 3
	private String flightDurationTotalReturn; //3h 35m 
	
	private String flightTotalPrice;
	
	private LocalDate flightDateDep_FH;
	private LocalDate flightDateRet_FH;
	

	public FlightsDTO(String flightFrom, String flightTo, String flightTimeInitDeparture, String flightTimeEndDeparture,
			String flightDateDeparture, String flightDurationTotalDeparture, String flightTimeInitReturn,
			String flightTimeEndReturn, String flightDateReturn, String flightDurationTotalReturn, String flightTotalPrice,
			LocalDate flightDateDep_FH, LocalDate flightDateRet_FH) {
		
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
		this.flightDateDep_FH= flightDateDep_FH;
		this.flightDateRet_FH = flightDateRet_FH;
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
		return flightDateDep_FH;
	}


	public void setFlightDateDep_FH(LocalDate flightDateDep_FH) {
		this.flightDateDep_FH = flightDateDep_FH;
	}


	public LocalDate getFlightDateRet_FH() {
		return flightDateRet_FH;
	}


	public void setFlightDateRet_FH(LocalDate flightDateRet_FH) {
		this.flightDateRet_FH = flightDateRet_FH;
	}

}
