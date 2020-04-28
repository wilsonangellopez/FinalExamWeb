package com.automation.training.pages;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

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
import com.google.inject.Key;
import net.bytebuddy.asm.Advice.Return; 
import static com.automation.training.utils.TestContext.CONTEXT;

public class TravelocityHomePage extends BasePage {

	public TravelocityHomePage(WebDriver driver) {	
		super(driver);
		driver.get("https://www.travelocity.com/");
	}

	@FindBy(id="tab-flight-tab-hp")
	private WebElement btnFlights;

	@FindBy(id="tab-hotel-tab-hp")
	private WebElement btnHotel;

	@FindBy(css="#tab-package-tab-hp")
	private WebElement btnVacationPackages;

	@FindBy(css="#fh-fh-hp-package")
	private WebElement RbnFlightHotel;

	@FindBy(xpath="//label[@id='flight-type-roundtrip-label-hp-flight']")
	private WebElement btnRoundtrip;

	@FindBy(css="#package-origin-hp-package")
	private WebElement inputFlyingFrom_FH;

	@FindBy(css="#package-destination-hp-package")
	private WebElement inputFlyingTo_FH;

	@FindBy(id="flight-departing-hp-flight")
	private WebElement inputCalendarDeparture;

	@FindBy(css="#package-departing-hp-package")
	private WebElement inputCalDep_FH;

	@FindBy(css="input#package-departing-hp-package")
	private WebElement inputCalDep_E;

	@FindBy(id="package-returning-hp-package")
	private WebElement inputCalRet_FH;

	@FindBy(xpath="//input[@id='flight-returning-hp-flight']")
	private WebElement inputCalendarReturning;

	@FindBy(css="div[class='datepicker-cal-month']")
	private WebElement calendarioDiv;

	@FindBy(xpath="//button[contains(@class,'datepicker-next btn')]")
	private WebElement btnNextCalendar;

	@FindBy(xpath="//button[contains(@class,'datepicker-paging datepicker-prev btn-paging btn-secondary prev')]")
	private WebElement btnBackCalendar;

	@FindBy(xpath="//caption[@class=\"datepicker-cal-month-header\"]")
	private WebElement MesesCalendar;

	@FindBy(xpath="//button[@class='btn-primary btn-action gcw-submit ' and span[contains(text(),'Search')]]")
	private WebElement btnSearch;

	@FindBy(id="search-button-hp-package")
	private WebElement btnSearch_FH;

	@FindBy(id="package-rooms-hp-package")
	private WebElement drpRooms2;
	//-3
	@FindBy(id="hotel-destination-hp-hotel")
	private WebElement inputGoingTo;

	@FindBy(id="hotel-checkin-hp-hotel")
	private WebElement inputCheckIn_H;

	@FindBy(id="hotel-checkout-hp-hotel")
	private WebElement inputCheckOut_H;

	@FindBy(id="package-checkin-hp-package")
	private WebElement inputCheckIn_E;

	@FindBy(id="package-checkout-hp-package")
	private WebElement inputCheckOut_E;

	@FindBy(css="#partialHotelBooking-hp-package")
	private WebElement chkBtnHotelPartofStay;

	@FindBy(id="tab-cruise-tab-hp")
	private WebElement btnCruises;

	@FindBy(id="cruise-destination-hp-cruise")
	private WebElement dropDownListGoingTo;

	@FindBy(id="cruise-start-date-hp-cruise")
	private WebElement inputCruiseDepartureEarly;

	@FindBy(id="cruise-end-date-hp-cruise")
	private WebElement inputCruiseLate;

	@FindBy(id="cruise-adults-hp-cruise")
	private WebElement drpQuantityPersonas_c;

	@FindBy(css="div[class='datepicker-cal-month'] button[class='datepicker-cal-date']")
	private List<WebElement> listaBotonesCalendario;

	public void clickFlights() {

		Logger.printInfo("click en fligts");

		getWait().until(ExpectedConditions.elementToBeClickable(btnFlights));
		btnFlights.click();

	}

	public boolean verifyPageOpen() {

		boolean ispresent= elementoPresente(By.cssSelector("#fh-fh-hp-package")) != false && 
				elementoPresente(By.cssSelector("#fh-fh-hp-package"))!=false?true:false;

		return ispresent;

	}

