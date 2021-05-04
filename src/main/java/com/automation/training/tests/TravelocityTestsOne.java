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
		//travelocityHomePage.selectFromAndTo(travelocityHomePage.findElement("Flying from"),OriginFlight);
		//travelocityHomePage.selectFromAndTo(travelocityHomePage.findElement("Flying to"),DestinationFlight);

		travelocityHomePage.selectDateForFlight("Departure",2);
		travelocityHomePage.selectDateForFlight("Return",3);
		travelocityHomePage.clickDoneBtnInCalendar();
		flightsSearchPage= travelocityHomePage.clickSearchButton();

		//flightsSearchPage = travelocityHomePage.clickBtnSearch(true);

		//assertTrue(flightsSearchPage.selectDrpDurationShortest(), "The Result flight page dont have sort button"); metodo actualizado
		assertTrue(flightsSearchPage.searchResultsAndSelectBtn(), "The results dont have select buttons");
		assertTrue(flightsSearchPage.searchResultsAndFlightDuration(), "The results dont have the flight duration");
		assertTrue(flightsSearchPage.searchResultsAndDetailsAndBags(), "The results dont have the Flight details and Bagges feees");

		assertTrue(flightsSearchPage.verifyFlightFromToVsSelected(from, to), "The system display differents in Origin / Destination tickets");
		assertTrue(flightsSearchPage.verifyTimeIsSorted(),"The List isn't in order from low to hight");

		assertTrue(flightsSearchPage.verifyTicketExistGeneral(firstResult, true),"The ticket is not Displayed");
		flightsSearchPage.getInfoFlight(firstResult, true);
		assertTrue(flightsSearchPage.selectTicketGeneral(firstResult),"The button select is not available");
		flightsSearchPage.selectFaresBtnGeneral(firstResult, true);

		assertTrue(flightsSearchPage.verifyTicketExistGeneral(thirdResult, false),"The ticket is not Displayed");
		flightsSearchPage.getInfoFlight(thirdResult, false);
		assertTrue(flightsSearchPage.selectTicketGeneral(thirdResult),"The button select is not available");
		flightInformationPage = flightsSearchPage.selectFaresBtnGeneral(thirdResult, false);

		flightInformationPage.changeTab("Trip Detail | Travelocity");

		assertTrue(flightInformationPage.verifyTripTotalPrice(), "The page dont contain total Price");
		assertTrue(flightInformationPage.verifyDepartureReturnInfoFlight(), "Flight information have diferences");
		assertTrue(flightInformationPage.verifyPriceGuaranteeText(),"The page dont contain the text price guarante");
		checkOutPage= flightInformationPage.selectBtnContinueBooking();


		assertThat(checkOutPage.getPageTitleCheckOutPage(), CoreMatchers.is("Travelocity: Payment"));
		assertThat(checkOutPage.getSubTitle(), CoreMatchers.is("Who's traveling?"));
		assertThat(checkOutPage.getFirstName(name), CoreMatchers.is(name));
		assertThat(checkOutPage.getMiddleName(middleName), CoreMatchers.is(middleName));
		assertThat(checkOutPage.getLastName(lastName), CoreMatchers.is(lastName));
		assertThat(checkOutPage.getPhoneNumber(phoneNumber), CoreMatchers.is(phoneNumber));
		assertTrue(checkOutPage.verifyTotalPriceTrip(), "The price is diferent");/**/

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
		
		travelocityHomePage.clickFlights();
		travelocityHomePage.clickRoundTrip();
		travelocityHomePage.selectFromAndTo("Las");
		travelocityHomePage.selectFromAndTo("Lax");

		travelocityHomePage.selectDateForFlight("Departure",2);
		travelocityHomePage.selectDateForFlight("Return",3);
		travelocityHomePage.clickDoneBtnInCalendar();
		flightsSearchPage= travelocityHomePage.clickSearchButton();
		
		assertTrue(flightsSearchPage.selectDropDownSort(dataShort), "The option doesn't have information");
		assertTrue(flightsSearchPage.searchResultsAndDetailsAndBags(), "The results dont have the Flight details and Bagges feees");
		
	}
	
	@Test(priority=2)
	public void test3SortingByDurationShorter() {	
		
		flightsSearchPage= travelocityHomePage.reDirect();
		assertTrue(flightsSearchPage.selectDropDownSort("DURATION_INCREASING"), "The option doesn't have information");
		assertTrue(flightsSearchPage.verifyTimeIsSorted(),"The List isn't in order from low to hight");
		
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


