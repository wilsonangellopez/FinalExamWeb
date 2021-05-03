package com.automation.training.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class TravelocityTestsFour extends BaseTestTravel {

	@Test
	public void testTravelocity() {	

		travelocityHomePage = getTravelocityHomePage();
		
		assertTrue(travelocityHomePage.clickVacationPackages(), "The bot can not click flight hotels button ");
		assertTrue(travelocityHomePage.verifyPageOpen(), "The page is not in Vacation Package / Flight Hotel");
		travelocityHomePage.selectFromAndTo(travelocityHomePage.findElement("Flying from FH"),"LAS");
		travelocityHomePage.selectFromAndTo(travelocityHomePage.findElement("Flying to FH"),"LAX");
		travelocityHomePage.selectDayForFlightPartial();
		travelocityHomePage.selectRoomsFlightHotel();
		travelocityHomePage.selectQuantityAdults("2");
		travelocityHomePage.selectCheckBoxPartStay();
		travelocityHomePage.selectDayForHotelPartial();
		
		travelocityHomePage.clickBtnSearch(false);
		
		assertFalse(travelocityHomePage.verifyErrorDisplayFH(), "Your partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates.");

	}

}