	public boolean clickVacationPackages() {

		boolean isPresentBtn = elementoPresente(By.cssSelector("#tab-package-tab-hp"));
		if(isPresentBtn) {
			getWait().until(ExpectedConditions.elementToBeClickable(btnVacationPackages));
			btnVacationPackages.click();	

			boolean isPresent = elementoPresente(By.cssSelector("#fh-fh-hp-package"));
			WebElement btnFlightHotel = getDriver().findElement(By.cssSelector("#fh-fh-hp-package"));
			if(!btnFlightHotel.isSelected()){
				btnFlightHotel.click();
				return true;
			}
			else return true;
		}
		return false;

	}

	public void clickRoundTrip() {
		Logger.printInfo("click en RoundTrip");
		String sb = isPresent(btnRoundtrip).getText().toString();
		if(sb!=null)click(btnRoundtrip);
	}

	public WebElement buscarElemento(String par) {


		switch (par) {
		case "Flying from FH":

			par= "Flying from";

			Logger.printInfo("buscando elemento con texto: " + par);

			By locator = By.xpath(String.format("//span[text()=\"%s\"]//following-sibling::input[@aria-autocomplete='list' and not(contains(@class,'disabled')) ]", par));
			List<WebElement> list = getDriver().findElements(locator);
			WebElement e= list.get(3);
			Logger.printInfo("Se encontro el WebElement: " + e.toString());

			return e;

		case "Flying to FH":

			par= "Flying to";

			Logger.printInfo("buscando elemento con texto: " + par);

			By locatorTo = By.xpath(String.format("//span[text()=\"%s\"]//following-sibling::input[@aria-autocomplete='list' and not(contains(@class,'disabled')) ]", par));
			List<WebElement> myList = getDriver().findElements(locatorTo);
			WebElement elemento= myList.get(2);
			Logger.printInfo("Se encontro el WebElement: " + elemento.toString());

			return elemento;


		default:
			break;
		}


		Logger.printInfo("buscando elemento con texto: " + par);

		By locator = By.xpath(String.format("//span[text()=\"%s\"]//following-sibling::input[@aria-autocomplete='list' and not(contains(@class,'disabled')) ]", par));
		List<WebElement> list = getDriver().findElements(locator);
		Optional<WebElement> e = list.stream().findFirst();
		Logger.printInfo("Se encontro el WebElement: " + e.toString());

		return e.get();

	}

	public void crearObjetoContext(String param) {

		FlightsDTO fly = new FlightsDTO();

		switch (param) {

		case "Las Vegas, NV (LAS-McCarran Intl.)":

			fly.setFlightFrom(param);
			CONTEXT.set("fly", fly);
			break;

		case "Los Angeles, California":

			fly = CONTEXT.get("fly"); 
			fly.setFlightTo(param);
			CONTEXT.set("fly", fly);
			break;
		}
	}

	//aqui se le puede enviar una cadena para saber si es from o to
	public void seleccionarFromAndTo(WebElement e, String sb) {

		Logger.printInfo("En el metodo de seleccionar: " + sb );

		if(isPresent(e).getClass()!=null) {
			click(e);
			Logger.printInfo("click en el input" + e.getAttribute("class").toString());	
		}

		switch (sb) {
		case "LAS":

			String from= "Las Vegas, NV (LAS-McCarran Intl.)";
			escribir(e, from);
			darEnter(e);	
			crearObjetoContext(from);
			break;

		case "LAX":

			String to="Los Angeles, California";
			//			String to="Australia Square, Sydney, New South Wales, Australia";
			escribir(e, to);
			darEnter(e);
			crearObjetoContext(to);
			break;

		}


	}

