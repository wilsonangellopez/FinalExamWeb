package com.automation.training.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.dto.CruiseDTO;
import com.automation.dto.FlightsDTO;
import com.automation.dto.HotelDTO;
import com.automation.training.utils.Logger;
import static com.automation.training.utils.TestContext.CONTEXT;

public class FlightsSearchPage extends BasePage {




	@FindBy(id="listings-sort")
	private WebElement sortBy;

	@FindBy(css="option[data-opt-id='ARRIVAL_DECREASING']")
	private WebElement dropDownLastelement;

	@FindBy(css="li[data-test-id='offer-listing']")
	private List<WebElement> listFieldSet;

	private String lstFieldSet= "li[data-test-id='offer-listing']";

	@FindBy(css="div[data-test-id='journey-duration']")
	private List<WebElement> listFlightDuration;

	private String baggageFees = "div[data-test-id='baggage-fee-information']";
	//flightBaggage fess
	//div[data-test-id="baggage-fee-information"]

	
	@FindBy(css="button[data-icon='tool-close']")
	private WebElement btnCloseBaggageFees;
	



	//--------

	@FindBy(css="div.progress-bar")
	private WebElement progresBar;

	@FindBy(css="#flightModuleList li.flight-module.segment.offer-listing")
	private List<WebElement> contenedor;

	@FindBy(css="li[data-test-id='offer-listing'] span[class='medium-bold']")
	private List<WebElement> containerTimeDep;

	@FindBy(xpath="//li[@data-test-id='offer-listing']//button[@data-test-id='select-button' and span/span[text()='Select']]")
	private List<WebElement> listSelectBtns;

	@FindBy(css="button.btn-secondary.btn-action.t-select-btn")
	private List<WebElement> listSelectBtnGeneral;

	@FindBy(xpath="//li[@data-test-id='offer-listing']//button[contains(@data-test-id, 'select-button-1') and span/span[text()='Select this fare']]")
	private List<WebElement> listFareBtnsFinalLA;

	@FindBy(className="title-city-text")
	private WebElement Subtitulo;

	@FindBy(css=".title-date-rtv")
	private WebElement departureDate;

	//@FindBy(css="li[data-test-id='offer-listing']")
	//private List<WebElement> listFieldSet;

	@FindBy(css="span.show-flight-details")
	private List<WebElement> listDetailsBagesFees;

	//div[data-test-id="journey-duration"]
	//@FindBy(xpath="//li[@data-test-id='offer-listing']//span[@data-test-id='duration']")
	//private List<WebElement> listFlightDuration;

	@FindBy(css="fieldset[class='sort-filter-bar control box']")
	private WebElement sortOptions;

	@FindBy(css="button[class='origin fakeLink']")
	private WebElement btnOriginFlightHotel;

	@FindBy(css="button[class='destination fakeLink']")
	private List<WebElement> listBtnDestination;

	@FindBy(css="span[class='day-of-week']+span")
	private List<WebElement> listDates;

	@FindBy(css="h1[class='section-header-main']")
	private WebElement subTitleFlightHotel;

	@FindBy(css="div[class='flex-link-wrap']")
	private List<WebElement> listResultsFH;

	@FindBy(css="div[class='flex-link-wrap'] strong[class='star-rating rating-secondary star rating'] span[class^='icon icon-stars']")
	private List<WebElement> listStars;

	@FindBy(css="button[aria-label='Sort by: Price']")
	private WebElement btnPriceFlightHotel;

	@FindBy(css="div.flex-card div[class='message-flag flex-flag']")
	private List<WebElement> listDiscounts;

	@FindBy(css="div.flex-card")
	private List<WebElement> listCruises;

	@FindBy(css="ul[class='hotel-price'] li[class^='actualPrice']")
	private List<WebElement> listPricesFlightHotel;

	@FindBy(css="#uitk-live-announce")
	private WebElement loaderFH;

	@FindBy(css="div[class='uitk-card uitk-grid messaging-card all-x-padding-three all-y-padding-three'] a")
	private List<WebElement> listLinksHotels;

	@FindBy(css="input[name='length-10-14']")
	private List<WebElement> radioBtnCruisesNights;

	@FindBy(css="div.flex-card")
	private List<WebElement> listContainerCruises;

	@FindBy(css="div[data-test-id='flight-info']")
	private List<WebElement> listFlightsVS;

	@FindBy(id="covid-alert-refundability-0")
	private WebElement modal;

