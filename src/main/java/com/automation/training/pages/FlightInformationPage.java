package com.automation.training.pages;

import static com.automation.training.utils.TestContext.CONTEXT;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.dto.CruceroDTO;
import com.automation.dto.FlightsDTO;
import com.automation.dto.HotelDTO;
import com.automation.training.utils.Logger;

public class FlightInformationPage extends BasePage{

	@FindBy(css="h1[class='section-header-main']")
	private WebElement Subtitulo;

	@FindBy(css="div[class='totalContainer']")
	private List<WebElement> listTripTotalPrice;

	@FindBy(xpath="//div[@class='flightSummary-expandedDetails bagFeesRequested']")
	private List<WebElement> listDepartureReturnInfo;

	@FindBy(xpath="//button[@class='btn-text toggle-trigger']")
	private List<WebElement> listBtnsShowFlyAndBaggageFee;

	@FindBy(xpath="//div[@id='flight-leg-0']")
	private WebElement contenidoDeparture;

	@FindBy(xpath="//div[@id='flight-leg-1']")
	private WebElement contenidoReturn;

	@FindBy(xpath="//button[@class='btn-text toggle-trigger']")
	private WebElement btnShowFly2; 

	@FindBy(css="#bookButton")
	private WebElement btnBooking;

	@FindBy(css="div[class='departureDate type-500']")
	private List<WebElement> listaFechas;

	@FindBy(css="div[class='departure'] span")
	private List<WebElement> listaHorasIni;

	@FindBy(css="div[class='arrival'] span")
	private List<WebElement> listadoHorasFin;

	@FindBy(css="div[class='departure'] div")
	private List<WebElement> ListOrigenesVuelos;

	@FindBy(css="div[class='arrival'] div")
	private List<WebElement> ListDestinoVuelos;

	@FindBy(css="span[class='durationTime type-500']")
	private List<WebElement> ListTotalFlightTime;

	@FindBy(css="div[class='priceGuarantee']")
	private List<WebElement> priceGuarantee;

	@FindBy(css="#license-plate #hotel-name")
	private WebElement nameHotelExpected;

	@FindBy(css="section[class='segmented-list cf']")
	private WebElement containerRooms;

	@FindBy(css="section[class='segmented-list cf'] article[class='segment no-target room cf room-above-fold branded-deal']")
	private WebElement firstRoomUnRealDeal;

	//--FH

	@FindBy(id="trip-flight-start")
	private WebElement fechaSalidaVuelo_FH;

	@FindBy(id="trip-flight-end")
	private WebElement fechaLlegadaVuelo_FH;

	@FindBy(id="hotelCheckinDateFullFormat")
	private WebElement fechaCheckInHotel_FH;

	@FindBy(id="hotelCheckoutDateFullFormat")
	private WebElement fechaCheckOutHotel_FH;

	@FindBy(id="trip-flight-to")
	private WebElement flightFrom_FH;

	@FindBy(id="trip-flight-from")
	private WebElement flightTo_FH; 

	@FindBy(id="trip-summary-hotel-title")
	private WebElement hotelName_FH;

	@FindBy(id="departure-time-automation-label-0")
	private WebElement horaSalidaVueloFrom_FH;

	@FindBy(id="arrival-time-automation-label-0")
	private WebElement horaLlegadaVueloFrom_FH;

	@FindBy(id="duration-automation-label-0")
	private WebElement duracionTotalVueloFrom_FH;

	@FindBy(id="departure-time-automation-label-1")
	private WebElement horaSalidaVueloTo_FH;

	@FindBy(id="arrival-time-automation-label-1")
	private WebElement horaLlegadaVueloTo_FH;

	@FindBy(id="duration-automation-label-1")
	private WebElement duracionTotalVueloTo_FH;
	
	@FindBy(css="#totalPriceSubTitle Strong")
	private WebElement valorTotalViajeVuelo_FH;
	
	@FindBy(css="#FlightUDPBookNowButton1 button")
	private WebElement btnNextFinalDetails;





	public FlightInformationPage(WebDriver driver) {
		super(driver);
	}

