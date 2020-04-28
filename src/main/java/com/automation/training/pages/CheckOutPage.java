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
import org.openqa.selenium.support.ui.Select;

import com.automation.dto.FlightsDTO;
import com.automation.dto.HotelDTO;
import com.automation.training.utils.Logger;

public class CheckOutPage extends BasePage {

	@FindBy(xpath="h2[class='faceoff-module-title']")
	private WebElement titulo;

	@FindBy(xpath="//input[@id='firstname[0]']")
	private WebElement inputFirstName;

	@FindBy(xpath="//p[@class='uitk-validation-error']")
	private List<WebElement> listErrors;

	@FindBy(css="input[name$='middleName']")
	private WebElement middleName;

	@FindBy(css="label[for='middlename[0]'] p")
	private WebElement middleNameError;

	@FindBy(xpath="//input[@id='lastname[0]']")
	private WebElement lastName;

	@FindBy(css="label[for='lastname[0]'] p")
	private WebElement LastNameError;

	@FindBy(xpath="//input[@id='phone-number[0]']")
	private WebElement phoneNumber;
	
	

	@FindBy(css="label[for='phone-number[0]'] p")
	private WebElement phoneNumberError;

	@FindBy(id="totalPriceForTrip")
	private WebElement totalPriceTrip;

	@FindBy(css="h2[class='faceoff-module-title']")
	private WebElement subtitle;

	//-checkOut
	@FindBy(css="div.product-content a[role='button']")
	private List<WebElement> listaNombres;

	@FindBy(css="div.product-content div[class='date-info']")
	private List<WebElement> listaFechas_FH;

	@FindBy(css="div.product-content div[class='location-info']")
	private List<WebElement> listaFromTOFlight_FH;

	@FindBy(css="span[data-price-update='tripTotal']")
	private WebElement precioFinal_FH;

	@FindBy(css="h2[class='title-main']")
	private List<WebElement> listSubtitles;

	@FindBy(css="div[class='product-description']")
	private List<WebElement> listTicketsRooms;


	public CheckOutPage(WebDriver driver) {
		super(driver);
	}

	// puede ser validacion de la informacion del vuelo q ya tengo los datos en contexto.

	public boolean buscarSeccionVueloHotelInfo() {
		
		boolean r1= getDriver().findElement(By.cssSelector("aside#secondary-content")) == null?true:false;
		return r1;
	}
	public boolean verifyTitle() {
		Logger.printInfo("verificando el titulo");

		String titu= getPageTitle();
		System.out.println(titu);

		if(getPageTitle().equalsIgnoreCase("Travelocity: Payment"))return true;
		else return false;
	}

	public boolean verifySubTitle() {
		Logger.printInfo("verificando el subtitulo");
		//		modalHandle();

		boolean isPresent=elementoPresente(By.cssSelector("h2[class='faceoff-module-title']"));

		if(isPresent) {
			String cc= getSubTitle(subtitle);
			boolean result= (getSubTitle(subtitle).equalsIgnoreCase("Who's traveling?"))? true: false;
			return result;
		}
		else return false;
	}

	public boolean verifyFirstName() {
		Logger.printInfo("verificando el nombre");
		
		inputFirstName.sendKeys("wilson");
		boolean ispresent= (getDriver().findElements(By.cssSelector("p[class='uitk-validation-error']")).size()>0);// si encuentra elemento retorna true de lo contrario retorna false

		System.out.println("var: "+ispresent);// si retorna false no encontro el error, esta bien

		boolean fin = !ispresent?true:false; //siNO encontro el error entonces retorna true aqui
		System.out.println(fin);
		return fin;
	}

	public boolean verifyMiddleName() {
		middleName.sendKeys("Angel");

		boolean isPresent= elementoPresente(By.cssSelector("input[id='middlename[0]']+p , input[data-cko-rfrr-id='MCKO.CKO.MIDDLENAME']+p[class='uitk-validation-error']"))?false:true; // si encuentra el mensaje de error retorno false
		System.out.println(isPresent);// si retorna false aqui es encontro el mensaje de error
		return isPresent;
	}

	public boolean verifyLastName() {
		lastName.sendKeys("lopez");

		boolean isPresent= elementoPresente(By.cssSelector("input[id='lastname[0]']+p , input[data-cko-rfrr-id='MCKO.CKO.TRAVELER1LASTNAME']+p[class='uitk-validation-error']"))?false:true; // si encuentra el mensaje de error retorno false
		System.out.println(isPresent);// si retorna false aqui es encontro el mensaje de error
		return isPresent;
	}

	public boolean verifyPhoneNumber() {
		
		WebElement phoneNumber = getDriver().findElement(By.cssSelector("input[id='phone-number[0]'] , input[data-cko-rfrr-id='MCKO.CKO.Phone.NumberEntered']"));
		phoneNumber.sendKeys("2345245344");
	
		boolean isPresent= elementoPresente(By.cssSelector("label[for='phone-number[0]'] p , input[data-cko-rfrr-id='MCKO.CKO.Phone.NumberEntered']+p[class='uitk-validation-error'] "))?false:true; // si encuentra el mensaje de error retorno false
		System.out.println(isPresent);// si retorna false aqui es encontro el mensaje de error
		return isPresent;
	}

	public boolean verifyTotalPriceTrip() {

		FlightsDTO FlyRet = CONTEXT.get("FlyRet");
		boolean selecccionado = FlyRet.getFlightTotalPrice().equalsIgnoreCase(totalPriceTrip.getText())?true:false;

		return selecccionado;


	}