	public void seleccionarDepartureDate(String depRet) {

		FlightsDTO fly = new FlightsDTO();

		LocalDate fechaFutura;
		LocalDate fechaYa= LocalDate.now();
		final int diaYa= fechaYa.getDayOfMonth();
		int ianio;


		switch (depRet) {

		case "Departure":

			Logger.printInfo("Selecionando el calendario en: "+ depRet);

			if(inputCalendarDeparture.isDisplayed()) inputCalendarDeparture.click();

			fechaFutura = calcularFecha2(depRet);
			Month mesFuturo = fechaFutura.getMonth();

			final int idiaFuturo= fechaFutura.getDayOfMonth();
			final int imesFuturoEnCalendario= fechaFutura.getMonthValue()-1;
			ianio= fechaFutura.getYear();


			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed() ) {

				boolean isPresent= elementoPresente(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				String mesDeListaBtnDias=listaBtnDias.stream()					
						.filter(x-> diaYa == Integer.parseInt(x.getAttribute("data-day")))
						.findFirst().get().getAttribute("data-month").toString();

				int mesActual= Integer.parseInt(mesDeListaBtnDias);

				if(!isPresent) {
					String b = Keys.BACK_SPACE.toString();
					String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
					inputCheckOut_H.sendKeys(del + mesFuturo + "/" + idiaFuturo + "/" + ianio);

					break;
				}

				while (imesFuturoEnCalendario>mesActual) {

					mesActual=obtenerMes(imesFuturoEnCalendario);
				}

				listaBotonesCalendario.stream()					
				.filter(x-> idiaFuturo == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imesFuturoEnCalendario ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();
				break;
			}

		case "Returning":

			Logger.printInfo("Selecionando el calendario en: "+ depRet);

			if(inputCalendarReturning.isDisplayed()) inputCalendarReturning.click();

			fechaFutura = calcularFecha2(depRet);
			mesFuturo = fechaFutura.getMonth();

			int idiaFuturo2= fechaFutura.getDayOfMonth();
			int imesFuturoEnCalendario2= fechaFutura.getMonthValue()-1;
			ianio= fechaFutura.getYear();


			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed() ) {

				boolean isPresent= elementoPresente(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				String mesDeListaBtnDias=listaBtnDias.stream()					
						.filter(x-> diaYa == Integer.parseInt(x.getAttribute("data-day")))
						.findFirst().get().getAttribute("data-month").toString();

				int mesActual= Integer.parseInt(mesDeListaBtnDias);

				if(!isPresent) {
					String b = Keys.BACK_SPACE.toString();
					String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
					inputCheckOut_H.sendKeys(del + mesFuturo + "/" + idiaFuturo2 + "/" + ianio);

					break;
				}

				while (imesFuturoEnCalendario2>mesActual) {

					mesActual=obtenerMes(imesFuturoEnCalendario2);
				}

				listaBotonesCalendario.stream()					
				.filter(x-> idiaFuturo2 == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imesFuturoEnCalendario2 ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();
				break;

			}


		case "Departure2":


			if(inputCalDep_FH.isDisplayed()) inputCalDep_FH.click();

			LocalDate fechaFuturaDep = calcularFecha2("Departure2");

			Month mesDepIng = fechaFuturaDep.getMonth();
			String mesSBDepIng= mesDepIng.getDisplayName(TextStyle.FULL, new Locale("en", "En"));
			mesSBDepIng= mesSBDepIng.substring(0, 3);
			int idiasDep= fechaFuturaDep.getDayOfMonth();
			ianio= fechaFuturaDep.getYear();

			String dateDep_FH= mesSBDepIng+ " " + idiasDep; 

			fly.setFlightDateDeparture(dateDep_FH);
			CONTEXT.set("flyDep_FH", fly);

			System.out.println("al inicio fecha salida " + mesSBDepIng);

			int imesDep= fechaFuturaDep.getMonthValue()-1;

			if(calendarioDiv.isDisplayed()) {

				boolean isPresent= elementoPresente(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));

				if(!isPresent) {
					String b = Keys.BACK_SPACE.toString();
					String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
					inputCheckOut_H.sendKeys(del + mesDepIng + "/" + idiasDep + "/" + ianio);

					break;
				}

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));
				int mesActual= Integer.parseInt(listaBtnDias.get(0).getAttribute("data-month"));

				while (imesDep>mesActual) {

					mesActual=obtenerMes(imesDep);
				}

				List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				listaBtnDias2.stream()					
				.filter(x-> idiasDep == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imesDep ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();

			}
			break;

		case "Returning2":

			if(inputCalRet_FH.isDisplayed()) inputCalRet_FH.click();


			if(calendarioDiv.isDisplayed()) {

				boolean isPresent= elementoPresente(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));

				fechaFutura= calcularFecha2("Returning2");

				Month mesRetIng = fechaFutura.getMonth();
				String mesSBRepIng= mesRetIng.getDisplayName(TextStyle.FULL, new Locale("en", "En"));
				mesSBRepIng= mesSBRepIng.substring(0, 3);
				int idias= fechaFutura.getDayOfMonth();

				String dateRet_FH= mesSBRepIng+ " " + idias; 

				fly = CONTEXT.get("flyDep_FH"); 
				fly.setFlightDateReturn(dateRet_FH);
				CONTEXT.set("flyDep_FH", fly);

				int imes= fechaFutura.getMonthValue();
				int imes2= fechaFutura.getMonthValue()-1;
				ianio= fechaFutura.getYear();


				if(!isPresent) {
					String b = Keys.BACK_SPACE.toString();
					String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
					inputCalRet_FH.sendKeys(del + imes + "/" + idias + "/" + ianio);

					break;
				}

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				String mesDeListaBtnDias=listaBtnDias.stream()					
						.filter(x-> diaYa == Integer.parseInt(x.getAttribute("data-day")))
						.findFirst().get().getAttribute("data-month").toString();

				int mesActual= Integer.parseInt(mesDeListaBtnDias);


				while (imes2>mesActual) {

					mesActual=obtenerMes(imes2); 

				}

				List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				listaBtnDias2.stream()					
				.filter(x-> idias == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imes2 ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();

			}
			break;

		case "Departure3":
			if(inputCheckIn_H.isDisplayed()) inputCheckIn_H.click();

			LocalDate fechaFuturaDep1 = calcularFecha2("Departure3");

			Month mesDepIng1 = fechaFuturaDep1.getMonth();
			String mesSBDepIng1= mesDepIng1.getDisplayName(TextStyle.FULL, new Locale("en", "En"));
			mesSBDepIng1= mesSBDepIng1.substring(0, 3);
			int idiasDep1= fechaFuturaDep1.getDayOfMonth();

			int imesDep1= fechaFuturaDep1.getMonthValue()-1;
			getWait().until(ExpectedConditions.visibilityOf(calendarioDiv));

			if(isPresent(calendarioDiv)!=null) {
				if( calendarioDiv.isDisplayed()) {

					List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

					String mesDeListaBtnDias=listaBtnDias.stream()					
							.filter(x-> diaYa == Integer.parseInt(x.getAttribute("data-day")))
							.findFirst().get().getAttribute("data-month").toString();

					int mesActual= Integer.parseInt(mesDeListaBtnDias);

					while (imesDep1>mesActual) {

						mesActual=obtenerMes(imesDep1);

					}

					List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

					listaBtnDias2.stream()					
					.filter(x-> idiasDep1 == Integer.parseInt(x.getAttribute("data-day")))
					.filter(x-> imesDep1 ==Integer.parseInt(x.getAttribute("data-month")))
					.findFirst().get().click();

				}
			}
			break;

		case "Returning3":

			if(inputCheckOut_H.isDisplayed()) inputCheckOut_H.click();


			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed()) {

				boolean isPresent= elementoPresente(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));

				fechaFutura= calcularFecha2("Returning3");


				Month mesRetIng = fechaFutura.getMonth();
				String mesSBRepIng= mesRetIng.getDisplayName(TextStyle.FULL, new Locale("en", "En"));
				mesSBRepIng= mesSBRepIng.substring(0, 3);
				int idias= fechaFutura.getDayOfMonth();

				int imes= fechaFutura.getMonthValue();
				int imes2= fechaFutura.getMonthValue()-1;
				ianio= fechaFutura.getYear();


				if(!isPresent) {
					String b = Keys.BACK_SPACE.toString();
					String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
					inputCheckOut_H.sendKeys(del + imes + "/" + idias + "/" + ianio);
					btnHotel.click();

					break;
				}

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				String mesDeListaBtnDias=listaBtnDias.stream()					
						.filter(x-> diaYa == Integer.parseInt(x.getAttribute("data-day")))
						.findFirst().get().getAttribute("data-month").toString();

				int mesActual= Integer.parseInt(mesDeListaBtnDias);

				while (imes2>mesActual) {

					mesActual=obtenerMes(imes2);

				}

				List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				listaBtnDias2.stream()					
				.filter(x-> idias == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imes2 ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();

			}
			break;


		case "Departure4a":

			if(inputCalDep_FH.isDisplayed()) inputCalDep_FH.click();

			LocalDate fechaFuturaDep4a = calcularFecha2("Departure4a");

			Month mesDepIng4a = fechaFuturaDep4a.getMonth();
			String mesSBDepIng4a= mesDepIng4a.getDisplayName(TextStyle.FULL, new Locale("en", "En"));
			mesSBDepIng4a= mesSBDepIng4a.substring(0, 3);
			int idiasDep4a= fechaFuturaDep4a.getDayOfMonth();
			int imesDep4a= fechaFuturaDep4a.getMonthValue()-1;

			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed()) {

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				String mesDeListaBtnDias=listaBtnDias.stream()					
						.filter(x-> diaYa == Integer.parseInt(x.getAttribute("data-day")))
						.findFirst().get().getAttribute("data-month").toString();

				int mesActual= Integer.parseInt(mesDeListaBtnDias);

				while (imesDep4a>mesActual) {

					mesActual=obtenerMes(imesDep4a);
				}

				List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				listaBtnDias2.stream()					
				.filter(x-> idiasDep4a == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imesDep4a ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();

				break;
			}

		case "Returning4a":

			if(inputCalRet_FH.isDisplayed()) inputCalRet_FH.click();


			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed()) {

				boolean isPresent= elementoPresente(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));

