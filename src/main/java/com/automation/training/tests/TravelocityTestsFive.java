package com.automation.training.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class TravelocityTestsFive extends BaseTestTravel {
	
	@Test
	public void testTravelocity() {	

		travelocityHomePage = getTravelocityHomePage();
		travelocityHomePage.clickBtnCruises();
		assertTrue(travelocityHomePage.clickDropDownList(), "Europe is not present in the list");

		travelocityHomePage.selectDayForCruise();
		flightsSearchPage = travelocityHomePage.pressEnterInCruise();

		flightsSearchPage.waitCruisesPage();
		assertTrue(flightsSearchPage.verifyLoaderVisible(), "The loader is visible");
		assertTrue(flightsSearchPage.verifyFilterRadioButton(), "The system doesn't has radio option 10 - 14 nights ");
		
		assertTrue(flightsSearchPage.verifyCruisesWithDiscountsAndWithOut(), "The system doesn't has cruise in the specific dates");
		flightInformationPage = flightsSearchPage.selectCruiseWithMoreDiscount();
		
		flightInformationPage.changeTab("Cruise | Travelocity");	
		assertTrue(flightInformationPage.verifyCruiseSelected(), "The cruise founded is not the same");
		
	}

}


