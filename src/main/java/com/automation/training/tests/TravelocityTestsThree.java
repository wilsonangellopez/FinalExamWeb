package com.automation.training.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.automation.training.pages.CheckOutPage;
import com.automation.training.pages.FlightInformationPage;
import com.automation.training.pages.FlightsSearchPage;
import com.automation.training.pages.SignInPage;
import com.automation.training.pages.TravelocityHomePage;

public class TravelocityTestsThree extends BaseTestTravel {
	
	TravelocityHomePage travelocityHomePage;

	@Test
	public void testTravelocity() {	

		travelocityHomePage = getTravelocityHomePage();
		
		travelocityHomePage.clickBtnHotels_H();
		travelocityHomePage.fillGoingTo_H();
		travelocityHomePage.seleccionarDepartureDate("Departure3");		
		travelocityHomePage.seleccionarDepartureDate("Returning3");
		travelocityHomePage.selectRooms();
		travelocityHomePage.selectQuantityAdults();
		travelocityHomePage.clickSearchBtn_H();
		
		FlightsSearchPage flightsSearchPage = getFlights();
		assertTrue(flightsSearchPage.findSponsoredSection_H(), "The system dooesn't display sponsored section");
		
		SignInPage signInPage = getSignInPage();
		assertTrue(signInPage.verifySignInPage(), "The system be not in the Sign In page");
		

	}

}


