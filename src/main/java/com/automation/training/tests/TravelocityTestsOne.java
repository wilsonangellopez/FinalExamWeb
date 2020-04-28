package com.automation.training.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.automation.training.pages.CheckOutPage;
import com.automation.training.pages.FlightInformationPage;
import com.automation.training.pages.FlightsSearchPage;
import com.automation.training.pages.TravelocityHomePage;

public class TravelocityTestsOne extends BaseTestTravel {
	
	TravelocityHomePage travelocityHomePage;

	@Test
	public void testTravelocity() {	

		travelocityHomePage = getTravelocityHomePage();

		travelocityHomePage.clickFlights();
		travelocityHomePage.clickRoundTrip();
		travelocityHomePage.seleccionarFromAndTo(travelocityHomePage.buscarElemento("Flying from"),"LAS");
		travelocityHomePage.seleccionarFromAndTo(travelocityHomePage.buscarElemento("Flying to"),"LAX");
		travelocityHomePage.seleccionarDepartureDate("Departure");		
		travelocityHomePage.seleccionarDepartureDate("Returning");
		travelocityHomePage.clickBtnSearch("H");
		
		FlightsSearchPage flightsSearchPage = getFlights();

		assertTrue(flightsSearchPage.searchSorty(), "The Result flight page dont have sort button");
		assertTrue(flightsSearchPage.searchResultsAndSelectBtn(), "The results dont have select buttons");
		assertTrue(flightsSearchPage.searchResultsAndFlightDuration(), "The results dont have the flight duration");
		assertTrue(flightsSearchPage.searchResultsAndDetailsAndBags(), "The results dont have the Flight details and Bagges feees");
		
		
		assertTrue(flightsSearchPage.selectFromSortList(),"The List isn't in order");
		assertTrue(flightsSearchPage.clickSelectButtonToLosAngeles(), "The system doesn't display tickets to Los Angeles");
		assertTrue(flightsSearchPage.selectLasVegasTicket(), "The system doesn't display third or more tickets to Las Vegas");
		
		FlightInformationPage flightInformationPage = getFlightsInformationPage();
		flightInformationPage.cambiarDePestaña("Trip Detail | Travelocity");
		
		assertTrue(flightInformationPage.verifyTripTotalPrice(), "The page dont contain total Price");
		assertTrue(flightInformationPage.verifyDepartureReturnInfo(), "Flight information have diferences");
		assertTrue(flightInformationPage.verifyPriceGuaranteeText(),"The page dont contain the text price guarante");
		flightInformationPage.selectBtnContinueBooking();
		
		CheckOutPage checkOutpage = getCheckOutpage();
		assertTrue(checkOutpage.verifyTitle(), "The system is not in checkOut Page or Who is traveling page");
		assertTrue(checkOutpage.verifySubTitle(), "The page is not in the Checkout page");
		assertTrue(checkOutpage.verifyFirstName(), "The first name has problems");
		assertTrue(checkOutpage.verifyMiddleName(), "The middle name has problems");
		assertTrue(checkOutpage.verifyLastName(), "The last name has problems");
		assertTrue(checkOutpage.verifyPhoneNumber(), "The phoneNumber has problems");
		assertTrue(checkOutpage.verifyTotalPriceTrip(), "The price is diferent");
		
	}

}