				fechaFutura= calcularFecha2("Returning4a");


				Month mesRetIng = fechaFutura.getMonth();
				String mesSBRepIng= mesRetIng.getDisplayName(TextStyle.FULL, new Locale("en", "En"));
				mesSBRepIng= mesSBRepIng.substring(0, 3);
				int idias= fechaFutura.getDayOfMonth();


				int imes= fechaFutura.getMonthValue();
				int imes2= fechaFutura.getMonthValue()-1;
				ianio= fechaFutura.getYear();


				if(!isPresent) {
					String b = Keys.BACK_SPACE.toString();
					String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
					inputCalRet_FH.sendKeys(del + imes + "/" + idias + "/" + ianio);
					break;

				}

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				int mesActual= Integer.parseInt(listaBtnDias.get(0).getAttribute("data-month"));

				while (imes2>mesActual) {

					mesActual=obtenerMes(imes2);
				}

				List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				listaBtnDias2.stream()					
				.filter(x-> idias == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imes2 ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();

			}
			break;



		case "Departure4b":

			if(inputCheckIn_E.isDisplayed()) inputCheckIn_E.click();

			LocalDate fechaFuturaDep4 = calcularFecha2("Departure4b");

			Month mesDepIng4 = fechaFuturaDep4.getMonth();
			String mesSBDepIng4= mesDepIng4.getDisplayName(TextStyle.FULL, new Locale("en", "En")).substring(0,3);
			int idiasDep4= fechaFuturaDep4.getDayOfMonth();
			int imesDep4= fechaFuturaDep4.getMonthValue()-1;

			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed()) {

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				String mesDeListaBtnDias=listaBtnDias.stream()					
						.filter(x-> idiasDep4 == Integer.parseInt(x.getAttribute("data-day")))
						.findFirst().get().getAttribute("data-month").toString();

				int mesActual= Integer.parseInt(mesDeListaBtnDias);

				while (imesDep4>mesActual) {

					mesActual=obtenerMes(imesDep4);
				}

				List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				listaBtnDias2.stream()					
				.filter(x-> idiasDep4 == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imesDep4 ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();

			}
			break;

		case "Returning4b":

			if(inputCheckOut_E.isDisplayed()) inputCheckOut_E.click();

			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed()) {

				boolean isPresent= elementoPresente(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
				fechaFutura= calcularFecha2("Returning4b");

				Month mesRetIng = fechaFutura.getMonth();
				String mesSBRepIng= mesRetIng.getDisplayName(TextStyle.FULL, new Locale("en", "En"));
				mesSBRepIng= mesSBRepIng.substring(0, 3);
				int idias= fechaFutura.getDayOfMonth();

				int imes= fechaFutura.getMonthValue();
				int imes2= fechaFutura.getMonthValue()-1;
				ianio= fechaFutura.getYear();

				if(!isPresent) {
					String b = Keys.BACK_SPACE.toString();
					String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
					inputCheckOut_H.sendKeys(del + imes + "/" + idias + "/" + ianio);
					//					btnHotel.click();

					break;
				}

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				String mesDeListaBtnDias=listaBtnDias.stream()					
						.filter(x-> diaYa == Integer.parseInt(x.getAttribute("data-day")))
						.findFirst().get().getAttribute("data-month").toString();

				int mesActual= Integer.parseInt(mesDeListaBtnDias);


				while (imes2>mesActual) {

					mesActual=obtenerMes(imes2);
					
				}

				List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month'][1]//button[@class='datepicker-cal-date']"));

				listaBtnDias2.stream()					
				.filter(x-> idias == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imes2 ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();
			}
			break;


		case "Departure5":

			if(inputCruiseDepartureEarly.isDisplayed()) inputCruiseDepartureEarly.click();

			fechaYa= LocalDate.now();
			//			diaYa= fechaYa.getDayOfMonth();
			LocalDate fechaFuturaDep5 = calcularFecha2("Departure5");

			Month mesDepIng5 = fechaFuturaDep5.getMonth();
			int idiasDep5= fechaFuturaDep5.getDayOfMonth();
			int imesDep5= fechaFuturaDep5.getMonthValue()-1;
			ianio= fechaFuturaDep5.getYear();

			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed()) {

				boolean isPresent= elementoPresente(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				String mesDeListaBtnDias=listaBtnDias.stream()					
						.filter(x-> diaYa == Integer.parseInt(x.getAttribute("data-day")))
						.findFirst().get().getAttribute("data-month").toString();

				int mesActual= Integer.parseInt(mesDeListaBtnDias);

				if(!isPresent) {
					String b = Keys.BACK_SPACE.toString();
					String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
					inputCheckOut_H.sendKeys(del + mesDepIng5 + "/" + idiasDep5 + "/" + ianio);

					break;
				}

				while (imesDep5>mesActual) {

					mesActual=obtenerMes(imesDep5);
				}

				listaBotonesCalendario.stream()					
				.filter(x-> idiasDep5 == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imesDep5 ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();

			}
			break;

		case "Returning5":

			if(inputCruiseLate.isDisplayed()) inputCruiseLate.click();

			if(isPresent(calendarioDiv)!=null && calendarioDiv.isDisplayed()) {

				boolean isPresent= elementoPresente(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
				fechaFutura= calcularFecha2("Returning5");

				Month mesRetIng = fechaFutura.getMonth();
				String mesSBRepIng= mesRetIng.getDisplayName(TextStyle.FULL, new Locale("en", "En")).substring(0,3);
				int idias= fechaFutura.getDayOfMonth();

				int imes= fechaFutura.getMonthValue();
				int imesFuturo= fechaFutura.getMonthValue()-1;
				int ianio5= fechaFutura.getYear();

				if(!isPresent) {
					String b = Keys.BACK_SPACE.toString();
					String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
					inputCheckOut_H.sendKeys(del + imes + "/" + idias + "/" + ianio5);

					break;
				}

				List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				int mesActual= Integer.parseInt(listaBtnDias.get(0).getAttribute("data-month"));

				while (imesFuturo>mesActual) {

					mesActual=obtenerMes(imesFuturo);
				}

				List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));

				listaBtnDias2.stream()					
				.filter(x-> idias == Integer.parseInt(x.getAttribute("data-day")))
				.filter(x-> imesFuturo ==Integer.parseInt(x.getAttribute("data-month")))
				.findFirst().get().click();
			}
			break;

		}

	}

	private int obtenerMes( int imesFuturoEnCalendario) {

		List<WebElement> listaBtnDias = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));
		int mesActual= Integer.parseInt(listaBtnDias.get(0).getAttribute("data-month").toString());

		if(Integer.parseInt(listaBtnDias.get(0).getAttribute("data-month").toString())==imesFuturoEnCalendario) {
			return mesActual;
		}

		btnNextCalendar.click();
		List<WebElement> listaBtnDias2 = getDriver().findElements(By.xpath("//div[@class='datepicker-cal-month']//button[@class='datepicker-cal-date']"));
		mesActual= Integer.parseInt(listaBtnDias2.get(0).getAttribute("data-month").toString());

		return mesActual;

	}

	public void clickBtnSearch(String param) {

		switch (param) {

		case "H":
			List<WebElement> listaBtns = getDriver().findElements(By.xpath("//button[@class='btn-primary btn-action gcw-submit ' and span[contains(text(),'Search')]]"));
			listaBtns.stream()					
			.findFirst().get().click();
			Logger.printInfo("click en el btn Search");
			break;

		case "FH":
			btnSearch_FH.click();
			break;

		default:
			break;
		}

	}

	public void selectRooms() {

		Select drpRooms = new Select (getDriver().findElement(By.cssSelector("#package-rooms-hp-package , #hotel-rooms-hp-hotel")));
		drpRooms.selectByVisibleText("1");

		HotelDTO hotel = new HotelDTO();
		hotel.setRooms("1");

		CONTEXT.set("hotel", hotel);

	}

	public void selectQuantityAdults() {

		Select drpQuantityAdults = new Select (getDriver().findElement(By.cssSelector("#package-1-adults-hp-package, #hotel-1-adults-hp-hotel")));
		drpQuantityAdults.selectByVisibleText("2");

		HotelDTO hotel = new HotelDTO();
		hotel = CONTEXT.get("hotel");
		hotel.setQuantityAdults("2");
		CONTEXT.set("hotel", hotel);

	}

	public boolean clickBtnHotels_H() {

		Logger.printInfo("Metodo click btn hotel");

		if(isPresent(btnHotel)!=null) {
			btnHotel.click();
			return true;
		}
		return false;
	}

	public void reFillMontevideoUruguay_H() {

		String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
		inputGoingTo.sendKeys(del + "Montevideo, Uruguay");
		clickInFrame_H();
	}

	public void clickInFrame_H() {

		WebElement frame= getDriver().findElement(By.cssSelector("section#WizardHero"));
		frame.click();
	}

	public boolean fillGoingTo_H() {

		if(isPresent(inputGoingTo)!=null) {
			reFillMontevideoUruguay_H();
			btnHotel.click();
			return true;			
		}
		return false;
	}

	public void clickSearchBtn_H() {

		List<WebElement> listBtnSearch= getDriver().findElements(By.cssSelector("div[class='cols-nested'] label button[class='btn-primary btn-action gcw-submit '][data-gcw-change-submit-text='Search']"));
		WebElement ultimoBtn= listBtnSearch.stream().reduce((first, second) -> second).get();
		ultimoBtn.click();
	}

	public boolean selectCheckBox_E() {

		Logger.printInfo("en el metodo de seleccionar el check box");

		if(isPresent(chkBtnHotelPartofStay)!=null) {

			if(!chkBtnHotelPartofStay.isSelected()) {

				chkBtnHotelPartofStay.click();
				return true;
			}
			else return true;
		}
		return false;
	}

	public boolean verifyErrorDisplay_E() {

		WebElement errorMsj= getDriver().findElement(By.cssSelector("a.error-link"));
		boolean r1= errorMsj
				.getText()
				.equalsIgnoreCase("Your partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates.")?true:false;
		return r1;

	}

	public void clickBtnCruises() {
		if(isPresent(btnCruises)!=null) {btnCruises.click();}
		else {Logger.printInfo("no esta el elemento" + btnCruises.getClass());}
	}

	public boolean clickDropDownList() {

		getWait().until(ExpectedConditions.elementToBeClickable(dropDownListGoingTo));
		Select drp = new Select(dropDownListGoingTo);
		drp.selectByValue("europe");
		String a = drp.getFirstSelectedOption().getText();
		boolean r1= drp.getFirstSelectedOption().getText().equals("Europe")?true:false;
		return r1;
	}

	public void presionarEnter() {

		inputCruiseLate.sendKeys(Keys.ENTER);
	}

}
