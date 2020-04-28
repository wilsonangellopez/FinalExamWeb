package com.automation.training.tests;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import com.automation.training.pages.CheckOutPage;
import com.automation.training.pages.FlightInformationPage;
import com.automation.training.pages.FlightsSearchPage;
import com.automation.training.pages.TravelocityHomePage;

public class TravelocityTestsFive extends BaseTestTravel {
	
	TravelocityHomePage travelocityHomePage;

	@Test
	public void testTravelocity() {	

		travelocityHomePage = getTravelocityHomePage();
		travelocityHomePage.clickBtnCruises();
		assertTrue(travelocityHomePage.clickDropDownList(), "Europe is not present in the list");

		travelocityHomePage.seleccionarDepartureDate("Departure5");		
		travelocityHomePage.seleccionarDepartureDate("Returning5");
		travelocityHomePage.presionarEnter();

		FlightsSearchPage flightsSearchPage = getFlights();
		
		assertTrue(flightsSearchPage.verifyLoaderVisible(), "The loader is visible");
		assertTrue(flightsSearchPage.verifyFilterRadioButton_C(), "The system doesn't has radio option 10 - 14 nights ");
		assertTrue(flightsSearchPage.verifyCruisesWithDiscountsAndWithOut_C(), "The system doesn't has cruise in the specific dates");
		flightsSearchPage.selectCruiseWithMoreDiscount_C();
		
		FlightInformationPage flightInformationPage = getFlightsInformationPage();

		flightInformationPage.cambiarDePestaña("Cruise | Travelocity");	
		assertTrue(flightInformationPage.verifyCruiseSelected(), "The cruise founded is not the same");
		
	}

}


