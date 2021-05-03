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

	@FindBy(css="div.product-content a[role='button']")
	private List<WebElement> listNames;

	@FindBy(css="div.product-content div[class='date-info']")
	private List<WebElement> listDates;

	@FindBy(css="div.product-content div[class='location-info']")
	private List<WebElement> listFromTOFlight;

	@FindBy(css="span[data-price-update='tripTotal']")
	private WebElement finalPrice;

	@FindBy(css="h2[class='title-main']")
	private List<WebElement> listSubtitles;

	@FindBy(id="secondary-content")
	private WebElement sectionHotelInfo;

	public CheckOutPage(WebDriver driver) {
		super(driver);

	}



	public String getSubTitle() {
		return getText(subtitle);
	}


	public String getFirstName(String name) {

		inputFirstName.sendKeys(name);
		Logger.printInfo("Text: "+ inputFirstName.getAttribute("value"));
		return inputFirstName.getAttribute("value");
	}

	public String getMiddleName(String mName) {
		middleName.sendKeys(mName);
		return middleName.getAttribute("value");
	}

	public String getLastName(String lastNameP) {
		lastName.sendKeys(lastNameP);
		return lastName.getAttribute("value");
	}

	public String getPhoneNumber(String phoneNumberP) {

		phoneNumber.sendKeys(phoneNumberP);
		return phoneNumber.getAttribute("value");
	}

	public boolean verifyTotalPriceTrip() {

		FlightsDTO FlyRet = CONTEXT.get("FlyRet");
		boolean bTotalPriceTrip = FlyRet.getFlightTotalPrice().equalsIgnoreCase(totalPriceTrip.getText())?true:false;
		return bTotalPriceTrip;

	}

	public void waitCheckOutPage() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div[class='site-content cols-row cf']"), By.cssSelector("#secondary-content")));
	}

	public boolean verifyHotelName(){

		HotelDTO hotel = CONTEXT.get("hotel");
		List<String>names= listNames.stream().map(x-> x.getText()).collect(Collectors.toList());
		boolean flag= hotel.getName().equalsIgnoreCase(names.get(1))?true:false;

		return flag;
	}

	public boolean verifyDatesHotel() {

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		WebElement listDatesForHotel = listDates.get(1);
		String listDatesHotel= listDatesForHotel.getText();

		String[] date = listDatesHotel.split("-");

		String checkIn= date[0].trim();
		String checkOut= date[1].trim();

		if(!checkIn.equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("Checkin date is not equals with the users choice");
			return false;
		}

		if(!checkOut.equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())){
			Logger.printInfo("Checkin date is not equals with the users choice");
			return false;
		}

		return true;

	}

	public boolean verifyDatesFlights() {

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		List<String[]> dates= listDates.stream().map(x-> x.getText().split("-")).collect(Collectors.toList());

		List<String> listDates = new ArrayList<>();
		for (String[] date : dates) {

			String date1= date[0].trim();
			String date2= date[1].trim();

			listDates.add(date1);
			listDates.add(date2);
			break;
		}

		if(!listDates.get(0).equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("The date for departure is not equals with the users choice");
			return false;
		}

		if(!listDates.get(1).equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())){
			Logger.printInfo("The date date for returning is not equals with the users choice");
			return false;
		}

		return true;
	}

	public boolean verifyFromToFlight() {


		List<String[]> dates= listFromTOFlight.stream().map(x-> x.getText().split(" to ")).collect(Collectors.toList());
		List<String> listDates = new ArrayList<>();
		for (String[] f : dates) {

			String f1= f[0].trim().replace(" (LAS)", "");
			String f2= f[1].trim().replace(" (LAX)", "");

			listDates.add(f1);
			listDates.add(f2);
			break;

		}


		FlightsDTO fly = CONTEXT.get("fly");

		Boolean flagDatesFrom=false,  flagDatesTo=false;

		if(listDates.get(0).equalsIgnoreCase(fly.getFlightFrom().substring(0,9))){
			Logger.printInfo("Place to beging DepartureThe choise is the same with the user fill at the begining of the process");
			flagDatesFrom=true;
		}
		else flagDatesFrom=false;

		if(listDates.get(1).equalsIgnoreCase(fly.getFlightTo().substring(0,11))){
			Logger.printInfo("place to begind return The choise is the same with the user fill at the begining of the process");
			flagDatesTo=true;
		}
		else flagDatesTo=false;

		boolean flagDates= !flagDatesFrom && !flagDatesTo?false:true;

		return flagDates;

	}

	public boolean verifyFinalPriceFH() {

		HotelDTO hotel = CONTEXT.get("hotel");
		boolean flagPrice= hotel.getValue().equalsIgnoreCase(finalPrice.getText())?true:false;
		return flagPrice;
	}

	public boolean verifySubTitleFlightHotel() {

		String subTitle= listSubtitles.stream().findFirst().get().getText();
		boolean flag= subTitle.equalsIgnoreCase("Who's flying?")?true:false;
		return flag;

	}
	
		public String getPageTitleCheckOutPage() {
			
			return getPageTitle();
		}



}
