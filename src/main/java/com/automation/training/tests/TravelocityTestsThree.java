package com.automation.training.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class TravelocityTestsThree extends BaseTestTravel {

	@Test
	public void testTravelocity() {	

		travelocityHomePage = getTravelocityHomePage();
		
		travelocityHomePage.clickBtnHotels();
		travelocityHomePage.fillGoingTo();
		travelocityHomePage.selectDayForHotel();
		travelocityHomePage.selectRoomsHotel();
		travelocityHomePage.selectQuantityAdults("2");
		flightsSearchPage = travelocityHomePage.clickSearchBtnHotel();
		
		assertTrue(flightsSearchPage.findSponsoredSectionHotels(), "The system dooesn't display sponsored section");
		signInPage = flightsSearchPage.clickOnSponsoredSectionHotel();
		
		assertTrue(signInPage.verifySignInPage(), "The system be not in the Sign In page");
		

	}

}


