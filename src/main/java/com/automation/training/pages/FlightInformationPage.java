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

import com.automation.dto.CruiseDTO;
import com.automation.dto.FlightsDTO;
import com.automation.dto.HotelDTO;
import com.automation.training.utils.Logger;

public class FlightInformationPage extends BasePage{

	@FindBy(css="span[class*='uitk-text uitk-type-500']")
	private WebElement TripTotalPrice;

	@FindBy(css="h2.uitk-heading-4")
	private List<WebElement> ListDepartureReturn;

	@FindBy(css="button[data-test-id='goto-checkout-button']")
	private WebElement btnCheckOut;

	@FindBy(css="div[data-test-id*='flight-review']")
	private List<WebElement> listParents;

	private String listChildsInfo = "div[data-test-id*='flight']";

	@FindBy(css="h3.uitk-heading-7.uitk-flex-item span")
	private List<WebElement> listTimeDepartureReturning;
	
	@FindBy(css="div[data-test-id='flight-operated']")
	private List<WebElement> listDateDepartureReturning;
	
	



	//---------------

	@FindBy(css="h1[class='section-header-main']")
	private WebElement Subtitulo;

	@FindBy(css="div[class='totalContainer']")
	private List<WebElement> listTripTotalPrice;

	@FindBy(css="div[class='flightSummary-expandedDetails bagFeesRequested']")
	private List<WebElement> listDepartureReturnInfo;

	@FindBy(css="button[class='btn-text toggle-trigger']")
	private List<WebElement> listBtnsShowFlyAndBaggageFee;

	@FindBy(css="#bookButton")
	private WebElement btnBooking;

	@FindBy(css="div[class='departureDate type-500']")
	private List<WebElement> listDatesFlight;

	@FindBy(css="div[class='departure'] span")
	private List<WebElement> listTimeInit;

	@FindBy(css="div[class='arrival'] span")
	private List<WebElement> listTimeEnds;

	@FindBy(css="div[class='departure'] div")
	private List<WebElement> listOriginFlights;

	@FindBy(css="div[class='arrival'] div")
	private List<WebElement> listDestinationFlights;

	@FindBy(css="span[class='durationTime type-500']")
	private List<WebElement> ListTotalFlightTime;

	@FindBy(className="tripTotals")
	private List<WebElement> priceGuarantee;

	@FindBy(css="#license-plate #hotel-name")
	private WebElement nameHotel;



	public FlightInformationPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Method to get total price ticket
	 * @param option
	 */
	String totalPrice;
	public boolean getTotalPrice() {

		waitForElementToBeClickable(btnCheckOut);
		waitForChildElementsFromParent(listParents, listChildsInfo);


		if(isPresent(TripTotalPrice)!=null) {
			FlightsDTO flight = new FlightsDTO();

			flight.setFlightTotalPrice(TripTotalPrice.getText());
			CONTEXT.set("FlightInfo", flight);

			return true;
		}
		return false;
	}
	
	/**
	 * Method to get information about place departure and returning
	 * @param option
	 */
	public boolean getPlaceDepartureReturning(String option) {

		if(option.equalsIgnoreCase("LAS")) {

			FlightsDTO flight = CONTEXT.get("FlightInfo");
			flight.setFlightFrom(ListDepartureReturn.get(0).getText());

			CONTEXT.set("FlightInfo", flight);

			return true;
		}
		if(option.equalsIgnoreCase("LAX")) {

			FlightsDTO flight = CONTEXT.get("FlightInfo");
			flight.setFlightTo(ListDepartureReturn.get(1).getText());

			CONTEXT.set("FlightInfo", flight);


			return true;

		}
		return false;
	} 


	public void waitFlightInformation() {
		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("#v2ProductSummary"), By.cssSelector("div")));
	}


	/**
	 * Method to click btn checkout and return a page checkout page 
	 * @param option
	 * @return checkout page
	 */
	public CheckOutPage selectBtnContinueBooking() {

		if(isPresent(btnCheckOut)!=null) {
			btnCheckOut.click();
			return new CheckOutPage(getDriver());
		}
		return null;
	}
	
	/**
	 * Method to get information about time departure and returning
	 * @param option
	 */
	public void getTimeDepartureReturning(String option) {

		FlightsDTO flight = CONTEXT.get("FlightInfo");

		if(option.equalsIgnoreCase("LAS")) {

			flight.setFlightDurationTotalDeparture(listTimeDepartureReturning.get(0).getText());
			CONTEXT.set("FlightInfo", flight);

		}
		if(option.equalsIgnoreCase("LAX")) {

			flight.setFlightDurationTotalReturn(listTimeDepartureReturning.get(1).getText());
			CONTEXT.set("FlightInfo", flight);
		}

	}

	/**
	 * Method to get information about date departure and returning
	 * @param option
	 */
	public void getDateDepartureReturning(String option) {

		FlightsDTO flight = CONTEXT.get("FlightInfo");

		if(option.equalsIgnoreCase("LAS")) {

			List<String> listDates= cleanListDateDepartureReturning(listDateDepartureReturning);
			flight.setFlightDateDeparture(listDates.get(0));
			
			CONTEXT.set("FlightInfo", flight);

		}
		if(option.equalsIgnoreCase("LAX")) {
			
			List<String> listDates= cleanListDateDepartureReturning(listDateDepartureReturning);
			flight.setFlightDateReturn(listDates.get(1));
			CONTEXT.set("FlightInfo", flight);
		}
		
	}

	/**
	 * Method to clean list
	 */
	private List<String> cleanListDateDepartureReturning(List<WebElement> listDateDepRet) {
		
		List<String> dates = listDateDepRet.stream().map(x-> x.getText()).collect(Collectors.toList());
		List<String> listDates = new ArrayList<>();
		
		for (String d : dates) {
			
			Logger.printInfo("List dates: " + d);
			String[] vectorDate= d.split("•");
			vectorDate[0]= vectorDate[1].replace(" ", "").trim();
			listDates.add(vectorDate[0]);
		}
		return listDates;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
