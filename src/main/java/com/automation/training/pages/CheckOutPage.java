package com.automation.training.pages;

import static com.automation.training.utils.TestContext.CONTEXT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.automation.dto.FlightsDTO;
import com.automation.dto.HotelDTO;
import com.automation.training.utils.Logger;

public class CheckOutPage extends BasePage {

	@FindBy(xpath="h2[class='faceoff-module-title']")
	private WebElement title;

	@FindBy(css="input[name$='firstName']")
	private WebElement inputFirstName;

	@FindBy(xpath="//p[@class='uitk-validation-error']")
	private List<WebElement> listErrors;

	@FindBy(css="input[name$='middleName']")
	private WebElement middleName;

	@FindBy(css="label[for='middlename[0]'] p")
	private WebElement middleNameError;

	@FindBy(css="input[id='lastname[0]']")
	private WebElement lastName;

	@FindBy(css="label[for='lastname[0]'] p")
	private WebElement LastNameError;

	@FindBy(css="input[id='phone-number[0]'] , input[data-cko-rfrr-id='MCKO.CKO.Phone.NumberEntered']")
	private WebElement phoneNumber;

	@FindBy(css="label[for='phone-number[0]'] p")
	private WebElement phoneNumberError;

	@FindBy(id="totalPriceForTrip")
	private WebElement totalPriceTrip;

	@FindBy(css="h2.faceoff-module-title")
	private WebElement subtitle;


	@FindBy(css="h6[class='flight-info-date type-200']")
	private List<WebElement> listDates;

	@FindBy(css=".location-info.type-300")
	private List<WebElement> listDepartureReturning;

	@FindBy(css="span.departure-arival-time")
	private List<WebElement> listTimeTravel;



	@FindBy(css="div.product-content a[role='button']")
	private List<WebElement> listNames;

	@FindBy(css="span[data-price-update='tripTotal']")
	private WebElement finalPrice;

	@FindBy(css="h2[class='title-main']")
	private List<WebElement> listSubtitles;


	public CheckOutPage(WebDriver driver) {
		super(driver);

	}

	/**
	 * Method to return the subtitle	
	 * @return subtitle
	 */
	public String getSubTitle() {

		return getText(subtitle);
	}

	/**
	 * Method to write and return the name	
	 * @return name
	 */
	public String getFirstName(String name) {

		inputFirstName.sendKeys(name);
		Logger.printInfo("Text: "+ inputFirstName.getAttribute("value"));
		return inputFirstName.getAttribute("value");
	}

	/**
	 * Method to write and return the Middle name	
	 * @return Middle name
	 */
	public String getMiddleName(String mName) {
		middleName.sendKeys(mName);
		return middleName.getAttribute("value");
	}

	/**
	 * Method to write and return the Middle Last name	
	 * @return Middle last name
	 */
	public String getLastName(String lastNameP) {
		lastName.sendKeys(lastNameP);
		return lastName.getAttribute("value");
	}

	/**
	 * Method to write and return the phoneNumber	
	 * @return phoneNumber
	 */
	public String getPhoneNumber(String phoneNumberP) {

		phoneNumber.sendKeys(phoneNumberP);
		return phoneNumber.getAttribute("value");
	}

	/**
	 * Method to verify the total price from the previous page	
	 * @return boolean
	 */
	public boolean verifyTotalPriceTrip() {

		FlightsDTO flight = CONTEXT.get("FlightInfo");
		return flight.getFlightTotalPrice().equalsIgnoreCase(totalPriceTrip.getText())?true:false;
	}

	/**
	 * Method to wait the page	
	 * @return boolean
	 */
	public void waitCheckOutPage() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div[class='site-content cols-row cf']"), By.cssSelector("#flight-date-leg1-seg1")));
	}


	/**
	 * Method to verify the dates of the flight according to the ticket selected in previous page
	 * @return boolean
	 */
	public boolean verifyDatesFlights(String option) {

		FlightsDTO flight = CONTEXT.get("FlightInfo");

		List<String> listDate = listDates.stream().map(x-> x.getText().replace(" ", "")).collect(Collectors.toList());

		if(option.equalsIgnoreCase("LAS")) {

			Logger.printInfo("flight.getFlightDateDeparture() "  + flight.getFlightDateDeparture());
			return (flight.getFlightDateDeparture().equalsIgnoreCase(listDate.get(0).toString()))?true:false;	
		}

		if(option.equalsIgnoreCase("LAX")) {

			Logger.printInfo("flight.getFlightDateReturn() " + flight.getFlightDateReturn());
			return (flight.getFlightDateReturn().equalsIgnoreCase(listDate.get(1).toString()))?true:false;
		}
		return false;
	}

	/**
	 * Method to verify the time of the flight according to the ticket selected in previous page
	 * @return boolean
	 */
	public boolean verifyTimeFlight(String option) {

		FlightsDTO flight = CONTEXT.get("FlightInfo");
		List<String> listDuration = listTimeTravel.stream().map(x-> x.getText().replace("+1", "").trim()).collect(Collectors.toList());
		Logger.printInfo(listDuration.get(0));
		System.out.println("--");

		if(option.equalsIgnoreCase("LAS")) {

			Logger.printInfo("flight.getFlightDurationTotalDeparture() "+ flight.getFlightDurationTotalDeparture());
			return flight.getFlightDurationTotalDeparture().equalsIgnoreCase(listDuration.get(0))?true:false;
		}
		if(option.equalsIgnoreCase("LAX")) {

			Logger.printInfo("flight.getFlightDurationTotalReturn() " + flight.getFlightDurationTotalReturn());
			Logger.printInfo("listTimeTravel.get(1).getText()" + listDuration.get(1));

			return flight.getFlightDurationTotalReturn().equalsIgnoreCase(listDuration.get(1))?true:false;
		}
		return false;
	}

	/**
	 * Method to verify the place of departure/returning flight according to the ticket selected in previous page
	 * @return boolean
	 */
	public boolean verifyFromToFlight(String option) {

		FlightsDTO flight = CONTEXT.get("FlightInfo");

		List<String> placeFrom= listDepartureReturning.stream().map(x-> x.getText().replace(" (LAS)", "")).collect(Collectors.toList());
		List<String> listPlaceFromTo = new ArrayList<>(placeFrom);
		List<String> placesFromTo= listPlaceFromTo.stream().map(x-> x.replace(" (LAX)", "")).collect(Collectors.toList());

		if(option.equalsIgnoreCase("LAS")) {


			Logger.printInfo("flight.getFlightFrom(): "+ flight.getFlightFrom());
			Logger.printInfo("placesFromTo.get(0).toString(): "+ placesFromTo.get(0).toString());

			return (flight.getFlightFrom().equalsIgnoreCase(placesFromTo.get(0).toString()))?true:false;

		}
		if(option.equalsIgnoreCase("LAX")) {

			flight.getFlightTo();
			Logger.printInfo("placesFromTo.get(0).toString(): "+ placesFromTo.get(1).toString());

			return (flight.getFlightTo().equalsIgnoreCase(placesFromTo.get(1).toString()))?true:false;

		}
		return false;
	}


	/**
	 * Method to verify the subTitle of the page
	 * @return boolean
	 */
	public boolean verifySubTitleFlightHotel() {

		String subTitle= listSubtitles.stream().findFirst().get().getText();
		boolean flag= subTitle.equalsIgnoreCase("Who's flying?")?true:false;
		return flag;

	}

	/**
	 * Method to return the title of the page
	 * @return boolean
	 */
	public String getPageTitleCheckOutPage() {

		return getPageTitle();
	}



}
