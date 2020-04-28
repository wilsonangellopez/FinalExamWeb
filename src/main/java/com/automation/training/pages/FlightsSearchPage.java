package com.automation.training.pages;

import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.SingleSelectionModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.dto.CruceroDTO;
import com.automation.dto.FlightsDTO;
import com.automation.dto.HotelDTO;
import com.automation.training.utils.Logger;
import com.google.common.collect.Comparators;
import static com.automation.training.utils.TestContext.CONTEXT;

public class FlightsSearchPage extends BasePage {

	@FindBy(xpath="//select[@id='sortDropdown']")
	private WebElement sortBy;

	@FindBy(xpath="class='progress-bar'")
	private WebElement progresBar;

	@FindBy(css="#flightModuleList li.flight-module.segment.offer-listing")
	private List<WebElement> contenedor;

	@FindBy(xpath="//li[@data-test-id='offer-listing']//span[@class='medium-bold']")
	private List<WebElement> contenedorHorasDep;
	@FindBy(xpath="//li[@data-test-id='offer-listing']//button[@data-test-id='select-button' and span/span[text()='Select']]")
	private List<WebElement> listSelectBtns;

	@FindBy(xpath="//li[@data-test-id='offer-listing']//button[@data-test-id='select-button' and span/span[text()='Select this fare']]")
	private List<WebElement> listSelectFareBtns;

	@FindBy(css="span[class='title-city-text']")
	private WebElement Subtitulo;

	@FindBy(css=".title-date-rtv")
	private WebElement departureDate;

	@FindBy(xpath="//li[@data-test-id='offer-listing']//span[@data-test-id='duration']")
	private List<WebElement> listaFlightDuration;

	@FindBy(css="fieldset[class='sort-filter-bar control box']")
	private WebElement sortOptions;

	@FindBy(css="button[class='origin fakeLink']")
	private WebElement btnOrigin_FH;

	@FindBy(css="button[class='destination fakeLink']")
	private List<WebElement> ListBtnDestination_FH;

	@FindBy(css="span[class='day-of-week']+span")
	private List<WebElement> listFechas;


	@FindBy(css="h1[class='section-header-main']")
	private WebElement subTitle_FH;

	@FindBy(css="div[class='flex-link-wrap']")
	private List<WebElement> listaResultados_FH;

	@FindBy(css="button[aria-label='Sort by: Price']")
	private WebElement btnPrice_FH;

	@FindBy(css="ul[class='hotel-price']") 
	private List<WebElement> listaPreciosFH;

	@FindBy(css="ul[class='hotel-price'] li[class~='actualPrice']")
	private List<WebElement> listaPrecios_fh2;

	@FindBy(css="#uitk-live-announce")
	private WebElement loaderFH;

	@FindBy(css="div[class='uitk-card uitk-grid messaging-card all-x-padding-three all-y-padding-three'] a")
	private List<WebElement> listaLinks_H;

	@FindBy(id="length-10-14-ember1337-label")
	private WebElement radioBtnCruisesNights;

	@FindBy(css="div.flex-card")
	private List<WebElement> listContenedorCruseros;


	public FlightsSearchPage(WebDriver driver) {		
		super(driver);
	}