	@FindBy(css="img#interstitial-secondary-image")
	private WebElement imagenEspera;


	public FlightsSearchPage(WebDriver driver) {		
		super(driver);
	}

	public boolean clickDropdownByValue(WebElement element, String data) {

		Logger.printInfo("In dropdown list + From page" + getDriver().getTitle());

		waitForElementToBeClickable(element);
		waitForElementToBeClickable(dropDownLastelement);

		//loaderFlightSearchPage();

		if((isPresent(sortBy)!=null)
				&& (sortBy.isEnabled())) {

			waitForElementToBeClickable(sortBy);
			Select drpSort = clickDropDown(data, sortBy);

			return getTextInDropDownSelected(drpSort,data);
		}
		return false;

	}

	/**
	 * @param data
	 * @return
	 */
	public Select clickDropDown(String data,WebElement element) {


		waitForElementToBeClickable(element);

		Select drpSort = new Select(element);
		drpSort.selectByVisibleText(data);
		return drpSort;
	}

	/**
	 * @param drpSort
	 * @return
	 */
	public boolean getTextInDropDownSelected(Select drpSort, String data) {

		waitForElementToBeClickable(dropDownLastelement);

		WebElement optionSelected= drpSort.getFirstSelectedOption();

		System.out.println("sort selects:  "+ optionSelected.getText());

		return optionSelected.getText().equalsIgnoreCase(data)?true:false;

	}


	/*
	 * public boolean selectDrpDurationShortest() {

		Logger.printInfo("In dropdown list + From page" + getDriver().getTitle());

		loaderFlightSearchPage();

		if((isPresent(sortBy)!=null)
				&& (sortBy.isEnabled())) {

			getWait().until(ExpectedConditions.elementToBeClickable(sortBy));

			Select drpSort = new Select(sortBy);
			drpSort.selectByVisibleText("Duration (Shortest)");
			WebElement optionSelected= drpSort.getFirstSelectedOption();

			if(optionSelected.getText().equalsIgnoreCase("Duration (Shortest)")) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;

	}

	 */

	public boolean verifyQuantityFlightDuration() {

		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[data-test-id='offer-listing']")));

		return listFieldSet.size()==listFlightDuration.size()?true:false;



	}

	public boolean searchResultsAndSelectBtn() {

		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[data-test-id='offer-listing']")));

		int quantityContainers= (int) listFieldSet.stream().count();
		int quantityBtnsSelect= (int) listSelectBtns.stream().count();

		if(quantityContainers==quantityBtnsSelect) {
			return true;
		}else {
			return false;
		}
	}

	public boolean searchResultsAndFlightDuration() {

		//li[data-test-id='offer-listing']
		waitForPresenceOfAllElementsLocatedByCss(lstFieldSet);
		//elementoPresente(By.xpath("//li[@data-test-id='offer-listing']"));

		int quantityContainers= (int) listFieldSet.stream().count();
		int quantityFlightDuration= (int) listFlightDuration.stream().count();
		Logger.printInfo("Flights containers count: " + quantityContainers);
		Logger.printInfo("Flights duration count: " + quantityFlightDuration);

		return (quantityContainers==quantityFlightDuration)?true:false;
	}

	public void getInfoDepartureFlight(int first) {

		Logger.printInfo("In method getInfoDepartureFlight");
		elementoPresente(By.cssSelector("li[data-test-id='offer-listing'] span[class='medium-bold']"));

		String  totalFlightDuration = listFlightDuration.stream().findFirst().get().getText();

		FlightsDTO flyDep = new FlightsDTO();

		flyDep.setFlightDurationTotalDeparture(totalFlightDuration);

		List<WebElement> listTimeDep = obtainsList(containerTimeDep, By.cssSelector("span[data-test-id='departure-time']"));
		String timeDeparture= listTimeDep.get(first).getText();

		flyDep.setFlightTimeInitDeparture(timeDeparture);

		List<WebElement> listTimeArrival = obtainsList(containerTimeDep, By.cssSelector("span[data-test-id='arrival-time']"));
		String timeArrival= listTimeArrival.get(first).getText();
		flyDep.setFlightTimeEndDeparture(timeArrival);

		String departDate= departureDate.getText();
		flyDep.setFlightDateDeparture(departDate);

		CONTEXT.set("FlyDep", flyDep);
	}

