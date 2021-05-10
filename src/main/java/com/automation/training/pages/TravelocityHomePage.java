package com.automation.training.pages;

import static com.automation.training.utils.TestContext.CONTEXT;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.time.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.dto.FlightsDTO;
import com.automation.dto.HotelDTO;
import com.automation.training.utils.Logger;

public class TravelocityHomePage extends BasePage {

	public TravelocityHomePage(WebDriver driver) {	
		super(driver);
		driver.get("https://www.travelocity.com/");
	}

	@FindBy(css="a[aria-controls*='flight']")
	private WebElement btnFlights;

	@FindBy(id="tab-hotel-tab-hp")
	private WebElement btnHotel;

	@FindBy(css="button[aria-label*='Leaving from']")
	private WebElement inputLeavingFrom;

	@FindBy(id="location-field-leg1-origin")
	private WebElement inputLeavingTxt;

	@FindBy(css="button[aria-label='Going to']")
	private WebElement inputGoingTo;

	@FindBy(css="div[data-stid*='origin'] li[data-index='0'] button")
	private WebElement inputLeavingToSelect;

	@FindBy(id="location-field-leg1-destination")
	private WebElement inputGoingToTxt;

	@FindBy(css="div[data-stid*='destination'] li[data-index='0'] button")
	private WebElement inputGoingToSelect;

	@FindBy(css="button[data-stid='apply-date-picker'] >span")
	private WebElement btnDoneCalendar;

	@FindBy(css="button[data-testid='submit-button']")
	private WebElement btnSearch;




	@FindBy(css="a[aria-controls*='roundtrip']")
	private WebElement btnRoundtrip;

	@FindBy(id="d1-btn")
	private WebElement inputCalendardDeptFlight;

	@FindBy(css="button[data-stid='date-picker-paging']:nth-child(2)")
	private WebElement btnNextCalendar;


	@FindBy(id="cruise-destination-hp-cruise")
	private WebElement dropDownListGoingTo;



	/*
	 * Method to click flight button
	 */
	public void clickFlights() {

		Logger.printInfo("click in fligts");
		getWait().until(ExpectedConditions.elementToBeClickable(btnFlights));
		btnFlights.click();
	}


	/*
	 * Method to click btn RoundTrip
	 */
	public void clickRoundTrip() {

		Logger.printInfo("click in RoundTrip");
		if(isPresent(btnRoundtrip).getText().toString().equalsIgnoreCase("RoundTrip")){
			click(btnRoundtrip);	
		}

	}

	/*
	 * Method to write departure and returning
	 */
	public void selectFromAndTo(String text) {

		if(text.equalsIgnoreCase("LAS")) {

			click(inputLeavingFrom);
			click(inputLeavingTxt);
			writing(inputLeavingTxt, text);
			click(inputLeavingToSelect);
		}


		if(text.equalsIgnoreCase("LAX")) {
			click(inputGoingTo);
			click(inputGoingToTxt);
			writing(inputGoingToTxt, text);
			click(inputGoingToSelect);
		}

	}

	/*
	 * Method to select some date from data picker-Calendar
	 */
	public void selectDateForFlight(String option, int monthFly) {

		if(option.equalsIgnoreCase("LAS")) {

			Logger.printInfo("Opcion enviada: " + option);

			click(inputCalendardDeptFlight);
			clickingNextBtnCalendar(btnNextCalendar);
			clickFutureDate(getFutureDate(monthFly));
		}
		if(option.equalsIgnoreCase("LAX")) {

			clickingNextBtnCalendar(btnNextCalendar);
			clickFutureDate(getFutureDate(monthFly));
		}

	}

	/*
	 * Methd to click done button in calendar
	 */

	public void clickDoneBtnInCalendar() {
		click(btnDoneCalendar);
	}

	private void clickFutureDate(String futureDate) {

		String elementDateFuture = "button[aria-label='" + futureDate + "']";

		try {
			clickByElementJS(getDriver(), elementDateFuture);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void clickingNextBtnCalendar(WebElement element) {

		click(element);
	}


	/**
	 * Method to create the future date 
	 * @param quantity
	 * @return
	 */
	public String getFutureDate(int quantity) {

		String departureDate ="";
		LocalDate date = LocalDate.now();
		date = date.plusMonths(quantity);
		int futureDay = date.getDayOfMonth();
		String monthArray[]= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		int indxMonth = date.getMonthValue()-1;
		String strMonthSelected = monthArray[indxMonth];
		int futureYear = date.getYear();

		departureDate = strMonthSelected + " " + futureDay + ", " + futureYear;

		System.out.println("cual es la fecha generada: " + departureDate);

		return departureDate ;
	}

	public boolean clickDropDownList() {

		getWait().until(ExpectedConditions.elementToBeClickable(dropDownListGoingTo));
		Select drp = new Select(dropDownListGoingTo);
		drp.selectByValue("europe");
		boolean isSelectDrp= drp.getFirstSelectedOption().getText().equals("Europe")?true:false;
		return isSelectDrp;
	}

	/*
	 * Method to click btn search
	 */
	public FlightsSearchPage clickSearchButton() {

		click(btnSearch);

		return new FlightsSearchPage(getDriver());
	}


	/*
	 * Method to redirect to flight search page 
	 */
	public FlightsSearchPage reDirect() {

		getDriver().get("https://www.travelocity.com/Flights-Search?leg1=from%3ALas%20Vegas%2C%20NV%20%28LAS-McCarran%20Intl.%29%2Cto%3ALos%20Angeles%2C%20CA%20%28LAX-Los%20Angeles%20Intl.%29%2Cdeparture%3A8%2F6%2F2021TANYT&leg2=from%3ALos%20Angeles%2C%20CA%20%28LAX-Los%20Angeles%20Intl.%29%2Cto%3ALas%20Vegas%2C%20NV%20%28LAS-McCarran%20Intl.%29%2Cdeparture%3A8%2F31%2F2021TANYT&mode=search&options=carrier%3A%2A%2Ccabinclass%3A%2Cmaxhops%3A1%2Cnopenalty%3AN&pageId=0&passengers=adults%3A1%2Cchildren%3A0%2Cinfantinlap%3AN&trip=roundtrip");
		getDriver().navigate().refresh();
		return new FlightsSearchPage(getDriver());
	}

}
