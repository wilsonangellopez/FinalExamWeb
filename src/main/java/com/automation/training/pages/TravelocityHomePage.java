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
	


	//---

	@FindBy(css="#tab-package-tab-hp")
	private WebElement btnVacationPackages;

	@FindBy(css="#fh-fh-hp-package")
	private WebElement RbnFlightHotel;

	@FindBy(css="a[aria-controls*='roundtrip']")
	private WebElement btnRoundtrip;

	@FindBy(css="button.btn-primary.btn-action.gcw-submit")
	private List<WebElement> listBtnSearchFly;

	//	@FindBy(id="flight-departing-hp-flight") //d1-btn
	@FindBy(id="d1-btn") //
	private WebElement inputCalendardDeptFlight;

	@FindBy(id="flight-returning-hp-flight")
	private WebElement inputCalendarRetFlight;

	@FindBy(id="package-departing-hp-package")
	private WebElement inputCalendarDepFlightHotel;

	@FindBy(id="package-returning-hp-package")
	private WebElement inputCalendarRetFlightHotel;

	@FindBy(className="datepicker-cal-month")
	private WebElement calendarioDiv;

	@FindBy(css="button[data-stid='date-picker-paging']:nth-child(2)")
	private WebElement btnNextCalendar;

	@FindBy(css="caption.datepicker-cal-month-header")
	private WebElement MesesCalendar;

	@FindBy(id="search-button-hp-package")
	private WebElement btnSearchFH;

	@FindBy(id="package-rooms-hp-package")
	private WebElement drpRoomsFlightHotel;

	@FindBy(id="hotel-rooms-hp-hotel")
	private WebElement drpRoomsHotel;



	@FindBy(id="hotel-checkin-hp-hotel")
	private WebElement inputCheckInHotel;

	@FindBy(id="hotel-checkout-hp-hotel")
	private WebElement inputCheckOutHotel;

	@FindBy(id="package-checkin-hp-package")
	private WebElement inputCheckInHotelPartial;

	@FindBy(id="package-checkout-hp-package")
	private WebElement inputCheckOutHotelPartial;

	@FindBy(css="#partialHotelBooking-hp-package")
	private WebElement chkBtnHotelPartOfStay;

	@FindBy(id="tab-cruise-tab-hp")
	private WebElement btnCruises;

	@FindBy(id="cruise-destination-hp-cruise")
	private WebElement dropDownListGoingTo;

	@FindBy(id="cruise-start-date-hp-cruise")
	private WebElement inputCruiseDepartureEarly;

	@FindBy(id="cruise-end-date-hp-cruise")
	private WebElement inputCruiseLate;

	@FindBy(css="section#WizardHero")
	private WebElement frame;

	@FindBy(css="button[class*='new-date-picker-day']")
	private List<WebElement> listBtnsCalendar;

	@FindBy(css="a.error-link")
	private List<WebElement>errorLink;

	@FindBy(css="div[class='cols-nested'] label button[class='btn-primary btn-action gcw-submit '][data-gcw-change-submit-text='Search']")
	private List<WebElement> listBtnSearchHotel;

	public void clickFlights() {

		Logger.printInfo("click in fligts");
		getWait().until(ExpectedConditions.elementToBeClickable(btnFlights));
		btnFlights.click();

	}

	public boolean verifyPageOpen() {

		boolean ispresent= isPresent(RbnFlightHotel)!=null?true:false;
		return ispresent;
	}

	public boolean clickVacationPackages() {

		if(isPresent(btnVacationPackages)!=null) {
			getWait().until(ExpectedConditions.elementToBeClickable(btnVacationPackages));
			btnVacationPackages.click();	

			if(!RbnFlightHotel.isSelected()){
				RbnFlightHotel.click();
				return true;
			}
			else {
				return true;
			}
		}
		return false;
	}

	public void clickRoundTrip() {

		Logger.printInfo("click in RoundTrip");
		if(isPresent(btnRoundtrip).getText().toString().equalsIgnoreCase("RoundTrip")){
			click(btnRoundtrip);	
		}

	}

	private void createObjetContext(String text, String destination) {

		FlightsDTO fly = new FlightsDTO();

		fly = CONTEXT.get("fly"); 
		fly.setFlightTo(text);
		fly.setFlyTo(destination);
		CONTEXT.set("fly", fly);
	}



	//	private void createObjetContext(String param, String fromTo) {
	//
	//		FlightsDTO fly = new FlightsDTO();
	//
	//		switch (param) {
	//
	//		case "Las Vegas, NV (LAS-McCarran Intl.)":
	//
	//			fly.setFlightFrom(param);
	//			fly.setFlyFrom(fromTo);
	//			CONTEXT.set("fly", fly);
	//			break;
	//
	//		case "Los Angeles, California":
	//
	//			fly = CONTEXT.get("fly"); 
	//			fly.setFlightTo(param);
	//			fly.setFlyTo(fromTo);
	//			CONTEXT.set("fly", fly);
	//			break;
	//		}
	//	}

	public void writeGeneric() {


	}

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


		// aqui buscar el elemento  para quitar el metodo findElementByText
		//		WebElement element = findElementByText(text);


		/*if(isPresent(element).getClass()!=null) {
			click(element);
			Logger.printInfo("click in input" + element.getAttribute("class").toString());	
		}

		writing(element, destination);
		pressEnter(element);
		createObjetContext(text, destination);*/


		// borrar esto

		//		switch (fromTo) {
		//		case "LAS":
		//
		//			String from= "Las Vegas, NV (LAS-McCarran Intl.)";
		//			writing(element, from);
		//			pressEnter(element);	
		//			createObjetContext(from, fromTo );
		//			break;
		//
		//		case "LAX":
		//
		//			String to="Los Angeles, California";
		//			writing(element, to);
		//			pressEnter(element);
		//			createObjetContext(to, fromTo);
		//			break;
		//
		//		}

	}

	public void selectDateForFlight(String option, int monthFly) {

		if(option.equalsIgnoreCase("Departure")) {
			
			Logger.printInfo("Opcion enviada: " + option);

			click(inputCalendardDeptFlight);
			clickingNextBtnCalendar(btnNextCalendar);
			clickFutureDate(getFutureDate(monthFly));
		}
		if(option.equalsIgnoreCase("Return")) {
			
			clickingNextBtnCalendar(btnNextCalendar);
			clickFutureDate(getFutureDate(monthFly));
		}




		// clikar sobre el selector q se debe armar con la fecha futura

		//calcular la fecha de regreso 
		//clickar sobre el selector q se acaba de armar con la fecha futura

		//selectDateBetter(inputCalendardDeptFlight, inputCalendarRetFlight, true, monthFly );
	}
	
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

	public void selectDayForFlightHotel() {

		selectDateBetter(inputCalendarDepFlightHotel, inputCalendarRetFlightHotel, false, 20L );
	}

	public void selectDayForHotel() {

		selectDateBetter(inputCheckInHotel, inputCheckOutHotel, false, 13L );
	}

	public void selectDayForFlightPartial() {

		selectDateBetter(inputCalendarDepFlightHotel, inputCalendarRetFlightHotel, true, 2L );
	}

	public void selectDayForHotelPartial() {

		writeInCalendarDirectPartial(inputCheckInHotelPartial, inputCheckOutHotelPartial, 1L );
	}

	public void selectDayForCruise() {

		selectDateBetter(inputCruiseDepartureEarly, inputCruiseLate, true, 3L );
	}

	public void selectDateBetter(WebElement inputCalendarDeparture, WebElement inputCalendarReturn, boolean isMonth, Long quantity) {

		String dateFuture = getFutureDate(quantity);
		click(webElement);



		selectDate(inputCalendarDeparture, date);
		setDateInContext(date, true);

		if(isMonth) {
			date = date.plusMonths(quantity);
		}
		else {
			date=date.plusDays(quantity);
		}

		selectDate(inputCalendarReturn, date);
		setDateInContext(date, false);
	}

	/**
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

	private boolean writeInCalendarDirect(WebElement inputCalendar, LocalDate date) {

		if( !btnNextCalendar.isDisplayed() ) {
			String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
			inputCalendar.sendKeys(del + date.getMonthValue()+ "/" + date.getDayOfMonth()+ "/" + date.getYear());
			pressEscape(inputCalendar);
			return false;
		}
		return true;
	}

	private void writeInCalendarDirectPartial(WebElement inputCalendar, WebElement inputCalendarReturn, Long quantity) {

		LocalDate date = LocalDate.now();
		date = date.plusMonths(4L);

		String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
		inputCalendar.sendKeys(del + date.getMonthValue()+ "/" + date.getDayOfMonth()+ "/" + date.getYear());
		pressEscape(inputCalendar);

		date = date.plusMonths(quantity);
		del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
		inputCalendarReturn.sendKeys(del + date.getMonthValue()+ "/" + date.getDayOfMonth()+ "/" + date.getYear());
		pressEscape(inputCalendarReturn);

	}

	public void setDateInContext(LocalDate date, boolean isDeparture) {

		FlightsDTO fly = new FlightsDTO();

		String monthText= date.getMonth().getDisplayName(TextStyle.FULL, new Locale("en", "En"));
		monthText= monthText.substring(0, 3);
		String dateMonthDay= monthText+ " " + date.getDayOfMonth(); 

		if(isDeparture) {

			fly.setFlightDateDeparture(dateMonthDay);
			CONTEXT.set("flyDepFH", fly);
		}else {

			fly = CONTEXT.get("flyDepFH"); 
			fly.setFlightDateReturn(dateMonthDay);
			CONTEXT.set("flyDepFH", fly);

		}

	}

	private void selectDate(WebElement inputCalendar, LocalDate date) {




		/*boolean isBtnNextVisible=false;

		if(isPresent(inputCalendar)!=null && inputCalendar.isDisplayed() ) {

			inputCalendar.click();

			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed() ) {

				isBtnNextVisible=writeInCalendarDirect(inputCalendar, date);

				if(isBtnNextVisible) {

					String yearFromList= listBtnsCalendar.stream()					
							.filter(x-> date.getDayOfMonth() == Integer.parseInt(x.getAttribute("data-day")))
							.findFirst().get().getAttribute("data-year").toString();

					int currentYearInCalendar= Integer.parseInt(yearFromList);

					while (date.getYear()>currentYearInCalendar) {

						currentYearInCalendar=getYear(date.getYear());
					}

					String monthsFromList= listBtnsCalendar.stream()					
							.filter(x-> date.getDayOfMonth() == Integer.parseInt(x.getAttribute("data-day")))
							.findFirst().get().getAttribute("data-month").toString();

					int currentMonth= Integer.parseInt(monthsFromList);

					while (date.getMonthValue()-1>currentMonth) {

						currentMonth=getMonth(date.getMonthValue()-1);
					}

					listBtnsCalendar.stream()					
					.filter(x-> date.getDayOfMonth() == Integer.parseInt(x.getAttribute("data-day")))
					.filter(x-> date.getMonthValue()-1 ==Integer.parseInt(x.getAttribute("data-month")))
					.filter(x-> date.getYear() == Integer.parseInt(x.getAttribute("data-year")))
					.findFirst().get().click();


				}
			}
		}*/

	}

	private int getMonth( int iMonthFutureInCalendar) {

		int currentMonth= Integer.parseInt(listBtnsCalendar.get(0).getAttribute("data-month").toString());

		if(Integer.parseInt(listBtnsCalendar.get(0).getAttribute("data-month").toString())==iMonthFutureInCalendar) {
			return currentMonth;
		}

		scrollToElement(btnNextCalendar);

		try {

			clickByElementJS(getDriver(), btnNextCalendar);

		} catch (InterruptedException e) {
			Logger.printInfo("error at JavaScript action");
			e.printStackTrace();
		}

		List<WebElement> listaBtnDias2 = getDriver().findElements(By.cssSelector("div.datepicker-cal-month button.datepicker-cal-date"));
		currentMonth= Integer.parseInt(listaBtnDias2.get(0).getAttribute("data-month").toString());

		return currentMonth;

	}

	private int getYear( int iYearFuture) {

		int iCurrentYear= Integer.parseInt(listBtnsCalendar.get(0).getAttribute("data-year").toString());

		if(Integer.parseInt(listBtnsCalendar.get(0).getAttribute("data-year").toString())==iYearFuture) {
			return iCurrentYear;
		}

		scrollToElement(btnNextCalendar);

		try {

			clickByElementJS(getDriver(), btnNextCalendar);

		} catch (InterruptedException e) {
			Logger.printInfo("error at JavaScript action");
			e.printStackTrace();
		}

		List<WebElement> listBtnsyears = getDriver().findElements(By.cssSelector("div.datepicker-cal-month button.datepicker-cal-date"));
		iCurrentYear= Integer.parseInt(listBtnsyears.get(0).getAttribute("data-year").toString());

		return iCurrentYear;

	}

	public FlightsSearchPage clickBtnSearch(boolean param) {

		if(param) {
			listBtnSearchFly.stream().findFirst().get().click();
			verifyErrorDisplayFH();

		} else {
			btnSearchFH.click();
		}

		return new FlightsSearchPage(getDriver());
	}

	public void selectRoomsFlightHotel() {

		selectRoomsInFlightHotel(drpRoomsFlightHotel);
	}

	public void selectRoomsHotel() {

		selectRoomsInFlightHotel(drpRoomsHotel);
	}

	private void selectRoomsInFlightHotel(WebElement drpRoomGeneric) {

		Select drpRoom = new Select (drpRoomGeneric);
		drpRoom.selectByValue("1");

		HotelDTO hotel = new HotelDTO();
		hotel.setRooms("1");
		CONTEXT.set("hotel", hotel);
	}

	public void selectQuantityAdults(String quantity) {

		Logger.printInfo("In method selectQuantityAdults");
		Select drpQuantityAdults = new Select (getDriver().findElement(By.cssSelector("#package-1-adults-hp-package, #hotel-1-adults-hp-hotel")));
		drpQuantityAdults.selectByValue(quantity);

		HotelDTO hotel = new HotelDTO();
		hotel = CONTEXT.get("hotel");
		hotel.setQuantityAdults(quantity);
		CONTEXT.set("hotel", hotel);

	}

	public boolean clickBtnHotels() {

		Logger.printInfo("Clicking btn hotel");

		if(isPresent(btnHotel)!=null) {
			btnHotel.click();
			return true;
		}
		return false;
	}

	private void reFillTextInput(WebElement element) {

		boolean isMontevideo = false;
		String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
		inputGoingTo.sendKeys(del + "Montevideo, Uruguay");
		isMontevideo = validateFilled();
		if(isMontevideo) {
			clickInFrame();
		}
		else {
			reFillMontevideoUruguay();
		}
	}

	private void reFillMontevideoUruguay() {

		boolean isMontevideo = false;
		String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
		inputGoingTo.sendKeys(del + "Montevideo, Uruguay");
		isMontevideo = validateFilled();
		if(isMontevideo) {
			clickInFrame();
		}
		else {
			reFillMontevideoUruguay();
		}
	}

	private boolean validateFilled() {

		String text = inputGoingTo.getAttribute("value");
		if(text.equalsIgnoreCase("Montevideo, Uruguay")) {
			return true;
		}
		return false;


	}

	private void clickInFrame() {

		frame.click();
	}

	public boolean fillGoingTo() {

		if(isPresent(inputGoingTo)!=null) {
			reFillMontevideoUruguay();
			btnHotel.click();
			return true;			
		}
		return false;
	}

	public FlightsSearchPage clickSearchBtnHotel() {

		boolean isError=false;
		WebElement ultimoBtn= listBtnSearchHotel.stream().reduce((first, second) -> second).get();
		ultimoBtn.click();
		isError = verifyLongerTrip();
		if(!isError) {
			return new FlightsSearchPage(getDriver());
		}
		return null;
	}

	public boolean selectCheckBoxPartStay() {

		Logger.printInfo("In method check box");

		if(isPresent(chkBtnHotelPartOfStay)!=null) {
			if(!chkBtnHotelPartOfStay.isSelected()) {

				chkBtnHotelPartOfStay.click();
				return true;
			}
			else {
				return true;
			}
		}
		return false;
	}

	public boolean verifyErrorDisplayFH() {

		Long count= errorLink.stream().filter(x-> x.getText().contains("Your partial check-in and check-out")).count();
		if(count>0) {
			return true;	
		}else {
			return false;
		}

	}

	public void clickBtnCruises() {

		if(isPresent(btnCruises)!=null) {
			btnCruises.click();
		}else {
			Logger.printInfo("Element not present" + btnCruises.getClass());
		}
	}

	public boolean clickDropDownList() {

		getWait().until(ExpectedConditions.elementToBeClickable(dropDownListGoingTo));
		Select drp = new Select(dropDownListGoingTo);
		drp.selectByValue("europe");
		boolean isSelectDrp= drp.getFirstSelectedOption().getText().equals("Europe")?true:false;
		return isSelectDrp;
	}

	public FlightsSearchPage pressEnterInCruise() {

		pressEnter(inputCruiseLate);
		return new FlightsSearchPage(getDriver());
	}

	public boolean verifyLongerTrip() {

		boolean isLonger=false;

		if(elementoPresente(By.cssSelector("a.error-link"))) {
			if(isPresent(errorLink.get(0))!=null) {

				String text= errorLink.get(0).getText();

				if(text.contains("Your length of stay cannot be longer than ")) {
					return isLonger=true;
				}
			}
			else {
				isLonger=false;
			}
		}
		return isLonger;

	}

	public FlightsSearchPage clickSearchButton() {
		
		click(btnSearch);
		
		return new FlightsSearchPage(getDriver());
	}

}
