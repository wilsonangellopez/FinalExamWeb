package com.automation.training.tests;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.training.pages.FlightInformationPage;


public class TravelocityTestsOne extends BaseTestTravel {


	@Test(priority=1) 
	@Parameters({"from", "to", "name", "middleName", "lastName", "phoneNumber", "firstResult", "thirdResult", "OriginFlight" ,"DestinationFlight"})	
	public void testTravelocityFromToAndDataPicker(String from,
			String to, 
			String name, 
			String middleName,
			String lastName,
			String phoneNumber,
			String firstResult,
			String thirdResult,
			String OriginFlight,
			String DestinationFlight) {	


		travelocityHomePage.clickFlights();
		travelocityHomePage.clickRoundTrip();
		travelocityHomePage.selectFromAndTo(from);
		travelocityHomePage.selectFromAndTo(to);

		travelocityHomePage.selectDateForFlight(from,2);
		travelocityHomePage.selectDateForFlight(to,3);
		travelocityHomePage.clickDoneBtnInCalendar();
		flightsSearchPage= travelocityHomePage.clickSearchButton();

	}

	@Test(dataProvider="dataP", priority=2)
	public void test2ADropDownSorting(String dataP) {	
		
		flightsSearchPage= travelocityHomePage.reDirect();
		assertTrue(flightsSearchPage.selectDropDownSort(dataP), "The option doesn't have information");
	}
	
	@Test(priority=3)
	public void test2CFlightDurationIsPresentOnEveryResult() {	
		
		flightsSearchPage= travelocityHomePage.reDirect();
		assertTrue(flightsSearchPage.searchResultsAndFlightDuration(), "The results don't have the flight duration");
	}
	
	@Test(priority=4, dataProvider="dataShort")
	public void test2DsearchResultsAndDetailsAndBags(String dataShort) {
		
		flightsSearchPage= travelocityHomePage.reDirect();
		
		assertTrue(flightsSearchPage.selectDropDownSort(dataShort), "The option doesn't have information");
		assertTrue(flightsSearchPage.searchResultsAndDetailsAndBags(), "The results dont have the Flight details and Bagges feees");
		
	}
	
	@Test(priority=5,dataProvider="dataShort")
	public void test3SortingByDurationShorter(String dataShort) {	
		
		flightsSearchPage= travelocityHomePage.reDirect();
		assertTrue(flightsSearchPage.selectDropDownSort(dataShort), "The option doesn't have information");
		assertTrue(flightsSearchPage.verifyTimeIsSorted(),"The List isn't in order from low to hight");
	}
	
	@Test(priority=6)
	@Parameters({"from", "to","firstResult","thirdResult", "name","middleName", "lastName", "phoneNumber","DURATION_INCREASING"})
	public void test4SelectFirstResult(String from,
			String to,
			String firstResult,
			String thirdResult,
			String name, 
			String middleName,
			String lastName,
			String phoneNumber,
			String durationIncreasing) {	
		
		flightsSearchPage= travelocityHomePage.reDirect();
		
		assertTrue(flightsSearchPage.selectDropDownSort(durationIncreasing), "The option doesn't have information");
		assertTrue(flightsSearchPage.verifyTimeIsSorted(),"The List isn't in order from low to hight");
		
		assertTrue(flightsSearchPage.verifyTicketExistGeneral(firstResult, true),"The ticket is not Displayed");
		assertTrue(flightsSearchPage.selectTicketByPosition(firstResult),"The button select is not available");
	
		
	}
	