	public boolean verificarPage() {

		if(Subtitulo.getText().equalsIgnoreCase("Review your trip")){
			return true;
		}
		else return false;
	}


	public boolean verifyTripTotalPrice(){
		elementoPresente(By.cssSelector("div[class='totalContainer']"));
		List<WebElement> list= obtainsList(listTripTotalPrice, "listTripTotalPrice");

		String valor = list.get(0).getText();
		String valor2= list.get(1).getText();


		if(list.get(1).getText().equalsIgnoreCase(""))	{

			String algo = list.get(1).getText();
			System.out.println(algo);
			return false;


		}
		else {

			FlightsDTO FlyRet = CONTEXT.get("FlyRet");

			FlyRet.setFlightTotalPrice(list.get(1).getText());
			CONTEXT.set("FlyRet", FlyRet);

			return true;

		}
	}

	//metodo que compara la informacion ingresada con la informacion de la sgt pagina.
	public boolean verifyDepartureReturnInfo(){

		elementoPresente(By.cssSelector("button[class='btn-text toggle-trigger']"));

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");


		Boolean bandera1, bandera2, bandera3, bandera4, bandera5, bandera6, bandera7, bandera8, bandera9, bandera10, bandera11, bandera12, banderaGeneral;

		String fecha= listaFechas.get(0).getText();

		if(listaFechas.get(0).getText().equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("Departure date is equals with the users choice");
			bandera1= true;
		}
		else bandera1=false;
		String listaFec= listaFechas.get(1).getText();

		if(listaFechas.get(1).getText().equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())){
			Logger.printInfo("Return date is equals with the users choice");
			bandera2=true;
		}
		else bandera2=false;

		if(listaHorasIni.get(0).getText().equalsIgnoreCase(flightExpectedDep.getFlightTimeInitDeparture())) {
			Logger.printInfo("Departure time beging is the same");
			bandera3=true;
		}
		else bandera3=false;

		if(listaHorasIni.get(2).getText().equalsIgnoreCase(flightExpectedRet.getFlightTimeInitDeparture())) {
			Logger.printInfo("Return time flight is the same with the user choise");
			bandera4=true;
		}
		else bandera4=false;

		if(ListOrigenesVuelos.get(0).getText().equalsIgnoreCase("LAS")){
			Logger.printInfo("Place to beging DepartureThe choise is the same with the user fill at the begining of the process");
			bandera5=true;
		}
		else bandera5=false;

		if(ListOrigenesVuelos.get(1).getText().equalsIgnoreCase("LAX")){
			Logger.printInfo("place to begind return The choise is the same with the user fill at the begining of the process");
			bandera6=true;
		}
		else bandera6=false;

		if(ListDestinoVuelos.get(0).getText().equalsIgnoreCase("LAX")) {
			Logger.printInfo("Place to End Flight from Departure The choise is the same with the user fill at the begining of the process");
			bandera7=true;
		}
		else bandera7=false;

		if(ListDestinoVuelos.get(1).getText().equalsIgnoreCase("LAS")) {
			Logger.printInfo("Place to end flight from Return The choise is the same with the user fill at the begining of the process");
			bandera8=true;
		}
		else bandera8=false;

		if(listadoHorasFin.get(0).getText().equalsIgnoreCase(flightExpectedDep.getFlightTimeEndDeparture())) {
			Logger.printInfo("The departure time is the same");
			bandera9=true;
		}
		else bandera9=false;

		if(listadoHorasFin.get(2).getText().equalsIgnoreCase(flightExpectedRet.getFlightTimeEndDeparture())) {
			Logger.printInfo("the return time is the same");
			bandera10=true;
		}
		else bandera10=false;

		if(ListTotalFlightTime.get(0).getText().equalsIgnoreCase(flightExpectedDep.getFlightDurationTotalDeparture())) {
			Logger.printInfo("Total time flight departure is the same");
			bandera11=true;
		}
		else bandera11=false;

		if(ListTotalFlightTime.get(2).getText().equalsIgnoreCase(flightExpectedRet.getFlightDurationTotalReturn())) {
			Logger.printInfo("Total time flight return is the same");
			bandera12=true;
		}
		else bandera12=false;