	public void waitCheckOutPage() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div[class='site-content cols-row cf']"), By.cssSelector("#secondary-content")));
	}

	public boolean verifyHotelName_FH(){

		FlightsDTO fly = CONTEXT.get("fly");
		HotelDTO hotel = CONTEXT.get("hotel");

		List<String>nombres= listaNombres.stream().map(x-> x.getText()).collect(Collectors.toList());
		String nom1= nombres.get(1);
		boolean r1= hotel.getName().equalsIgnoreCase(nombres.get(1))?true:false;

		return r1;
	}

	public boolean verifyDatesHotel_FH() {

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		Boolean bandera1=false, bandera2=false , banderaGeneral=false;


		List<String[]> fechas= listaFechas_FH.stream().map(x-> x.getText().split("-")).collect(Collectors.toList());
		List<String> listadofechas = new ArrayList<>();
		for (String[] f : fechas) {

			String f1= f[0].trim();
			System.out.println(f1);
			String f2= f[1].trim();
			System.out.println(f2);

			listadofechas.add(f1);
			listadofechas.add(f2);
			break;

		}

		String ff1= flightExpectedDep.getFlightDateDeparture();

		String f= listadofechas.get(0);

		if(listadofechas.get(0).equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("Checkin date is equals with the users choice");
			bandera1= true;
		}
		else { 
			Logger.printInfo("Checkin date is not equals with the users choice");
			bandera1=false;
		}

		String ff2= flightExpectedRet.getFlightDateDeparture();
		if(listadofechas.get(1).equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())){
			Logger.printInfo("Checkin date is equals with the users choice");
			bandera2=true;
		}
		else {
			Logger.printInfo("Checkin date is not equals with the users choice");
			bandera2=false;
		}

		if( !bandera1 && !bandera2) {
			banderaGeneral=false;	
		}
		else banderaGeneral=true;

		return banderaGeneral;


		//				return true;
	}

	public boolean verifyDatesFlights_FH() {

		FlightsDTO flightExpectedDep = CONTEXT.get("FlyDep");
		FlightsDTO flightExpectedRet = CONTEXT.get("FlyRet");

		Boolean bandera1=false, bandera2=false , banderaGeneral=false;


		List<String[]> fechas= listaFechas_FH.stream().map(x-> x.getText().split("-")).collect(Collectors.toList());
		List<String> listadofechas = new ArrayList<>();
		for (String[] f : fechas) {

			String f1= f[0].trim();
			String f2= f[1].trim();

			listadofechas.add(f1);
			listadofechas.add(f2);
			break;

		}

		String ff1= flightExpectedDep.getFlightDateDeparture();

		String f= listadofechas.get(0);

		if(listadofechas.get(0).equalsIgnoreCase(flightExpectedDep.getFlightDateDeparture())) {
			Logger.printInfo("Checkin date is equals with the users choice");
			bandera1= true;
		}
		else { 
			Logger.printInfo("Checkin date is not equals with the users choice");
			bandera1=false;
		}

		String ff2= flightExpectedRet.getFlightDateDeparture();
		if(listadofechas.get(1).equalsIgnoreCase(flightExpectedRet.getFlightDateDeparture())){
			Logger.printInfo("Checkin date is equals with the users choice");
			bandera2=true;
		}
		else {
			Logger.printInfo("Checkin date is not equals with the users choice");
			bandera2=false;
		}

		if( !bandera1 && !bandera2) {
			banderaGeneral=false;	
		}
		else banderaGeneral=true;

		return banderaGeneral;

	}

	public boolean verifyFromToFlight_FH() {


		List<String[]> fechas= listaFromTOFlight_FH.stream().map(x-> x.getText().split(" to ")).collect(Collectors.toList());
		List<String> listadofechas = new ArrayList<>();
		for (String[] f : fechas) {

			String f1= f[0].trim().replace(" (LAS)", "");
			System.out.println(f1);
			String f2= f[1].trim().replace(" (LAX)", "");
			System.out.println(f2);

			listadofechas.add(f1);
			listadofechas.add(f2);
			break;

		}


		FlightsDTO fly = CONTEXT.get("fly");

		Boolean bandera5=false,  bandera6=false, banderaGeneral=false;

		String flyFromCntx= fly.getFlightFrom().substring(0,9);
		if(listadofechas.get(0).equalsIgnoreCase(fly.getFlightFrom().substring(0,9))){
			Logger.printInfo("Place to beging DepartureThe choise is the same with the user fill at the begining of the process");
			bandera5=true;
		}
		else bandera5=false;
		//-

		String flyfrom= fly.getFlightTo().substring(0,11);
		String a= listadofechas.get(1);

		if(listadofechas.get(1).equalsIgnoreCase(fly.getFlightTo().substring(0,11))){
			Logger.printInfo("place to begind return The choise is the same with the user fill at the begining of the process");
			bandera6=true;
		}
		else bandera6=false;

		boolean r1= !bandera5 && !bandera6?false:true;

		return r1;

	}

	public boolean verifyPrecioFinal_FH() {

		HotelDTO hotel = CONTEXT.get("hotel");
		boolean r1= hotel.getValue().equalsIgnoreCase(precioFinal_FH.getText())?true:false;

		return r1;
	}

	public boolean verifySubTitle_FH() {

		String subTitle= listSubtitles.stream().findFirst().get().getText();
		boolean r1= subTitle.equalsIgnoreCase("Who's flying?")?true:false;
		return r1;

	}

	public boolean verifyTickets_FH() {

		HotelDTO hotel = CONTEXT.get("hotel");

		String a= listTicketsRooms.get(0).getText().substring(0,1);
		System.out.println(a);
		boolean r1 = hotel.getQuantityAdults().equalsIgnoreCase(listTicketsRooms.get(0).getText().substring(0,1))?true:false;

		return r1;
	}

}
