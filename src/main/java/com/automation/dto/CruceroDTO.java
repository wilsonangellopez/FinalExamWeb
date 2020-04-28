package com.automation.dto;

public class CruceroDTO {
	
	
	private String name;
	private String value;
	private String dateDeparture;
	private String dateReturning;
	private String from;
	private String to;
	
	public CruceroDTO() {
		
	}
	
	public CruceroDTO(String name, String value, String dateDeparture, String dateReturning, String from, String to) {
		this.name = name;
		this.value = value;
		this.dateDeparture = dateDeparture;
		this.dateReturning = dateReturning;
		this.from = from;
		this.to = to;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDateDeparture() {
		return dateDeparture;
	}
	public void setDateDeparture(String dateDeparture) {
		this.dateDeparture = dateDeparture;
	}
	public String getDateReturning() {
		return dateReturning;
	}
	public void setDateReturning(String dateReturning) {
		this.dateReturning = dateReturning;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	

}