		if(!bandera1 && !bandera2 && !bandera3 && !bandera4 && !bandera5 && bandera6 && !bandera7 && !bandera8 && !bandera9 && !bandera10 && !bandera11 && !bandera12) {
			banderaGeneral=false;	
		}
		else banderaGeneral=true;

		return banderaGeneral;
	}


	//*************************
	
	public void waitFlightInformation() {
		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("#v2ProductSummary"), By.cssSelector("div")));
		
		
	}
	public boolean verifyDateDepartureReturnFlight_FH(){

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		Boolean bandera1=false, bandera2=false , banderaGeneral=false;

		String f1= fechaSalidaVuelo_FH.getText();
		String ff1= flightExpectedDep.getFlightDateDeparture();

		if(fechaSalidaVuelo_FH.getText().equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("Departure date is equals with the users choice");
			bandera1= true;
		}
		else { 
			Logger.printInfo("Departure date is not equals with the users choice");
			bandera1=false;
		}

		String f2 = fechaLlegadaVuelo_FH.getText();
		String ff2= flightExpectedRet.getFlightDateDeparture();
		if(fechaLlegadaVuelo_FH.getText().equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())){
			Logger.printInfo("Return date is equals with the users choice");
			bandera2=true;
		}
		else {
			Logger.printInfo("Return date is not equals with the users choice");
			bandera2=false;
		}

		if( !bandera1 && !bandera2) {
			banderaGeneral=false;	
		}
		else banderaGeneral=true;

		return banderaGeneral;

	}

	public boolean verifyTimeDepartureReturnFlightFrom_FH(){

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");


		Boolean bandera3=false,  bandera9=false, banderaGeneral=false;

		String f3 = horaSalidaVueloFrom_FH.getText();
		String ff3= flightExpectedDep.getFlightTimeInitDeparture();
		if(horaSalidaVueloFrom_FH.getText().equalsIgnoreCase(flightExpectedDep.getFlightTimeInitDeparture())) {
			Logger.printInfo("Departure time beging is the same");
			bandera3=true;
		}
		else {
			Logger.printInfo("Departure time beging is Not the same");
			bandera3=false;
		}

		String h = horaLlegadaVueloFrom_FH.getText();
		String hh= flightExpectedDep.getFlightTimeEndDeparture();
		if(horaLlegadaVueloFrom_FH.getText().equalsIgnoreCase(flightExpectedDep.getFlightTimeEndDeparture())) {
			Logger.printInfo("The departure time is the same");
			bandera9=true;
		}
		else {
			bandera9=false;
			Logger.printInfo("The departure time is not the same");
		}


		if( !bandera3 && !bandera9) {
			banderaGeneral=false;	
		}
		else banderaGeneral=true;

		return banderaGeneral;

	}

	public boolean verifyTimeDepRetFlightTo_FH(){

		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		Boolean bandera4=false,  bandera10=false, banderaGeneral=false;

		String f4=horaSalidaVueloTo_FH.getText();
		String ff4= flightExpectedRet.getFlightTimeInitDeparture();

		if(horaSalidaVueloTo_FH.getText().equalsIgnoreCase(flightExpectedRet.getFlightTimeInitDeparture())) {
			Logger.printInfo("Return time init flight is the same with the user choise");
			bandera4=true;
		}
		else {
			Logger.printInfo("Return time init flight is Not the same with the user choise");
			bandera4=false;
		}

		String h2= horaLlegadaVueloTo_FH.getText();
		String h22= flightExpectedRet.getFlightTimeEndDeparture();

		if(horaLlegadaVueloTo_FH.getText().equalsIgnoreCase(flightExpectedRet.getFlightTimeEndDeparture())) {
			Logger.printInfo("the return end time is the same");
			bandera10=true;
		}
		else {
			Logger.printInfo("the return end time is Not the same");
			bandera10=false;
		}

		if(!bandera4 && !bandera10) {
			banderaGeneral=false;	
		}
		else {
			banderaGeneral=true;
		}

		return banderaGeneral;
	}

	public boolean verifyFlightFromTo_FH(){

		FlightsDTO fly = CONTEXT.get("fly");

		Boolean bandera5=false,  bandera6=false, banderaGeneral=false;

		String flyFromCntx= fly.getFlightFrom().substring(0,9);
		String flightFrom = flightFrom_FH.getText().substring(0,9);

		if(flightFrom_FH.getText().substring(0,9).equalsIgnoreCase(fly.getFlightFrom().substring(0,9))){
			Logger.printInfo("Place to beging DepartureThe choise is the same with the user fill at the begining of the process");
			bandera5=true;
		}
		else bandera5=false;

		//-
		String flyfrom= fly.getFlightTo().substring(0,9);
		String flightfromm = flightTo_FH.getText().substring(0,9);

		if(flightTo_FH.getText().substring(0,9).equalsIgnoreCase(fly.getFlightTo().substring(0,9))){
			Logger.printInfo("place to begind return The choise is the same with the user fill at the begining of the process");
			bandera6=true;
		}
		else bandera6=false;

		if( !bandera5 && !bandera6) {
			banderaGeneral=false;	
		}
		else banderaGeneral=true;

		return banderaGeneral;

	}

	public boolean verifyHotelName_FH(){
		
		FlightsDTO fly = CONTEXT.get("fly");
		HotelDTO hotel = CONTEXT.get("hotel");

		Boolean bandera7=false;

		String f5= hotelName_FH.getText();
		String ff5= hotel.getName();
		if(hotelName_FH.getText().equalsIgnoreCase(hotel.getName())) {
			Logger.printInfo("The departure time is the same");
			return bandera7=true;
		}
		else {
			Logger.printInfo("The departure time is not the same");
			return bandera7=false;
		}
	}
	
	public boolean verifyHotelCheckInDate_FH() {
		
		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		Boolean  bandera8, bandera13, banderaGeneral;

		String f6= fechaCheckInHotel_FH.getText();
		String f7= flightExpectedDep.getFlightDateDeparture();
		if(fechaCheckInHotel_FH.getText().equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("The departure time is the same");
			bandera8=true;
		}
		else {
			Logger.printInfo("The departure time is not the same");
			bandera8=false;
		}

		String f8= fechaCheckOutHotel_FH.getText();
		String ff8=flightExpectedRet.getFlightDateDeparture();
		if(fechaCheckOutHotel_FH.getText().equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())) {
			Logger.printInfo("The departure time is the same");
			bandera13=true;
		}
		else bandera13=false;

		if( !bandera8 && !bandera13) {
			banderaGeneral=false;	
		}
		else banderaGeneral=true;

		return banderaGeneral;

	}
	
	public boolean verifyTotalFlightDurationFROM_FH() {
		
		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");

		Boolean bandera11=false;
		
		String d= duracionTotalVueloFrom_FH.getText();
		String dd = flightExpectedDep.getFlightDurationTotalDeparture();			

		if(duracionTotalVueloFrom_FH.getText().equalsIgnoreCase(flightExpectedDep.getFlightDurationTotalDeparture())) {
			Logger.printInfo("Total time flight departure is the same");
			return bandera11=true;
		}
		else {
			Logger.printInfo("Total time flight departure is not the same");
			return bandera11=false;
		}
	}
	
	public boolean verifyTotalDurationFlightTO_FH() {
		
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		Boolean bandera12=false;

		String d2= duracionTotalVueloTo_FH.getText();
		String dd2= flightExpectedRet.getFlightDurationTotalReturn();

		if(duracionTotalVueloTo_FH.getText().equalsIgnoreCase(flightExpectedRet.getFlightDurationTotalReturn())) {
			Logger.printInfo("Total time flight return is the same");
			return bandera12=true;
		}
		else {
			Logger.printInfo("Total time flight return is not the same");
			return bandera12=false;
		}
		
	}
	
	public void obtainTotalTripValue_FH() {
		
		HotelDTO hotel = new HotelDTO();
		hotel= CONTEXT.get("hotel");
		hotel.setValue(valorTotalViajeVuelo_FH.getText());
		CONTEXT.set("hotel", hotel);
		
	}
	
	public void clickBtnNextFinalDetails() {
		 if(isPresent(btnNextFinalDetails)!=null) btnNextFinalDetails.click();
		
	}
	//*************************	

	public void selectBtnContinueBooking() {

		if(isPresent(btnBooking)!=null) {

			btnBooking.click();
		}
	}

	public boolean verifyPriceGuaranteeText() {


		WebElement e = getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(priceGuarantee.get(1), By.cssSelector("div[class='priceGuarantee'] span span")));

		if(e.getClass()!= null) {
			System.out.println("si esta el elemento");
			return true;

		}
		else return false;
	}

	public boolean verifyHotelSelected() {

		
		elementoPresente(By.cssSelector(".page-header"));
		elementoPresente(By.cssSelector("#license-plate"));
		elementoPresente(By.cssSelector(".lead-price"));

		WebElement parcial= getDriver().findElement(By.cssSelector("div[class='lead-price']"));
		String valueHotelExpected= parcial.findElement(By.partialLinkText("$")).getText();
		String nameHotelExpected= getDriver().findElement(By.cssSelector("#license-plate #hotel-name")).getText();
		String starsHotelExpected= getDriver().findElement(By.cssSelector("#license-plate span[class^='icon icon-stars']")).getAttribute("title");

		HotelDTO hotel = new HotelDTO();
		hotel= CONTEXT.get("hotel");

		boolean r1= hotel.getName().equals(nameHotelExpected)?true:false;
		boolean r2= hotel.getStars().equals(starsHotelExpected)?true:false;
		boolean r3= hotel.getValue().equals(valueHotelExpected)?true:false;


		if(r1&&r2&&r3)return true;
		else return false;
	}

	public boolean verifyCruiseSelected() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("#cabin-category-north-view"), By.cssSelector("aside[class='col sailing-details']")));
		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div.trip-summary"), By.cssSelector("div.ember-view")));
		//---
		String valueCruiseExpected= getDriver().findElements(By.cssSelector("span.updated-price")).get(0).getText();
		String nameCruiseExpected= getDriver().findElement(By.cssSelector("div[class='small-title trip-title']")).getText();
		String departureCruiseExpected= getDriver().findElement(By.cssSelector("div[class='departure-port']")).getText();
		String departureDateCruiseExpected= getDriver().findElement(By.cssSelector("div[class='departure-date']")).getText();

		CruceroDTO crucero = new CruceroDTO();
		crucero= CONTEXT.get("crucero");
		
		boolean r4;
		if(departureDateCruiseExpected.contains(crucero.getDateDeparture())) r4=true; 
		else r4=false;

		boolean r1= crucero.getName().equalsIgnoreCase(nameCruiseExpected)?true:false;
		boolean r2= crucero.getFrom().equals(departureCruiseExpected)?true:false;
		boolean r3= crucero.getValue().equals(valueCruiseExpected)?true:false;

		if(r1&&r2&&r3&&r4)return true;
		else return false;
	}

	public boolean selectTheFirstRoomOption() {
		Logger.printInfo("Seleccionando el pimer Room");

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(containerRooms, By.cssSelector("article[class='segment no-target room cf room-above-fold']")));
		
		List<WebElement> listaRooms = getDriver().findElements(By.cssSelector("section[class='segmented-list cf'] article[class^='segment no-target'] a[data-control='modal']"));
		listaRooms.stream().findFirst().get().click();
		
		getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#covid-alert-refundability")));
		
		WebElement modal = getDriver().findElement(By.id("covid-alert-refundability"));
		if(isPresent(modal)!=null && modal.isDisplayed()) {
			
			getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.modal-body a[class='btn btn-secondary btn-sub-action book-button modal-button'][role='button']")));
			
			getDriver().findElement(By.cssSelector("div.modal-body a[class='btn btn-secondary btn-sub-action book-button modal-button'][role='button']")).click();
		}
		
		return true;
	}



}