	//SELECCIONA una opcion de un DropDownList
	public boolean searchSorty() {

		Logger.printInfo("en el metodo de buscar el Sort By -  Estamos en la pagina +" + getDriver().getTitle());

		loaderFlightSearchPage();


		if(elementoPresente(By.xpath("//select[@id='sortDropdown']")) && isPresent(sortBy)!=null && sortBy.isEnabled()) {

			Select drpSort = new Select(getWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("sortDropdown")))));
			drpSort.selectByVisibleText("Duration (Shortest)");

			if(drpSort.getClass()!=null)return true;
			else return false;
		}

		if(elementoPresente(By.xpath("//select[@id='sortDropdown']")) && isPresent(sortBy)!=null) {

			Select drpSort = new Select(getWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("sortDropdown")))));
			drpSort.selectByVisibleText("Duration (Shortest)");
			return true;
		}
		else return false;
	}

	//metodo q cuenta los resultados y los btns de seleccionar en cada uno
	public boolean searchResultsAndSelectBtn() {

		elementoPresente(By.xpath("//li[@data-test-id='offer-listing']"));

		List<WebElement> listaContenedores = super.getDriver().findElements(By.xpath("//li[@data-test-id='offer-listing']"));
		int cantidadContenedores= (int) listaContenedores.stream().count();

		int cantidadBtnsSelect= (int) listSelectBtns.stream().count();

		if(cantidadContenedores==cantidadBtnsSelect) return true;
		else return false;

	}

	/*	aqui valido que todos los resultados de vuelos tengan una duracion , ADEMAS DE:
		aqui se arma el objeto y se pone en CONTEXTO para despues compararlo.*/
	public boolean searchResultsAndFlightDuration() {

		elementoPresente(By.xpath("//li[@data-test-id='offer-listing']"));

		List<WebElement> listaContenedores = super.getDriver().findElements(By.xpath("//li[@data-test-id=\"offer-listing\"]"));
		int cantidadContenedores= (int) listaContenedores.stream().count();

		int cantidadFlightDuration= (int) listaFlightDuration.stream().count();

		if(cantidadContenedores==cantidadFlightDuration) return true;
		else return false;

	}

	public void getInfoDepartureFlight() {

		Logger.printInfo("en el metodo getInfoDepartureFlight");

		elementoPresente(By.cssSelector("li[data-test-id=\"offer-listing\"] span[class=\"medium-bold\"]"));
		List<WebElement> contenedorHorasDep= getDriver().findElements(By.cssSelector("li[data-test-id=\"offer-listing\"] span[class=\"medium-bold\"]"));

		String  primertiempo = listaFlightDuration.stream().findFirst().get().getText();

		FlightsDTO flyDep = new FlightsDTO();

		flyDep.setFlightDurationTotalDeparture(primertiempo);

		List<WebElement> listHDI = obtainsList(contenedorHorasDep, "HoraDepartureIni");
		String horaSalidaInicial= listHDI.get(0).getText();
		flyDep.setFlightTimeInitDeparture(horaSalidaInicial);

		elementoPresente(By.cssSelector("li[data-test-id='offer-listing'] span[class='medium-bold']"));
		List<WebElement> contenedorHorasDepFin = getDriver().findElements(By.xpath("//li[@data-test-id='offer-listing']//span[@class='medium-bold']"));

		List<WebElement> listHDF = obtainsList(contenedorHorasDepFin, "HoraDepartureFin");
		String horaSalidaFin= listHDF.get(0).getText();
		flyDep.setFlightTimeEndDeparture(horaSalidaFin);
		System.out.println("hora salida fin");

		String sbDepartureDate= departureDate.getText();
		flyDep.setFlightDateDeparture(sbDepartureDate);

		CONTEXT.set("FlyDep", flyDep);
	}

	public void getInfoReturnFlight() {

		FlightsDTO flyRet = new FlightsDTO();

		List<WebElement> contenedorHorasRet = getDriver().findElements(By.cssSelector("li[data-test-id='offer-listing'] span[class='medium-bold']"));
		String primertiempo= listaFlightDuration.get(2).getText();

		flyRet.setFlightDurationTotalReturn(primertiempo);

		List<WebElement> listHRI = obtainsList(contenedorHorasRet, "HoraDepartureIni");
		String horaReturnInicial= listHRI.get(2).getText();
		flyRet.setFlightTimeInitDeparture(horaReturnInicial);

		elementoPresente(By.cssSelector("li[data-test-id='offer-listing'] span[class='medium-bold']"));
		List<WebElement> contenedorHorasRetFin = getDriver().findElements(By.cssSelector("li[data-test-id='offer-listing'] span[class='medium-bold']"));
		List<WebElement> listHRF = obtainsList(contenedorHorasRetFin, "HoraReturnFin");

		String horaSalidaFin= listHRF.get(2).getText();
		flyRet.setFlightTimeEndDeparture(horaSalidaFin);

		String sbReturnDate= departureDate.getText();
		flyRet.setFlightDateDeparture(sbReturnDate);

		CONTEXT.set("FlyRet", flyRet);
		FlightsDTO fr = CONTEXT.get("FlyRet");

	}

	//metodo que cuenta los resultados y los detalles de cada resultado
	public boolean searchResultsAndDetailsAndBags() {

		getWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//li[@data-test-id='offer-listing']"))));

		List<WebElement> listaContenedores = super.getDriver().findElements(By.xpath("//li[@data-test-id='offer-listing']"));
		int cantidadContenedores= (int) listaContenedores.stream().count();

		List<WebElement> listaDetailsAndBags = super.getDriver().findElements(By.xpath("//li[@data-test-id='offer-listing']//span[@class='show-flight-details']"));
		int cantidadDetailsAndBags= (int) listaDetailsAndBags.stream().count();

		if(cantidadContenedores==cantidadDetailsAndBags) return true;
		else return false;

	}


	//metodo que busca en una lista de horas y verifica que esten en orden
	@SuppressWarnings("unchecked")
	public boolean selectFromSortList() {

		Logger.printInfo("en el metodo SelectFromSortList");

		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[@data-test-id='offer-listing']//span[@class='duration-emphasis']")));

		//		List<WebElement> listaDuration = obtainsDurationSpans(contenedor, "verifyListahoras");
		List<WebElement> listaDuration = obtainsList(contenedor, "verifyListahoras");

		List<String> duracion = listaDuration.stream().map(x-> x.getText()).collect(Collectors.toList());

		List<Integer> listadoNumeros = new ArrayList<>();

		for (String horas : duracion) {

			String[] vectorH= horas.split(" ");

			vectorH[0]= vectorH[0].replace("h", "");
			Integer hora= Integer.parseInt(vectorH[0])*60;

			vectorH[1]=vectorH[1].replace("m", "");
			listadoNumeros.add(hora+Integer.parseInt(vectorH[1]));

		}


		for (int i = 0; i < listadoNumeros.size()-1; i++) {

			if(listadoNumeros.get(i)<=listadoNumeros.get(i+1)) {
				System.out.println("Lista ordenada");				
				return true;
			}

			else if (listadoNumeros.get(i)>listadoNumeros.get(i+1)) {
				System.out.println("Lista Des ordenada");
				return false;				
			}

		}
		return false;

	}

	//Metodo que selecciona el primer vuelo a Los Angeles y el tercero de Regreso
	public boolean clickSelectButtonToLosAngeles() {

		Logger.printInfo("En el metodo Select Button");
//		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.xpath("//li[@data-test-id='offer-listing']"), By.xpath("//button[@data-test-id='select-button' and span/span[text()='Select']]")));

		List<WebElement> listaBtnsSelect = getDriver().findElements(By.xpath("//li[@data-test-id='offer-listing']//button[@data-test-id='select-button' and span/span[text()='Select']]"));

		
		if(isPresent(Subtitulo)!=null && Subtitulo.getText().equalsIgnoreCase("Select your departure to Los Angeles")) {
			
//			if(

			Logger.printInfo("Buscando los ticketes en los Angeles");

			getInfoDepartureFlight();
			listaBtnsSelect.stream().findFirst().get().click();
			return buscarSelectThisfareLosAngeles();
		}
		return false;

	}

	public boolean buscarSelectThisfareLosAngeles() {

		Logger.printInfo("En el metodo Select this fare");

		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[@data-test-id='offer-listing']//button[@data-test-id='select-button' and span/span[text()='Select']]")));

		if(Subtitulo.getText().equalsIgnoreCase("Select your departure to Los Angeles")) {

			Logger.printInfo("Buscando opciones en Los Angeles");

			getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.xpath("//div[@class='basic-economy-tray uitk-grid']"), By.xpath("//button[@data-test-id='select-button' and span/span[text()='Select this fare']]")));

			if(elementoPresente(By.xpath("//div[@class='basic-economy-tray uitk-grid']//button[@data-test-id='select-button' and span/span[text()='Select this fare']]"))) {

				List<WebElement> listaBtns = getDriver().findElements(By.xpath("//div[@class='basic-economy-tray uitk-grid']//button[@data-test-id='select-button' and span/span[text()='Select this fare']]"));

				listaBtns.stream().findFirst().get().click();
				Logger.printInfo("click en el boton Select this Fare");
				return true;
			}
			else
			{
				Logger.printInfo("no hay boton extra para clikar");
				return true;
			}
		}
		return false;
	}

	public boolean selectLasVegasTicket() {

		if(Subtitulo.getText().equalsIgnoreCase("Select your return to Las Vegas")) {

			Logger.printInfo("Buscando ticketes para Las vegas");

			if(elementoPresente(By.xpath("//li[@data-test-id='offer-listing']//button[@data-test-id='select-button' and span/span[text()='Select']]"))!=true) {

				Logger.printInfo("no hay Boton select");

			}


			try {

				getWait().until(ExpectedConditions.elementToBeClickable(listSelectBtns.get(2)));

			} catch (IndexOutOfBoundsException e) {
				Logger.printInfo("The system doesn't display a third ticket");
				return false;
			}

			if(elementoPresente(By.xpath("//li[@data-test-id='offer-listing']//button[@data-test-id='select-button' and span/span[text()='Select']]"))) {

				getInfoReturnFlight();

				try {

					//aqui se selecciona el 3er resultado
					listSelectBtns.get(2).click();

				} catch (IndexOutOfBoundsException e) {
					Logger.printInfo("The system doesn't display a third ticket");
					return false;
				}

				getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[@data-test-id='offer-listing']//button[@data-test-id='select-button' and span/span[text()='Select this fare']]")));
				if(elementoPresente(By.xpath("//div[@class='basic-economy-tray uitk-grid']//button[@data-test-id='select-button' and span/span[text()='Select this fare']]"))) {

					if(listSelectFareBtns.get(2).isDisplayed()) {

						try {

							//aqui se selecciona el 3er resultado
							listSelectFareBtns.get(2).click();
							return true;

						} catch (IndexOutOfBoundsException e) {
							Logger.printInfo("The system doesn't display a third ticket");
							return false;
						}
					}else {
						//						listSelectFareBtns.get(listSelectFareBtns.size()-1).click();
						return true;
					}
				}
			}

		}
		return false;
	}

	private boolean selectLasVegasEspecial() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div[class='grid-container standard-padding ']"), By.cssSelector("a[data-test-id='select-button']")));
		List<WebElement> listaTicketesLAX= getDriver().findElements(By.cssSelector("div[class='grid-container standard-padding '] a[data-test-id='select-button']"));
		listaTicketesLAX.get(listaTicketesLAX.size()-1).click();
		getInfoReturnFlight();
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[@data-test-id='offer-listing']//button[@data-test-id='select-button' and span/span[text()='Select this fare']]")));

		if(listSelectFareBtns.get(listSelectFareBtns.size()-1).isDisplayed()) {
			listSelectFareBtns.get(listSelectFareBtns.size()-1).click();
			return true;
		}
		
		return false;

	}


	public boolean verifySortOptionsFH() {

		Logger.printInfo("Verificando que tenga resultados");
		System.out.println("esta si o no?"+elementoPresente(By.cssSelector("fieldset[class='sort-filter-bar control box']")));
		return elementoPresente(By.cssSelector("fieldset[class='sort-filter-bar control box']"));

	}

	public boolean verifyLoaderVisible() {

		elementoPresente(By.cssSelector("#uitk-live-announce"));

		WebElement loader= getDriver().findElement(By.cssSelector("#uitk-live-announce"));

		if(loader.getAttribute("aria-live").equalsIgnoreCase("polite")) return true;

		boolean result=false;

		while (result!= false){
			Logger.printInfo("Atributo:" + loader.getAttribute("aria-live"));
			result= verify();
			System.out.println("booleana "+ result);
		} 

		return true;

	}

	public boolean loaderFlightSearchPage() {

		if(elementoPresente(By.xpath("//div[@class='progress-bar']"))) {

			WebElement progressBars= getDriver().findElement(By.xpath("//div[@class='progress-bar']"));

			if(	!progressBars.isDisplayed()) {
				System.out.println("NO esta visible");
				return true;
			}
			else {
				System.out.println("si visible");
				getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='progress-bar']")));
				return true;
			}

		}
		return false;
	}

	private boolean verify() {

		WebElement loader= getDriver().findElement(By.cssSelector("#uitk-live-announce"));
		boolean result = loader.getAttribute("aria-live").equalsIgnoreCase("polite")?true:false;
		return result;

	}

	public boolean verifyInfoFlight() {

		if(isPresent(btnOrigin_FH)!=null) {


			String originFly = btnOrigin_FH.getText();
			String destinationFly = ListBtnDestination_FH.get(1).getText().toString();

			String[] vectorFrom = originFly.split(",");
			String fromExpected= vectorFrom[0].replace(" ", "");

			String[] vectorTo = destinationFly.split("\\(");
			//		String[] vectorTo = destinationFly.split(",");
			String toExpected= vectorTo[0].replace(" ", "");

			String fechaSalidaExpected = listFechas.get(0).getText().toString();
			String fechaLlegadaExpected = listFechas.get(1).getText().toString();

			FlightsDTO flightInfo = new FlightsDTO();

			flightInfo = CONTEXT.get("flyDep_FH");
			String fechaSalidaIngresada = flightInfo.getFlightDateDeparture();
			String fechaRetornoIngresada= flightInfo.getFlightDateReturn();

			//------

			FlightsDTO fly = CONTEXT.get("fly");
			String flightFrom = fly.getFlightFrom();

			String[] vectorFF = flightFrom.split(",");
			flightFrom= vectorFF[0].replace(" ", "");

			String flightTo = fly.getFlightTo();
			String[] vectorFT = flightTo.split(",");
			flightTo= vectorFT[0].replace(" ", "");

			boolean resultDestiniFrom = flightFrom.equalsIgnoreCase(fromExpected)?true:false;
			boolean resultDestiniTo = flightTo.equalsIgnoreCase(toExpected)?true:false;

			boolean resultDateDep= fechaSalidaIngresada.equalsIgnoreCase(fechaSalidaExpected);
			boolean resultDateRet= fechaRetornoIngresada.equalsIgnoreCase(fechaLlegadaExpected);

			boolean resultadoFinal= !resultDestiniFrom && !resultDestiniTo && !resultDateDep && !resultDateRet?false:true;

			System.out.println(resultadoFinal);

			return resultadoFinal;
		}
		return false;
	}

	public boolean verifyTitle() {
		String subTitle= subTitle_FH.getText().trim();
		boolean result= subTitle_FH.getText().equalsIgnoreCase(subTitle)?true:false;
		return result;
	}

	public boolean verifyResults() {

		boolean result= listaResultados_FH.size()>0?true:false;
		return result;
	}

	public boolean verifyResultSorteByPrice() {

		btnPrice_FH.click();
		verifyLoaderVisible();
		getDriver().navigate().refresh();
		verifyLoaderVisible();

		getWait().until(ExpectedConditions.textMatches(By.cssSelector("ul[class='hotel-price'] li[class^='actualPrice']"), Pattern.compile("[$?]+([0-9])")));
		List<WebElement> listaPrecios= getDriver().findElements(By.cssSelector("ul[class='hotel-price'] li[class^='actualPrice']"));

		List<String> sbListaPrecios = listaPrecios.stream().map(x-> x.getText().replace("$", "").replace(",", "")).collect(Collectors.toList());
		List<Integer> listaPreciosInt = sbListaPrecios.stream().map(x-> Integer.parseInt(x)).collect(Collectors.toList());
		List<Integer> listaPreciosIntCopy =  sbListaPrecios.stream().map(x-> Integer.parseInt(x)).collect(Collectors.toList());

		return equalLists(listaPreciosInt, listaPreciosIntCopy);
		//		Collections.sort(listaPreciosInt, (x,y)-> x.compareTo(y));

	}

	public boolean selectFirstResultWith3Starts() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("#resultsContainer"), By.cssSelector("strong[class='star-rating rating-secondary star rating'] span[class^='icon icon-stars']")));

		List<WebElement> listaResultados = getDriver().findElements(By.cssSelector("div[class='flex-link-wrap']"));
		List<WebElement> listaEstrellas = getDriver().findElements(By.cssSelector("div[class='flex-link-wrap'] strong[class='star-rating rating-secondary star rating'] span[class^='icon icon-stars']"));

		int ipos=0;
		for (WebElement i : listaEstrellas) {
			System.out.println(i.getAttribute("title"));
			if( i.getAttribute("title").equals("3.0")|i.getAttribute("title").equals("3.5")|i.getAttribute("title").equals("4.0")|i.getAttribute("title").equals("4.5")) {
				ipos= listaEstrellas.indexOf(i);
				System.out.println("Posicion encontrada" + ipos);
//				ipos=ipos+1;
				break;
			}
		}
		WebElement hotelSeleccionado= listaResultados.get(ipos);
		System.out.println(hotelSeleccionado.getText());
		obtain3StartsHotelInfo(hotelSeleccionado);
		listaResultados.get(ipos).findElement(By.cssSelector("a.flex-link")).click();

		return true;
	}

	private void obtain3StartsHotelInfo(WebElement hotelSeleccionado) {

//		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(hotelSeleccionado, By.cssSelector("span[class^='icon icon-stars']")));

		String hotelName= hotelSeleccionado.findElement(By.cssSelector("h4[data-automation='hotel-name']")).getText();
		String value= hotelSeleccionado.findElement(By.cssSelector("li[class^='actualPrice price']")).getText();// tener encuenta q la cadena va con signo pesos
		//		String stars= hotelSeleccionado.findElement(By.cssSelector("span[class^='icon icon-stars']")).getAttribute("title"); //3.0
		String stars= hotelSeleccionado.findElement(By.cssSelector("strong[class='star-rating rating-secondary star rating'] span[class^='icon icon-stars']")).getAttribute("title"); //3.0


		HotelDTO hotel = new HotelDTO();
		hotel = CONTEXT.get("hotel");

		hotel.setName(hotelName);
		hotel.setValue(value);
		hotel.setStars(stars);


		CONTEXT.set("hotel", hotel);
	}

	public boolean findSponsoredSection_H() {

		if(!elementoPresente(By.cssSelector("div[class='uitk-card uitk-grid messaging-card all-x-padding-three all-y-padding-three']"))) {
			Logger.printInfo("la seccion no esta disponible");
			return false;
		}

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div[class='uitk-card uitk-grid messaging-card all-x-padding-three all-y-padding-three']"), By.cssSelector("a")));

		if(isPresent(listaLinks_H.get(0))!=null){

			listaLinks_H.stream().filter(x-> x.getText().equalsIgnoreCase("Sign in")).findFirst().get().click();
			return true;
		}
		return false;
	}

	public boolean verifyFilterRadioButton_C() {
		getWait().until(ExpectedConditions.elementToBeClickable(radioBtnCruisesNights));

		Logger.printInfo("Verify filter");
		if(isPresent(radioBtnCruisesNights)!=null) {
			return true;
		}
		return false;
	}

	public boolean verifyCruisesWithDiscountsAndWithOut_C() {

		boolean r1=false;
		int quantityCruisesWithOutDiscount = listContenedorCruseros.size();
		int quantityCruisesWithDiscount= getDriver().findElements(By.cssSelector("div.flex-card div[class^='message-']")).size();

		return r1= quantityCruisesWithDiscount>0 && quantityCruisesWithOutDiscount>0? true:false;

	}

	public void selectCruiseWithMoreDiscount_C() {

		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("div.flex-card"), By.cssSelector("div[class='flex-content']")));

		List<WebElement> listaDescuentos = getDriver().findElements(By.cssSelector("div.flex-card div[class='message-flag flex-flag']"));

		obtenerDescuentoCadena(listaDescuentos);

	}

	private void obtenerDescuentoCadena(List<WebElement> listaDescuentos) {

		String DescuentoMax = listaDescuentos.stream()
				.map(x-> x.getText().replaceAll("[A-Z\\D\\s]", ""))
				.map(x-> Integer.parseInt(x))
				.max((i, j) -> i.compareTo(j))
				.toString();

		List<WebElement> listaDescuentosOriginal = getDriver().findElements(By.cssSelector("div.flex-card div[class='message-flag flex-flag']"));

		Optional<String> nonEmptyOptional = Optional.ofNullable(DescuentoMax); 
		String  imax=nonEmptyOptional.orElse("50");
		imax= imax.replaceAll("[A-Z\\D\\s]", "");

		int ipos=0;
		for (WebElement i : listaDescuentosOriginal) {
			System.out.println(i.getText() + "VS" + imax);
			if( i.getText().contains(imax)) {

				ipos= listaDescuentos.indexOf(i);
				System.out.println("posicion en la lista" + ipos);
				break;
			}
		}

		List<WebElement> listaCruceros = getDriver().findElements(By.cssSelector("div.flex-card"));
		WebElement cruceroSeleccionado= listaCruceros.get(ipos); 
		obtainCruiseInfo(cruceroSeleccionado);
		listaCruceros.get(ipos).findElement(By.cssSelector("a[id^='selectSailingButton']")).click();


	}

	private void obtainCruiseInfo(WebElement cruceroSeleccionado) {

		String cruceroName= cruceroSeleccionado.findElement(By.cssSelector("div.title-on-ship-image")).getText();// 7 night Europe Cruise
		String cruceroValue= cruceroSeleccionado.findElement(By.cssSelector("span.card-price")).getText();// $599
		String cruceroSaliendoDe= cruceroSeleccionado.findElement(By.cssSelector("div.subtitle-on-ship-image")).getText(); //Departing from Civitavecchia, Italy
		String cruceroFechaSalida= cruceroSeleccionado.findElement(By.cssSelector("span.departing-on")).getText(); //Jul 1, 2020

		CruceroDTO crucero= new CruceroDTO();

		crucero.setName(cruceroName);
		crucero.setValue(cruceroValue);
		crucero.setFrom(cruceroSaliendoDe);
		crucero.setDateDeparture(cruceroFechaSalida);

		CONTEXT.set("crucero", crucero);
	}

}

