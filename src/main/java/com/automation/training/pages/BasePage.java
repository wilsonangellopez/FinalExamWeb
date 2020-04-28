package com.automation.training.pages;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.maven.plugin.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
/////5555import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.training.utils.Logger;
import com.google.common.base.Function;
//import com.idb.servicenow.utils.Logger;
//import com.idb.servicenow.utils.WebDriverManager;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public abstract class BasePage {

	private WebDriver driver;
	private WebDriverWait wait;
	protected Scenario scenario;


	public BasePage(WebDriver pDriver) {

		PageFactory.initElements(pDriver, this);
		wait = new WebDriverWait(pDriver, 30);
		driver= pDriver;

	}

	public WebDriverWait getWait() {

		return wait;
	}

	protected WebDriver getDriver() {

		return driver;
	}

	public void dispose() {

		if(driver!=null) {
			driver.quit();
		}

	}

	protected void click(WebElement webElement) {

		getWait().until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
		Logger.printInfo("Se dio click en: " + webElement);
	}

	protected void escribir(WebElement webElement, String orgDes) {

		getWait().until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.sendKeys(orgDes);
		Logger.printInfo("Escribiendo: "+ orgDes);
	}

	protected void borrar(WebElement webElement) {

		getWait().until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.sendKeys(Keys.CLEAR);
		webElement.sendKeys(" ");
	}

	protected void darEnter(WebElement webElement) {

		getWait().until(ExpectedConditions.elementToBeClickable(webElement));		
		webElement.sendKeys(Keys.ENTER);
		Logger.printInfo("Se dio enter");
	}


	protected WebElement isPresent(final WebElement element) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(30, TimeUnit.SECONDS) 			
				.pollingEvery(1, TimeUnit.SECONDS) 			
				.ignoring(NoSuchElementException.class);

		WebElement clickseleniumlink = wait.until(new Function<WebDriver, WebElement>(){

			public WebElement apply(WebDriver driver ) {
				if (element.isDisplayed()) {
					return element;
				}
				return element;
			}

		});

		return element;
	}

	protected String getPageTitle() {
		Logger.printInfo("Verifyng Page Title Name");
		return getDriver().getTitle();
	}

	protected String getSubTitle(WebElement e) {

		Logger.printInfo("getting subTitle");
		return e.getText();
	}

	protected boolean elementoPresente(By by) {

		try {
			Logger.printInfo("En el metodo ElementoPresente" +  by);
			if(getWait().until(ExpectedConditions.presenceOfElementLocated(by))!=null) {

				Logger.printInfo("el elemento Si esta" + by);

				return true;
			}
			else Logger.printInfo("el elemento no esta"); return false; 

		} catch (NoSuchElementException e) {
			Logger.printInfo("Elemento no encontrado" + e.getMessage());
			Logger.printError("Elemento no encontrado"+ e.getMessage());
			return false;


		}
		catch (Exception e) {
			Logger.printError("error:" + e.getMessage());
		}
		return false;

	}

	protected void modalHandle() {

		if(elementoPresente(By.cssSelector("div[class='modal-inner modal-dismiss']"))) {
			getDriver().findElement(By.cssSelector("#modalCloseButton")).click();
		}

	}

	protected LocalDate calcularFecha2(String cuando) {

		LocalDate fecha = LocalDate.now();
		System.out.println("fecha actual>" + fecha);

		switch (cuando) {


		case "Departure":
			fecha= fecha.plusMonths(2L);
			break;

		case "Returning":
			fecha= fecha.plusMonths(4L).plusDays(10);
			break;



		case "Departure2":
			fecha= fecha.plusMonths(2L);
			break;

		case "Returning2":
			fecha= fecha.plusMonths(2L).plusDays(13);
			break;

		case "Departure3":
			fecha= fecha.plusDays(1l);
			break;

		case "Returning3":
			fecha= fecha.plusDays(5l);
			break;


		case "Departure4a":
			fecha= fecha.plusDays(1l);
			break;

		case "Returning4a":
			fecha= fecha.plusDays(5l);
			break;

		case "Departure4b":
			fecha= fecha.plusDays(6l);
			break;

		case "Returning4b":
			fecha= fecha.plusDays(8l);
			break;

		case "Departure5":
			fecha= fecha.plusDays(60l);
			break;

		case "Returning5":
			fecha= fecha.plusDays(95l);
			break;

		default:
			break;
		}

		System.out.println("Fecha Deseada>"+fecha+"---");

		return fecha;
	}

	
	public void cambiarDePestaña(String parametro) {
		ArrayList<String> handles = new ArrayList<String>(getDriver().getWindowHandles());
		for (String data : handles) {
			getDriver().switchTo().window(data);
			if (getDriver().getTitle().contains(parametro)) {
				break;
			}
		}
	}

	public List<WebElement> obtainsList(List<WebElement> contenedor , String sb) {
		

		Logger.printInfo("buscando la lista");

		List<WebElement> list = new ArrayList<>();

		switch (sb) {

		case "ListaPreciosFH":

			List<WebElement> conte = getDriver().findElements(By.cssSelector("ul[class='hotel-price']"));

			conte.forEach(f -> {
				list.add(f.findElement(By.cssSelector("li[class^='actualPrice']")));

			});

			return list;

		case "HoraDepartureIni":
			return contenedor.stream().map(f-> f.findElement(By.cssSelector("span[data-test-id=\"departure-time\"]"))).collect(Collectors.toList());

		case "HoraDepartureFin":
			return contenedor.stream().map(f-> f.findElement(By.xpath("//span[@data-test-id=\"arrival-time\"]"))).collect(Collectors.toList());


		case "HoraReturnFin":
			return contenedor.stream().map(f-> f.findElement(By.cssSelector("span[data-test-id=\"arrival-time\"]"))).collect(Collectors.toList());


		case "verifyListahoras":
			getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(contenedor.get(0), By.cssSelector("span.duration-emphasis")));
			return contenedor.stream().map(f-> f.findElement(By.cssSelector("span.duration-emphasis"))).collect(Collectors.toList());
//			contenedor.forEach(f -> {
//				list.add(f.findElement(By.cssSelector("span.duration-emphasis")));
//			});
//			return list;

		case "listTripTotalPrice":

			contenedor.forEach(f -> {
				list.add(f.findElement(By.cssSelector("span[class='packagePriceTotal']")));
			});
			return list;

		case "listDepRetInfo":

			contenedor.forEach(f -> {
				list.add(f.findElement(By.xpath("//div[@class='toggle-inner']")));
			});
			return list;

		case "ListaResultados3Estrellas":
			return contenedor.stream().map(f-> f.findElement(By.cssSelector("strong[class='star-rating rating-secondary star rating'] span[class^='icon icon-stars']"))).collect(Collectors.toList());

		}

		return list;
	}

	//recibe dos listas ordena la segunda y las compara por posicion.
	protected  boolean equalLists(List<Integer> a, List<Integer> b){

		boolean bandera = false;

		if ((a.size() != b.size()) || (a == null && b!= null) || (a != null && b== null) || (a == null && b == null)){
			return false;
		}

		Collections.sort(b);

		for (int i = 0; i < a.size()-1; i++) {

			System.out.println("a["+ i + "]:"+ a.get(i));
			System.out.println("b["+ i + "]:"+ b.get(i));

			int valor= a.get(i).compareTo(b.get(i));

			System.out.println(valor);

			if(valor==0) {

				Logger.printInfo("lista ordenada" );
				bandera=true;
			}
			else {
				Logger.printInfo("Lista des Ordenada");
				bandera=false;
			}
		}

		System.out.println("finalmente la lista esta ordenada = "+ bandera);
		return bandera;

		//		return a.equals(b); // con esto comparo que esten todos los elementos sin importar el orden

	}

	public static Boolean waitForPageToBeLoaded(WebDriver driver, int seconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(expectation);
			return Boolean.TRUE;
		} catch (Throwable error) {
			return Boolean.FALSE;
		}
	}

}
