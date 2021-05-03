package com.automation.training.pages;

import static com.automation.training.utils.TestContext.CONTEXT;

import java.util.List;

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

	@FindBy(css="#license-plate span[class^='icon icon-stars']")
	private WebElement starsHotel;

	@FindBy(css="section[class='segmented-list cf']")
	private WebElement containerRooms;

	@FindBy(css="section[class='segmented-list cf'] article[class^='segment no-target'] a[data-control='modal']")
	private List<WebElement> listaRooms;

	@FindBy(css="section[class='segmented-list cf'] article[class='segment no-target room cf room-above-fold branded-deal']")
	private WebElement firstRoomUnRealDeal;

	@FindBy(id="trip-flight-start")
	private WebElement dateDepartureFH;

	@FindBy(id="trip-flight-end")
	private WebElement dateArrivalFH;

	@FindBy(id="hotelCheckinDateFullFormat")
	private WebElement dateCheckInHotel;

	@FindBy(id="hotelCheckoutDateFullFormat")
	private WebElement dateCheckOutHotel;

	@FindBy(id="trip-flight-to")
	private WebElement flightFromFH;

	@FindBy(id="trip-flight-from")
	private WebElement flightToFH; 

	@FindBy(id="trip-summary-hotel-title")
	private WebElement hotelName;

	@FindBy(id="departure-time-automation-label-0")
	private WebElement timeDepartFlightFrom;

	@FindBy(id="arrival-time-automation-label-0")
	private WebElement timeArrivalFromFH;

	@FindBy(id="duration-automation-label-0")
	private WebElement totalFlightDurationFrom;

	@FindBy(id="departure-time-automation-label-1")
	private WebElement timeDepartureFlightTO;

	@FindBy(id="arrival-time-automation-label-1")
	private WebElement timeArrivalFlightTO;

	@FindBy(id="duration-automation-label-1")
	private WebElement totalFlightDurationTo;

	@FindBy(css="#totalPriceSubTitle Strong")
	private WebElement totalValueFlight;

	@FindBy(css="#FlightUDPBookNowButton1 button")
	private WebElement btnNextFinalDetails;

	@FindBy(id="covid-alert-refundability-0")
	private WebElement modal;

	@FindBy(css="div[class='lead-price']")
	private WebElement parcial;

	@FindBy(css="img#interstitial-secondary-image")
	private WebElement imagenWait;

	@FindBy(css="section.mis-bg-wrapper")
	private WebElement sectionLoader;

	@FindBy(css="span.updated-price")
	private List<WebElement> valueCruise;

	@FindBy(css="div.title-on-ship-image")
	private WebElement nameCruise;

	@FindBy(css="div.card-content-detail.departure-city")
	private WebElement departureCruise;

	@FindBy(css="div.card-content-detail.sailing-dates")
	private WebElement departureDateCruise;


	public FlightInformationPage(WebDriver driver) {
		super(driver);
	}

	public boolean verifyTripTotalPrice(){

		elementoPresente(By.cssSelector("div[class='totalContainer']"));
		List<WebElement> listPrices= obtainsList(listTripTotalPrice, By.cssSelector("span[class='packagePriceTotal']"));

		if(listPrices.get(1).getText().equalsIgnoreCase(""))	{

			listPrices.get(1).getText();
			return false;
		} else {

			FlightsDTO FlyRet = CONTEXT.get("FlyRet");

			FlyRet.setFlightTotalPrice(listPrices.get(1).getText());
			CONTEXT.set("FlyRet", FlyRet);
			return true;

		}
	}

	public boolean verifyDepartureReturnInfoFlight(){

		elementoPresente(By.cssSelector("button[class='btn-text toggle-trigger']"));

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");
		FlightsDTO fly = CONTEXT.get("fly");

		if(!listDatesFlight.get(0).getText().equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("Departure date is Not equals with the users choice");
			return false;
		}

		if(!listDatesFlight.get(1).getText().equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())){
			Logger.printInfo("Return date is Not equals with the users choice");
			return false;
		}

		if(!listTimeInit.get(0).getText().equalsIgnoreCase(flightExpectedDep.getFlightTimeInitDeparture())) {
			Logger.printInfo("Departure time beging is Not the same");
			return false;
		}

		if(!listTimeInit.get(2).getText().equalsIgnoreCase(flightExpectedRet.getFlightTimeInitDeparture())) {
			Logger.printInfo("Return time flight is not the same with the user choise");
			return false;
		}

		if(!listOriginFlights.get(0).getText().equalsIgnoreCase(fly.getFlyFrom().toString())){
			Logger.printInfo("Place to beging DepartureThe choise is not the same with the user fill at the begining of the process");
			return false;
		}

		if(!listOriginFlights.get(1).getText().equalsIgnoreCase(fly.getFlyTo().toString())){
			Logger.printInfo("place to begind return The choise is not the same with the user fill at the begining of the process");
			return false;
		}

		if(!listDestinationFlights.get(0).getText().equalsIgnoreCase(fly.getFlyTo().toString())) {
			Logger.printInfo("Place to End Flight from Departure The choise is not the same with the user fill at the begining of the process");
			return false;
		}

		if(!listDestinationFlights.get(1).getText().equalsIgnoreCase(fly.getFlyFrom().toString())) {
			Logger.printInfo("Place to end flight from Return The choise is not the same with the user fill at the begining of the process");
			return false;
		}

		if(!listTimeEnds.get(0).getText().equalsIgnoreCase(flightExpectedDep.getFlightTimeEndDeparture())) {
			Logger.printInfo("The departure time is not the same");
			return false;
		}

		if(!listTimeEnds.get(2).getText().equalsIgnoreCase(flightExpectedRet.getFlightTimeEndDeparture())) {
			Logger.printInfo("the return time is not the same");
			return false;
		}

		if(!ListTotalFlightTime.get(0).getText().equalsIgnoreCase(flightExpectedDep.getFlightDurationTotalDeparture())) {
			Logger.printInfo("Total time flight departure is Not the same");
			return false;
		}

		if(!ListTotalFlightTime.get(2).getText().equalsIgnoreCase(flightExpectedRet.getFlightDurationTotalReturn())) {
			Logger.printInfo("Total time flight return is Not the same");
			return false;
		} 
		return true;

	}

	public void waitFlightInformation() {
		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("#v2ProductSummary"), By.cssSelector("div")));
	}

	public boolean verifyDateDepartureArrivalFH(){

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		Boolean isSameDateDept=false, isSameArrivalDate=false , isSameDeptArrivDate=false;

		if(dateDepartureFH.getText().equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("Departure date is equals with the users choice");
			isSameDateDept= true;
		} else { 
			Logger.printInfo("Departure date is not equals with the users choice");
			isSameDateDept=false;
		}

		if(dateArrivalFH.getText().equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())){
			Logger.printInfo("Return date is equals with the users choice");
			isSameArrivalDate=true;
		}
		else {
			Logger.printInfo("Return date is not equals with the users choice");
			isSameArrivalDate=false;
		}

		if( !isSameDateDept && !isSameArrivalDate) {
			isSameDeptArrivDate=false;	
		} else {
			isSameDeptArrivDate=true;
		}

		return isSameDeptArrivDate;
	}

	public boolean verifyTimeDepartureArrivalFromFH(){

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");

		Boolean isSameTimeDepFlightFrom=false,  isSameTimeArrival=false, isSameTime=false;

		if(timeDepartFlightFrom.getText().equalsIgnoreCase(flightExpectedDep.getFlightTimeInitDeparture())) {
			Logger.printInfo("Departure time beging is the same");
			isSameTimeDepFlightFrom=true;
		} else {
			Logger.printInfo("Departure time beging is Not the same");
			isSameTimeDepFlightFrom=false;
		}

		if(timeArrivalFromFH.getText().equalsIgnoreCase(flightExpectedDep.getFlightTimeEndDeparture())) {
			Logger.printInfo("The departure time is the same");
			isSameTimeArrival=true;
		} else {
			isSameTimeArrival=false;
			Logger.printInfo("The departure time is not the same");
		}


		if( !isSameTimeDepFlightFrom && !isSameTimeArrival) {
			isSameTime=false;	
		}
		else isSameTime=true;

		return isSameTime;

	}

	public boolean verifyTimeDepartureArrivalFlightTo(){

		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		if(!timeDepartureFlightTO.getText().equalsIgnoreCase(flightExpectedRet.getFlightTimeInitDeparture())) {
			Logger.printInfo("Arrival time init flight is Not the same with the user choise");

			return false;
		}

		if(!timeArrivalFlightTO.getText().equalsIgnoreCase(flightExpectedRet.getFlightTimeEndDeparture())) {
			Logger.printInfo("The arrival end time is Not the same");
			return false;
		}

		return true;
	}

	public boolean verifyPlaceFlightFromTo(){

		FlightsDTO fly = CONTEXT.get("fly");

		Boolean isSamePlaceFrom=false,  isSamePlaceTo=false, isSamePlace=false;

		if(flightFromFH.getText().substring(0,9).equalsIgnoreCase(fly.getFlightFrom().substring(0,9))){
			Logger.printInfo("Place to beging DepartureThe choise is the same with the user fill at the begining of the process");
			isSamePlaceFrom=true;
		}else {
			isSamePlaceFrom=false;
		}

		if(flightToFH.getText().substring(0,9).equalsIgnoreCase(fly.getFlightTo().substring(0,9))){
			Logger.printInfo("place to begind return The choise is the same with the user fill at the begining of the process");
			isSamePlaceTo=true;
		}
		else {
			isSamePlaceTo=false;
		}

		if( !isSamePlaceFrom && !isSamePlaceTo) {
			isSamePlace=false;	
		}else {
			isSamePlace=true;
		}

		return isSamePlace;

	}

	public boolean verifyHotelNameFH(){

		HotelDTO hotel = CONTEXT.get("hotel");

		boolean isSameName;
		if(hotelName.getText().equalsIgnoreCase(hotel.getName())) {
			Logger.printInfo("The departure time is the same");
			isSameName=true;
			return isSameName;
		}
		else {
			Logger.printInfo("The departure time is not the same");
			isSameName=false;
			return isSameName;
		}
	}

	public boolean verifyHotelCheckInOutDate() {

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		Boolean  isSameCheckinDate, isSameCheckOutDate, isSameDate;

		if(dateCheckInHotel.getText().equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("The departure time is the same");
			isSameCheckinDate=true;
		}else {
			Logger.printInfo("The departure time is not the same");
			isSameCheckinDate=false;
		}

		if(dateCheckOutHotel.getText().equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())) {
			Logger.printInfo("The departure time is the same");
			isSameCheckOutDate=true;
		}else {
			isSameCheckOutDate=false;
		}

		if( !isSameCheckinDate && !isSameCheckOutDate) {
			isSameDate=false;	
		}
		else isSameDate=true;

		return isSameDate;

	}

	public boolean verifyTotalFlightDurationFrom() {

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");

		Boolean isSameTotalFlight=false;

		if(totalFlightDurationFrom.getText().equalsIgnoreCase(flightExpectedDep.getFlightDurationTotalDeparture())) {
			Logger.printInfo("Total time flight departure is the same");
			isSameTotalFlight=true;
			return isSameTotalFlight;
		} else {
			Logger.printInfo("Total time flight departure is not the same");
			isSameTotalFlight=false;
			return isSameTotalFlight;
		}
	}

	public boolean verifyTotalDurationFlightTo() {

		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		Boolean isSameTotalFlight=false;

		if(totalFlightDurationTo.getText().equalsIgnoreCase(flightExpectedRet.getFlightDurationTotalReturn())) {
			Logger.printInfo("Total time flight return is the same");
			isSameTotalFlight=true;
			return isSameTotalFlight;
		} else {
			Logger.printInfo("Total time flight return is not the same");
			return isSameTotalFlight=false;
		}

	}

	public void obtainTotalTripValueFH() {

		HotelDTO hotel = new HotelDTO();
		hotel= CONTEXT.get("hotel");
		hotel.setValue(totalValueFlight.getText());
		CONTEXT.set("hotel", hotel);

	}

	public CheckOutPage clickBtnNextFinalDetails() {
		if(isPresent(btnNextFinalDetails)!=null) {
			btnNextFinalDetails.click();
			return new CheckOutPage(getDriver());
		}
		return null;
	}

	public CheckOutPage selectBtnContinueBooking() {

		if(isPresent(btnBooking)!=null) {
			btnBooking.click();
			return new CheckOutPage(getDriver());
		}
		return null;
	}

	public boolean verifyPriceGuaranteeText() {

		WebElement e = getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(priceGuarantee.get(1), By.cssSelector("span.packagePriceTotal")));

		if(e.getClass()!= null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyHotelNameSelected() {

		Logger.printInfo("verify name selected");

		waitForPageToBeLoaded(getDriver(), 20);

		elementoPresente(By.cssSelector(".page-header"));
		elementoPresente(By.cssSelector("#license-plate"));
		elementoPresente(By.cssSelector(".lead-price"));

		HotelDTO hotel = new HotelDTO();
		hotel= CONTEXT.get("hotel");


		String nameHotelExpected = nameHotel.getText();
		return hotel.getName().equals(nameHotelExpected);
	}

	public boolean verifyHotelValueSelected() {

		HotelDTO hotel = new HotelDTO();
		hotel= CONTEXT.get("hotel");

		String valueHotelExpected = parcial.findElement(By.partialLinkText("$")).getText();
		boolean isHotelValueEquals= hotel.getValue().equals(valueHotelExpected)?true:false;
		return isHotelValueEquals;
	}

	public boolean verifyHotelStarsSelected() {

		HotelDTO hotel = new HotelDTO();
		hotel= CONTEXT.get("hotel");

		String starsHotelExpected= starsHotel.getAttribute("title");
		boolean isHotelStarsEquals= hotel.getStars().equals(starsHotelExpected)?true:false;
		return isHotelStarsEquals;
	}

	public boolean verifyCruiseSelected() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div.user-decisions"), By.cssSelector("div.infosite.decision-point.margin-top.box")));

		String valueCruiseExpected = valueCruise.get(0).getText();
		String nameCruiseExpected= nameCruise.getText();
		String departureCruiseExpected= departureCruise.getText();
		String departureDateCruiseExpected= departureDateCruise.getText();

		CruiseDTO cruise = new CruiseDTO();
		cruise= CONTEXT.get("cruise");

		boolean isCruiseDepartureDateSame;
		if(departureDateCruiseExpected.contains(cruise.getDateDeparture())) { 
			isCruiseDepartureDateSame=true;
		} else {
			isCruiseDepartureDateSame=false;
		}

		boolean isNameCruiseSame= cruise.getName().equalsIgnoreCase(nameCruiseExpected)?true:false;
		boolean isDepartureCruiseSame= cruise.getFrom().equals(departureCruiseExpected)?true:false;
		boolean isValueCruiseSame= cruise.getValue().equals(valueCruiseExpected)?true:false;

		if(isNameCruiseSame 
				&& isDepartureCruiseSame
				&& isValueCruiseSame
				&& isCruiseDepartureDateSame) {
			return true;
		}
		else {
			return false;
		}
	}

	public FlightsSearchPage selectTheFirstRoomOption() {

		Logger.printInfo("select the first Room");
		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(containerRooms, By.cssSelector("article[class='segment no-target room cf room-above-fold']")));
		listaRooms.stream().findFirst().get().click();

		modalAppear(modal);

		return new FlightsSearchPage(getDriver());
	}

}