	public void getInfoReturnFlight(int third) {

		FlightsDTO flyRet = new FlightsDTO();

		String timeFlight= listFlightDuration.get(third).getText();

		flyRet.setFlightDurationTotalReturn(timeFlight);

		List<WebElement> listHRI = obtainsList(containerTimeDep, By.cssSelector("span[data-test-id='departure-time']"));
		String timeReturnInit= listHRI.get(third).getText();
		flyRet.setFlightTimeInitDeparture(timeReturnInit);

		List<WebElement> listHRF = obtainsList(containerTimeDep, By.cssSelector("span[data-test-id='arrival-time']"));

		String timeDepartureEnd= listHRF.get(third).getText();
		flyRet.setFlightTimeEndDeparture(timeDepartureEnd);

		String returnDate= departureDate.getText();
		flyRet.setFlightDateDeparture(returnDate);

		CONTEXT.set("FlyRet", flyRet);

	}

	public boolean searchResultsAndDetailsAndBags() {
		
		int quantityDetailsAndBags=0;
		
		Logger.printInfo("------ searchResultsAndDetailsAndBags ---------");
		waitForPresenceOfAllElementsLocatedByCss(lstFieldSet);

		int quantityContainers= (int) listFieldSet.stream().count();
		Logger.printInfo("Flights Quantity: " + quantityContainers);

		quantityDetailsAndBags= clickFielSetAndClose(listFieldSet);
		Logger.printInfo("quantityDetailsAndBags: " + quantityDetailsAndBags );

		//int quantityDetailsAndBags= (int) listDetailsBagesFees.stream().count();

		if(quantityContainers==quantityDetailsAndBags) { 
			return true;
		}
		else {
			return false;
		}

	}

	int countBaggageFees=1;
	private int clickFielSetAndClose(List<WebElement> listFieldSet) {

	
		for (int i = 0; i < listFieldSet.size()-1; i++) {

			listFieldSet.get(i).click();
			waitForPresenceOfAllElementsLocatedByCss(baggageFees);
			int countBaggageFees = countBaggageFees(baggageFees);
			
			Logger.printInfo("count Baggage Fees:  " + countBaggageFees);
			closeBaggageFees(btnCloseBaggageFees);
			
		}
		return countBaggageFees;


	}

	private void closeBaggageFees(WebElement btnBaggageClose) {
		
		waitForElementToBeClickable(btnBaggageClose);
		btnBaggageClose.click();
		
	}

	//int countBaggageFees=1;
	
	private int countBaggageFees(String baggageFees) {
		
		//int countBaggageFees=1;
		return (getDriver().findElement(By.cssSelector(baggageFees))!=null)?countBaggageFees++:countBaggageFees--;
	}

	public boolean verifyTimeIsSorted() {

		Logger.printInfo("In method SelectFromSortList");
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[data-test-id='offer-listing'] span[class='duration-emphasis']")));

		List<WebElement> listDuration = obtainsList(contenedor, By.cssSelector("span.duration-emphasis"));
		List<String> duration = listDuration.stream().map(x-> x.getText()).collect(Collectors.toList());
		List<Integer> listNumbers = getCleanList(duration);
		List<Integer> listNumbersCopy = new ArrayList<>(listNumbers);

		return  compareListsLowToHight(listNumbers, listNumbersCopy);

	}

	private List<Integer> getCleanList(List<String> duration) {

		List<Integer> listNumbers = new ArrayList<>();

		for (String hours : duration) {

			String[] vectorTime= hours.split(" ");

			vectorTime[0]= vectorTime[0].replace("h", "");
			Integer time= Integer.parseInt(vectorTime[0])*60;

			vectorTime[1]=vectorTime[1].replace("m", "");
			listNumbers.add(time+Integer.parseInt(vectorTime[1]));

		}
		return listNumbers;
	}

	public boolean verifyTicketExistGeneral(String position, boolean isLAS) {


		int pos = Integer.parseInt(position)-1;
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[data-test-id='offer-listing'] button[data-test-id='select-button']")));

		if(isLAS 
				&& (isPresent(Subtitulo)!=null) 
				&& (Subtitulo.getText().equalsIgnoreCase("Select your departure to Los Angeles"))) {

			return verifyTicketExistInList(listSelectBtns, pos);
		}

		if(!isLAS 
				&& (Subtitulo.getText().equalsIgnoreCase("Select your return to Las Vegas"))) { 

			return verifyTicketExistInList(listSelectBtns, pos);
		}
		return false;
	}