	@Test(priority=7)
	@Parameters({"from", "to","firstResult","thirdResult", "name","middleName", "lastName", "phoneNumber","DURATION_INCREASING"})
	public void test5SelectThirdResult(String from,
			String to,
			String firstResult,
			String thirdResult,
			String name, 
			String middleName,
			String lastName,
			String phoneNumber,
			String durationIncreasing) {		
		
		flightsSearchPage= travelocityHomePage.reDirect();
		assertTrue(flightsSearchPage.selectDropDownSort(durationIncreasing), "The option doesn't have information");
		assertTrue(flightsSearchPage.verifyTimeIsSorted(),"The List isn't in order from low to hight");
		
		assertTrue(flightsSearchPage.verifyTicketExistGeneral(firstResult, true),"The ticket is not Displayed");
		assertTrue(flightsSearchPage.selectTicketByPosition(firstResult),"The button select is not available");
		assertTrue(flightsSearchPage.selectTicketByPosition(thirdResult),"The button select is not available");
	
		
	}
	
	
	@Test(priority=8)
	@Parameters({"from", "to","firstResult","thirdResult", "name","middleName", "lastName", "phoneNumber","DURATION_INCREASING"})
	public void test6VerifyTripDetails(String from,
			String to,
			String firstResult,
			String thirdResult,
			String name, 
			String middleName,
			String lastName,
			String phoneNumber,
			String durationIncreasing) {	
		
		flightsSearchPage= travelocityHomePage.reDirect();
		assertTrue(flightsSearchPage.selectDropDownSort(durationIncreasing), "The option doesn't have information");
		assertTrue(flightsSearchPage.verifyTimeIsSorted(),"The List isn't in order from low to hight");
		
		assertTrue(flightsSearchPage.verifyTicketExistGeneral(firstResult, true),"The ticket is not Displayed");
		assertTrue(flightsSearchPage.selectTicketByPosition(firstResult),"The button select is not available");
		assertTrue(flightsSearchPage.selectTicketByPosition(thirdResult),"The button select is not available");
		
		flightInformationPage = flightsSearchPage.goToFlightInformation();
		
		flightInformationPage.changeTab("Flight details | Travelocity");
		assertTrue(flightInformationPage.getTotalPrice(), "The page doesn't display ticket price");
		assertTrue(flightInformationPage.getPlaceDepartureReturning(from), "The system doesn't display Departure info");
		assertTrue(flightInformationPage.getPlaceDepartureReturning(to), "The system doesn't display Return info");
		
	}
	
	
	@Test(priority=9)
	@Parameters({"from", "to","firstResult","thirdResult", "name","middleName", "lastName", "phoneNumber","DURATION_INCREASING"})
	public void test7_8(String from,
			String to,
			String firstResult,
			String thirdResult,
			String name, 
			String middleName,
			String lastName,
			String phoneNumber,
			String durationIncreasing) {	
		
		flightsSearchPage= travelocityHomePage.reDirect();
		assertTrue(flightsSearchPage.selectDropDownSort(durationIncreasing), "The option doesn't have information");
		assertTrue(flightsSearchPage.verifyTimeIsSorted(),"The List isn't in order from low to hight");
		
		assertTrue(flightsSearchPage.verifyTicketExistGeneral(firstResult, true),"The ticket is not Displayed");
		assertTrue(flightsSearchPage.selectTicketByPosition(firstResult),"The button select is not available");
		assertTrue(flightsSearchPage.selectTicketByPosition(thirdResult),"The button select is not available");
		
		flightInformationPage = flightsSearchPage.goToFlightInformation();
		
		flightInformationPage.changeTab("Flight details | Travelocity");
		assertTrue(flightInformationPage.getTotalPrice(), "The page doesn't display ticket price");
		assertTrue(flightInformationPage.getPlaceDepartureReturning(from), "The system doesn't display Departure info");
		assertTrue(flightInformationPage.getPlaceDepartureReturning(to), "The system doesn't display Return info");
		flightInformationPage.getTimeDepartureReturning(from);
		flightInformationPage.getTimeDepartureReturning(to);
		flightInformationPage.getDateDepartureReturning(from);
		flightInformationPage.getDateDepartureReturning(to);
		
		
		checkOutPage= flightInformationPage.selectBtnContinueBooking();
		
		checkOutPage.waitCheckOutPage();
		assertThat(checkOutPage.getPageTitleCheckOutPage(), CoreMatchers.is("Travelocity: Payment"));
		assertThat(checkOutPage.getSubTitle(), CoreMatchers.is("Who's traveling?"));
		assertThat(checkOutPage.getFirstName(name), CoreMatchers.is(name));
		assertThat(checkOutPage.getMiddleName(middleName), CoreMatchers.is(middleName));
		assertThat(checkOutPage.getLastName(lastName), CoreMatchers.is(lastName));
		assertThat(checkOutPage.getPhoneNumber(phoneNumber), CoreMatchers.is(phoneNumber));
		assertTrue(checkOutPage.verifyTotalPriceTrip(),"The price is not the same that the user selects");
		assertTrue(checkOutPage.verifyFromToFlight(from),"The system not load the same information for departure");
		assertTrue(checkOutPage.verifyFromToFlight(to),"The system not load the same information for returning");
		assertTrue(checkOutPage.verifyTimeFlight(from), "The system not load the same information for time departure");
		assertTrue(checkOutPage.verifyTimeFlight(to), "The system not load the same information for time returning");
		assertTrue(checkOutPage.verifyDatesFlights(from), "The system not load the same information for date departure");
		assertTrue(checkOutPage.verifyDatesFlights(to), "The system not load the same information for date returning");
		
	}
	
	

	@DataProvider(name="dataP")
	public Object[] dataProvider() {
		Object[] dataP= new Object[4];
		dataP[0]="DURATION_INCREASING";
		dataP[1]="PRICE_INCREASING";
		dataP[2]="DEPARTURE_INCREASING";
		dataP[3]="ARRIVAL_INCREASING";
		

		return dataP;
	}	
	
	@DataProvider(name="dataShort")
	public Object[] dataProviderShortes() {
		Object[] dataShort= new Object[1];
		dataShort[0]="DURATION_INCREASING";
		
		return dataShort;
	}	


}


