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

	@FindBy(css="button[data-icon='tool-close']")
	private WebElement btnCloseBaggageFees;

	private String progressBar="div.uitk-loading-bar-current";

	@FindBy(css="button[data-test-id='select-button']")
	private WebElement btnContinueDeparture;

	@FindBy(css="img[class=large-logo]")
	private WebElement logo;

	@FindBy(css="button[data-test-id='select-link']")
	private List<WebElement> listSelectBtns;

	@FindBy(css="button[data-test-id='select-link']:nth-child(3)")
	private List<WebElement> listBtnTickets;


	@FindBy(css="div[data-test-id='journey-duration']")
	private List<WebElement> listDuration;



	public FlightsSearchPage(WebDriver driver) {		
		super(driver);
	}

	/**
	 * Method to select the dropdown
	 */
	public boolean clickDropdownByValue(WebElement element, String data) {

		Logger.printInfo("In dropdown list + From page" + getDriver().getTitle());

		waitLoaderDisAppear(progressBar);
		waitForElementToBeClickable(element);
		waitForElementToBeClickable(dropDownLastelement);

		if((isPresent(sortBy)!=null)
				&& (sortBy.isEnabled())) {


			Select drpSort = clickDropDown(data, sortBy);
			return getTextInDropDownSelected(drpSort,data);
		}
		return false;

	}

	/**
	 * Method to select the dropdown 
	 * @param data
	 * @return the element selecteed
	 */
	public Select clickDropDown(String data,WebElement element) {

		waitForElementToBeClickable(element);

		Select drpSort = new Select(element);
		waitForElementToBeClickable(dropDownLastelement);
		drpSort.selectByValue(data);

		return drpSort;
	}

	/**
	 * Method to obtain text from the dropdown selected
	 * @param drpSort, data
	 * @return text selectd
	 */
	public boolean getTextInDropDownSelected(Select drpSort, String data) {

		waitForElementToBeClickable(dropDownLastelement);

		String optionSelected= drpSort.getFirstSelectedOption().getAttribute("value").toString();
		return optionSelected.equalsIgnoreCase(data)?true:false;

	}

	/**
	 * Method to return the FlightInformationPage 
	 */
	public FlightInformationPage goToFlightInformation() {

		return new FlightInformationPage(getDriver());
	}


	/**
	 * Method to count and validate the ticket options and flight durations are the same quantity 
	 */
	public boolean searchResultsAndFlightDuration() {


		waitLoaderDisAppear(progressBar);
		waitForElementToBeClickable(listFieldSet.get(listFieldSet.size()-1));
		waitForPageToBeLoaded(getDriver(), 20);

		int quantityContainers= (int) listFieldSet.stream().count();
		int quantityFlightDuration= (int) listFlightDuration.stream().count();
		Logger.printInfo("Flights containers count: " + quantityContainers);
		Logger.printInfo("Flights duration count: " + quantityFlightDuration);

		return (quantityContainers==quantityFlightDuration)?true:false;
	}


	/**
	 * Method to count and validate the tickets options and baggage fees are the same quantity 
	 */
	public boolean searchResultsAndDetailsAndBags() {

		int quantityDetailsAndBags=0;

		Logger.printInfo("------ searchResultsAndDetailsAndBags ---------");
		waitForPresenceOfAllElementsLocatedByCss(lstFieldSet);

		int quantityContainers= (int) listFieldSet.stream().count();
		quantityDetailsAndBags= clickFielSetAndClose(listFieldSet);

		if(quantityContainers==quantityDetailsAndBags) { 
			return true;
		}
		else {
			return false;
		}

	}

	/**
	 * Method to count the baggage fees
	 */
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

	/**
	 * Method to close the detail trip window
	 */
	private void closeBaggageFees(WebElement btnBaggageClose) {

		waitForElementToBeClickable(btnBaggageClose);
		btnBaggageClose.click();

	}

	/**
	 * Method to counts baggage fees
	 */
	private int countBaggageFees(String baggageFees) {

		return (getDriver().findElement(By.cssSelector(baggageFees))!=null)?countBaggageFees++:countBaggageFees--;
	}

	/**
	 * Method to validate the time is sorted 
	 */
	public boolean verifyTimeIsSorted() {

		Logger.printInfo("In method SelectFromSortList");

		waitLoaderDisAppear(progressBar);
		waitForPageToBeLoaded(getDriver(), 20);
		waitForPresenceOfAllElementsLocatedByCss("button[data-test-id='select-link']:nth-child(3)");
		waitForElementToBeClickable(listBtnTickets.get(listBtnTickets.size()-1));


		List<String> duration = listDuration.stream().map(x-> x.getText()).collect(Collectors.toList());
		List<Integer> listNumbers = getCleanList(duration);
		List<Integer> listNumbersCopy = new ArrayList<>(listNumbers);

		return  compareListsLowToHight(listNumbers, listNumbersCopy);

	}

	/**
	 * Method to clean the list and calculate the time of the flight in minuts
	 * @return a list with the flight duration in minuts
	 */
	private List<Integer> getCleanList(List<String> duration) {

		List<Integer> listNumbers = new ArrayList<>();

		for (String hours : duration) {

			Logger.printInfo("List hours not sorted: " + hours);

			String[] vectorTime= hours.split(" ");

			vectorTime[0]= vectorTime[0].replace("h", "");
			Integer time= Integer.parseInt(vectorTime[0])*60;

			vectorTime[1]=vectorTime[1].replace("m", "");
			listNumbers.add(time+Integer.parseInt(vectorTime[1]));

		}
		return listNumbers;
	}


	/**
	 * Method to validate if are available tickets by position
	 */
	public boolean verifyTicketExistGeneral(String position, boolean isLAS) {


		int pos = Integer.parseInt(position)-1;

		waitLoaderDisAppear(progressBar);
		waitForPresenceOfAllElementsLocatedByCss("li[data-test-id='offer-listing']");
		waitForElementToBeClickable(listFieldSet.get(listFieldSet.size()-1));
		waitForPageToBeLoaded(getDriver(), 30);

		if(isLAS 
				&& (isPresent(logo)!=null) 
				&& (getDriver().getTitle().contains("LAS to LAX "))) {

			return verifyTicketExistInList(listFieldSet, pos);
		}

		if(!isLAS 
				&& (getDriver().getTitle().contains("LAS to LAX "))) { 

			return verifyTicketExistInList(listFieldSet, pos);
		}
		return false;
	}


	/**
	 * Method to select a ticket by position send by "the user"
	 */
	public boolean selectTicketByPosition(String position) {

		waitLoaderDisAppear(progressBar);		
		waitForPresenceOfAllElementsLocatedByCss("button[data-test-id='select-link']");

		if((isPresent(logo)!=null)) {
			waitForElementToBeClickable(dropDownLastelement);	
			waitForElementToBeClickable(listSelectBtns.get(listSelectBtns.size()-1));


			int pos = Integer.parseInt(position)-1;

			boolean isSelectedTicket = selectTicket(listSelectBtns,pos);

			click(btnContinueDeparture);

			return isSelectedTicket;
		}
		return false;

	}


	/**
	 * Method to select an option from a list of  web elements 
	 */
	private boolean selectTicket(List<WebElement> listSelect, int position){

		try {
			waitForElementToBeClickable(listSelect.get(listSelect.size()-1));
			listSelect.get(position).click();
		} catch (IndexOutOfBoundsException e) {
			Logger.printInfo("The system doesn't display a ticket");

			return false;
		}

		return true;
	}

	/**
	 * Verify the list of tickets has elements in a position
	 */
	private boolean verifyTicketExistInList(List<WebElement> list, int position) {

		try {
			getWait().until(ExpectedConditions.elementToBeClickable(list.get(position)));
		} catch (IndexOutOfBoundsException e) {
			Logger.printInfo("The system doesn't display a ticket in the position: " + position);
			return false;
		}
		return true;
	}

	/**
	 * Method to select a dropdown by value 
	 */
	public boolean selectDropDownSort(String data) {

		return clickDropdownByValue(sortBy, data);
	}



}