	public void getInfoFlight(String position, boolean isDeparture) {

		int pos = Integer.parseInt(position)-1;

		if(isDeparture) {
			getInfoDepartureFlight(pos);
		}
		else {
			getInfoReturnFlight(pos);	
		}


	}

	public boolean selectTicketGeneral(String position) {

		int pos = Integer.parseInt(position)-1;

		boolean isSelectedTicket = selectTicket(listSelectBtns,pos);

		if(isSelectedTicket) {
			modalInFlightSearch();
		}
		return isSelectedTicket;


	}

	public FlightInformationPage selectFaresBtnGeneral(String position, boolean isLAS) {

		int pos = Integer.parseInt(position)-1;

		if(isLAS) {
			selectFareBtn(listFareBtnsFinalLA, pos);	
		}else {
			boolean isPresentBtn= selectFareBtn(listFareBtnsFinalLA, pos);

			if(isPresentBtn) {
				return new FlightInformationPage(getDriver());	
			}else {
				return null;
			}
		}
		return null;



	} 

	public boolean selectTicketToLasVegas(String thirdResult) {

		int third = Integer.parseInt(thirdResult)-1;
		selectFareBtn(listFareBtnsFinalLA, third);
		return false;
	}

	private boolean selectFareBtn(List<WebElement>listFareBtnsFinalLA,  int position) {

		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div button.btn-secondary.btn-action.t-select-btn")));

		if(isPresent(listFareBtnsFinalLA.get(position))!=null) {

			if(listFareBtnsFinalLA.get(position).isDisplayed()) {

				try {
					listFareBtnsFinalLA.get(position).click();
					return true;
				} catch (IndexOutOfBoundsException e) {
					Logger.printInfo("The system doesn't display a third ticket");
					return false;
				}

			}else {
				return true;
			}
		}
		return true;
	}

	private boolean selectTicket(List<WebElement> listSelect, int position){

		try {
			getWait().until(ExpectedConditions.elementToBeClickable(listSelect.get(position)));
			listSelect.get(position).click();
		} catch (IndexOutOfBoundsException e) {
			Logger.printInfo("The system doesn't display a ticket");

			return false;
		}

		return true;
	}

	private boolean verifyTicketExistInList(List<WebElement> list, int position) {

		try {
			getWait().until(ExpectedConditions.elementToBeClickable(list.get(position)));
		} catch (IndexOutOfBoundsException e) {
			Logger.printInfo("The system doesn't display a third ticket");
			return false;
		}
		return true;
	}

	public boolean verifySortOptionsFlightHotel() {

		Logger.printInfo("Checking element");
		return elementoPresente(By.cssSelector("fieldset[class='sort-filter-bar control box']"));
	}

	public boolean verifyLoaderVisible() {

		isPresent(loaderFH);

		if(loaderFH.getAttribute("aria-live").equalsIgnoreCase("polite")) {
			return true;
		}

		boolean loaderVisible=false;

		while (loaderVisible!= false){
			Logger.printInfo("Attribute:" + loaderFH.getAttribute("aria-live"));
			loaderVisible= verify();
		} 

		return true;

	}

