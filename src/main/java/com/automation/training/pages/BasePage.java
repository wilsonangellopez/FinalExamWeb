package com.automation.training.pages;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.training.utils.Logger;
import com.google.common.base.Function;

import cucumber.api.Scenario;

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
		Logger.printInfo("Click: " + webElement);
	}

	protected void waitForElementToBeClickable(WebElement element) {

		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void click(String element) {

		getWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector(element)));
		getDriver().findElement(By.cssSelector(element)).click();
		Logger.printInfo("Click: " + element );

	}

	protected void writing(WebElement webElement, String orgDes) {

		getWait().until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.sendKeys(orgDes);
		Logger.printInfo("Writing: "+ orgDes);
	}

	protected void clearInputText(WebElement webElement) {

		getWait().until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.sendKeys(Keys.CLEAR);
		webElement.sendKeys(" ");
	}

	protected void pressEnter(WebElement webElement) {

		getWait().until(ExpectedConditions.elementToBeClickable(webElement));		
		webElement.sendKeys(Keys.ENTER);
		Logger.printInfo("Press Enter");
	}

	protected void pressEscape(WebElement webElement) {

		getWait().until(ExpectedConditions.elementToBeClickable(webElement));		
		webElement.sendKeys(Keys.ESCAPE);
		Logger.printInfo("Press Escape");
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

	protected void scrollToElement(WebElement element) {

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
			// meter un wait tradicional de elementos implicito

		} catch (StaleElementReferenceException e) {
			Logger.printError("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			Logger.printError("Element " + element + " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			Logger.printError("Element " + element + " was not clickable " + e.getStackTrace());
		}
	}

	protected String getPageTitle() {

		waitForPageToBeLoaded(getDriver(), 10);
		Logger.printInfo("Verifyng Page Title Name");
		return getDriver().getTitle();
	}

	protected String getText(WebElement element) {

		if(isPresent(element)!=null) {
			Logger.printInfo("getting subTitle");
			return element.getText();
		} else {
			return null;
		}
	}

	protected boolean elementoPresente(By by) {

		try {
			Logger.printInfo("In method > ElementoPresente" +  by);
			if(getWait().until(ExpectedConditions.presenceOfElementLocated(by))!=null) {

				Logger.printInfo("The element is present" + by);

				return true;
			}
			else Logger.printInfo("The element is not present"); return false; 

		} catch (NoSuchElementException e) {
			Logger.printInfo("Element not found" + e.getMessage());
			Logger.printError("Element not found "+ e.getMessage());
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

	protected void modalInFlightSearch() {

		if(elementoPresente(By.cssSelector("#xSellHotelForcedChoice div.modal-inner"))) {
			getDriver().findElement(By.cssSelector("a#forcedChoiceNoThanks.link")).click();
		}
	}

	public void changeTab(String parametro) {

		ArrayList<String> handles = new ArrayList<String>(getDriver().getWindowHandles());
		for (String data : handles) {
			getDriver().switchTo().window(data);
			if (getDriver().getTitle().contains(parametro)) {
				break;
			}
		}
	}

	protected List<WebElement> obtainsList(List<WebElement> contenedor, By selector) {
		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(contenedor.get(0), selector));
		return contenedor.stream().map(f-> f.findElement(selector)).collect(Collectors.toList());
	}


	protected void waitForPresenceOfAllElementsLocatedByCss(String selector) {
		getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(selector)));
		//getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[data-test-id='offer-listing']")));
	}

	protected void clickByElementJS(WebDriver driver, WebElement element) throws InterruptedException {
		Actions act = new Actions(driver);
		getWait().until(ExpectedConditions.elementToBeClickable(element));
		//		Thread.sleep(500);
		// aqui manejar un wait del elemento tradicional 
		act.moveToElement(element).click().build().perform();
		Thread.sleep(500);

	}

	protected void clickByElementJS(WebDriver driver, String element) throws InterruptedException {


		Actions act = new Actions(driver);
		getWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector(element)));

		WebElement elem = getDriver().findElement(By.cssSelector(element));
		//		Thread.sleep(500);
		// aqui manejar un wait del elemento tradicional 
		act.moveToElement(elem).click().build().perform();
		Thread.sleep(500);

	}


	protected  boolean compareListsLowToHight(List<Integer> a, List<Integer> b){

		boolean isSorted = false;

		if ((a.size() != b.size()) || (a == null && b!= null) || (a != null && b== null) || (a == null && b == null)){
			return false;
		}

		Collections.sort(b);

		for (int i = 0; i < a.size()-1; i++) {


			Logger.printInfo("a["+ i + "]:"+ a.get(i));
			Logger.printInfo("b["+ i + "]:"+ b.get(i));

			int valor= a.get(i).compareTo(b.get(i));

			Logger.printInfo("IF value is cero is same position  " + valor);

			if(valor==0) {

				Logger.printInfo("The list is sorted" );
				isSorted=true;
			}
			else {
				Logger.printInfo("The list is NOT sorted");
				isSorted=false;
			}
		}

		Logger.printInfo("The list is sorted ? "+ isSorted);
		return isSorted;

	}

	protected static Boolean waitForPageToBeLoaded(WebDriver driver, int seconds) {
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

	protected WebElement findElementByText(String text) {

		switch (text) {

		case "Flying from FH":

			text= "Flying from";

			Logger.printInfo("search element with word " + text);

			By locatorFrom = By.xpath(String.format("//span[text()=\"%s\"]//following-sibling::input[@aria-autocomplete='list' and not(contains(@class,'disabled')) ]", text));
			List<WebElement> listFrom = getDriver().findElements(locatorFrom);
			WebElement element= listFrom.get(3);
			Logger.printInfo("WebElement is: " + element.toString());

			return element;

		case "Flying to FH":

			text= "Flying to";

			Logger.printInfo("search element with word " + text);

			By locatorTo = By.xpath(String.format("//span[text()=\"%s\"]//following-sibling::input[@aria-autocomplete='list' and not(contains(@class,'disabled')) ]", text));
			List<WebElement> listTo = getDriver().findElements(locatorTo);
			WebElement elemento= listTo.get(2);
			Logger.printInfo("WebElement is: " + elemento.toString());

			return elemento;

		default:
			break;
		}


		Logger.printInfo("search element with word " + text);

		By locator = By.xpath(String.format("//span[text()=\"%s\"]//following-sibling::input[@aria-autocomplete='list' and not(contains(@class,'disabled')) ]", text));
		List<WebElement> list = getDriver().findElements(locator);
		Optional<WebElement> e = list.stream().findFirst();
		Logger.printInfo("WebElement is : " + e.toString());

		return e.get();

	}

	protected void modalAppear(WebElement modal) {

		int count=0;
		do {
			isPresent(modal);
			count++;
		}while(count>3);


		if(isPresent(modal)!=null && modal.isDisplayed()) {

			getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.modal-body a[class='btn btn-secondary btn-sub-action book-button modal-button'][role='button']")));
			getDriver().findElement(By.cssSelector("div.modal-body a[class='btn btn-secondary btn-sub-action book-button modal-button'][role='button']")).click();
		}
	}

	protected void waitFlightInformationPageGenral(WebElement element) {

		if( element.isDisplayed()){
			getWait().until(ExpectedConditions.invisibilityOf(element));
		}

	}

	protected boolean childElementIsPresent(WebElement webElement, By childLocator) {
		try {
			webElement.findElement(childLocator);
		} catch (StaleElementReferenceException e) {
			return false;
		} catch (NoSuchElementException n) {
			return false;
		}
		return true;
	}

	public void waitLoaderDisAppear(String loader) {

		getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(loader)));
	}
}