	public boolean loaderFlightSearchPage() {

		if(elementoPresente(By.cssSelector("div.progress-bar"))) {
			if(isPresent(progresBar)!=null) {

				if(	!progresBar.isDisplayed()) {
					Logger.printInfo("NO visible");
					return true;
				}
				else {
					Logger.printInfo("NO visible");
					getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='progress-bar']")));
					return true;
				}

			}
		}
		return false;
	}

	private boolean verify() {

		WebElement loader= getDriver().findElement(By.cssSelector("#uitk-live-announce"));
		boolean loaderVisible = loader.getAttribute("aria-live").equalsIgnoreCase("polite")?true:false;
		return loaderVisible;

	}

	public boolean verifyInfoFlight() {

		if(isPresent(btnOriginFlightHotel)!=null) {

			String originFly = btnOriginFlightHotel.getText();
			String destinationFly = listBtnDestination.get(1).getText().toString();

			String fromExpected= cleanCommaText(originFly, true);
			String toExpected= cleanCommaText(destinationFly, false);

			String dateDepartureExpected = listDates.get(0).getText().toString();
			String dateArrivalExpected = listDates.get(1).getText().toString();

			FlightsDTO flightInfo = new FlightsDTO();

			flightInfo = CONTEXT.get("flyDepFH");
			String dateDepartureFilled = flightInfo.getFlightDateDeparture();
			String dateArrivalFilled = flightInfo.getFlightDateReturn();

			FlightsDTO fly = CONTEXT.get("fly");
			String flightFrom = fly.getFlightFrom();

			flightFrom= cleanCommaText(flightFrom, true);

			String flightTo = fly.getFlightTo();
			flightTo= cleanCommaText(flightTo, true);

			boolean isSameFrom = flightFrom.equalsIgnoreCase(fromExpected)?true:false;
			boolean isSameTo = flightTo.equalsIgnoreCase(toExpected)?true:false;

			boolean isSameDateDeparture= dateDepartureFilled.equalsIgnoreCase(dateDepartureExpected);
			boolean isSameDateArrival= dateArrivalFilled.equalsIgnoreCase(dateArrivalExpected);

			boolean isSameInfo= !isSameFrom && !isSameTo && !isSameDateDeparture && !isSameDateArrival?false:true;

			Logger.printInfo("is the same ? " +isSameInfo);

			return isSameInfo;
		}
		return false;
	}

	private String cleanCommaText(String originFly, boolean isOptionComma) {

		if(isOptionComma) {
			String[] vectorFrom = originFly.split(",");
			String fromExpected= vectorFrom[0].replace(" ", "");
			return fromExpected;
		}
		else {
			String[] vectorTo = originFly.split("\\(");
			String toExpected= vectorTo[0].replace(" ", "");
			return toExpected;

		}
	}

	public boolean verifyTitleFlightHotel() {

		String subTitle= getText(subTitleFlightHotel).trim();
		boolean isSameTitle= subTitleFlightHotel.getText().equalsIgnoreCase(subTitle)?true:false;
		return isSameTitle;
	}

	public boolean verifyResultsFlightHotel() {

		boolean isPresentResults= listResultsFH.size()>0?true:false;
		return isPresentResults;
	}

	public boolean verifyResultSorteByPrice() {

		btnPriceFlightHotel.click();
		verifyLoaderVisible();
		getDriver().navigate().refresh();
		verifyLoaderVisible();

		getWait().until(ExpectedConditions.textMatches(By.cssSelector("ul[class='hotel-price'] li[class^='actualPrice']"), Pattern.compile("[$?]+([0-9])")));

		List<String> sbListPrices = listPricesFlightHotel.stream().map(x-> x.getText().replace("$", "").replace(",", "")).collect(Collectors.toList());
		List<Integer> listPricesInt = sbListPrices.stream().map(x-> Integer.parseInt(x)).collect(Collectors.toList());
		List<Integer> listPricesIntCopy =  sbListPrices.stream().map(x-> Integer.parseInt(x)).collect(Collectors.toList());

		return compareListsLowToHight(listPricesInt, listPricesIntCopy);

	}

	public int obtainPositionOf3StarsHotel() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("#resultsContainer"), By.cssSelector("strong[class='star-rating rating-secondary star rating'] span[class^='icon icon-stars']")));

		int ipos=0;
		for (WebElement i : listStars) {
			Logger.printInfo("attribute: " + i.getAttribute("title"));

			if( (i.getAttribute("title").equals("3.0"))
					|(i.getAttribute("title").equals("3.5"))
					|(i.getAttribute("title").equals("4.0"))
					|(i.getAttribute("title").equals("4.5"))) {
				ipos= listStars.indexOf(i);
				break;
			}
		}

		return ipos;	


	}

	public FlightInformationPage selectFirstResultWith3Starts(int position) {

		getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.flex-link")));

		try {
			listResultsFH.get(position).findElement(By.cssSelector("a.flex-link")).click();

			waitFlightInformationPageGenral(imagenEspera);

			return new FlightInformationPage(getDriver());

		} catch (IndexOutOfBoundsException e) {
			Logger.printInfo("The system doesn't display the Hotel");
			return null;
		}
	}

	public void obtain3StartsHotelInfo(int position) {

		WebElement hotelSelected= listResultsFH.get(position);
		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(hotelSelected, By.cssSelector("span[class^='icon icon-stars']")));

		String hotelName= hotelSelected.findElement(By.cssSelector("h4[data-automation='hotel-name']")).getText();
		String value= hotelSelected.findElement(By.cssSelector("li[class^='actualPrice price']")).getText();
		String stars= hotelSelected.findElement(By.cssSelector("strong[class='star-rating rating-secondary star rating'] span[class^='icon icon-stars']")).getAttribute("title"); 

		HotelDTO hotel = new HotelDTO();
		hotel = CONTEXT.get("hotel");

		hotel.setName(hotelName);
		hotel.setValue(value);
		hotel.setStars(stars);

		CONTEXT.set("hotel", hotel);
	}

	public boolean findSponsoredSectionHotels() {

		if(!elementoPresente(By.cssSelector("div[class='uitk-card uitk-grid messaging-card all-x-padding-three all-y-padding-three']"))) {
			Logger.printInfo("The section is not available");
			return false;
		}

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(listLinksHotels.get(0), By.cssSelector("a")));

		if(isPresent(listLinksHotels.get(0))!=null){
			return true;
		} else {
			return false;	
		}

	}

	public boolean verifyFilterRadioButton() {

		getWait().until(ExpectedConditions.elementToBeClickable(radioBtnCruisesNights.get(1)));
		Logger.printInfo("Verify filter");

		if(isPresent(radioBtnCruisesNights.get(1))!=null) {
			return true;
		} else { 
			return false;	
		}

	}

	public boolean verifyCruisesWithDiscountsAndWithOut() {

		boolean isSameDiscount;
		int quantityCruisesWithOutDiscount = listContainerCruises.size();
		int quantityCruisesWithDiscount= getDriver().findElements(By.cssSelector("div.flex-card div[class^='message-']")).size();

		return isSameDiscount= quantityCruisesWithDiscount>0 && quantityCruisesWithOutDiscount>0? true:false;
	}

	public FlightInformationPage selectCruiseWithMoreDiscount() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div.flex-card"), By.cssSelector("div[class='flex-content']")));
		getDiscountFromList(listDiscounts);

		return new FlightInformationPage(getDriver());
	}

	private void getDiscountFromList(List<WebElement> listDiscounts) {

		Integer maxValue=0;
		WebElement elementMax = null;

		for (WebElement cruise : listContainerCruises) {

			if(childElementIsPresent(cruise, By.cssSelector(".message-flag.flex-flag"))) {

				Integer value = Integer.parseInt(cruise.findElement(By.cssSelector("div[class='message-flag flex-flag']")).getText().replaceAll("[A-Z\\D\\s]", ""));

				if(value > maxValue) {

					maxValue = value;
					elementMax= cruise;
				}

			}
		}         

		obtainCruiseInfo(elementMax);
		elementMax.findElement(By.cssSelector("a[id^='selectSailingButton']")).click();

	}

	private void obtainCruiseInfo(WebElement cruiseSelected) {

		String cruiseName= cruiseSelected.findElement(By.cssSelector("div.title-on-ship-image")).getText();
		String cruiseValue= cruiseSelected.findElement(By.cssSelector("span.card-price")).getText();
		String cruiseFrom= cruiseSelected.findElement(By.cssSelector("div.card-content-detail.departure-city")).getText(); 
		String cruiseDateDeparture= cruiseSelected.findElement(By.cssSelector("div.card-content-detail.sailing-dates")).getText();


		CruiseDTO cruise= new CruiseDTO();

		cruise.setName(cruiseName);
		cruise.setValue(cruiseValue);
		cruise.setFrom(cruiseFrom);
		cruise.setDateDeparture(cruiseDateDeparture);

		CONTEXT.set("cruise", cruise);
	}

	public boolean verifyFlightFromToVsSelected(String from, String To) {

		String texto=listFlightsVS.get(0).getText();
		if(texto.contains(from) && texto.contains(To)){
			return true;
		} else {
			return false;
		}
	}

	public SignInPage clickOnSponsoredSectionHotel() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div[class='uitk-card uitk-grid messaging-card all-x-padding-three all-y-padding-three']"), By.cssSelector("a")));

		if(isPresent(listLinksHotels.get(0))!=null){

			listLinksHotels.stream().filter(x-> x.getText().equalsIgnoreCase("Sign in")).findFirst().get().click();
			return new SignInPage(getDriver());
		} else {
			return null;	
		}
	}

	public void waitCruisesPage() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div.flex-card"), By.cssSelector("div[class='flex-content']")));
	}

	public void waitForPageLoaded() {

		System.out.println("ya llegue");


	}

	public boolean selectDropDownSort(String data) {

		return clickDropdownByValue(sortBy, data);

	}



}

