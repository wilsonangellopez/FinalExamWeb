package com.automation.training.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.automation.training.pages.CheckOutPage;
import com.automation.training.pages.FlightInformationPage;
import com.automation.training.pages.FlightsSearchPage;
import com.automation.training.pages.TravelocityHomePage;

public class TravelocityTestsFour extends BaseTestTravel {
	
	TravelocityHomePage travelocityHomePage;

	@Test
	public void testTravelocity() {	

		travelocityHomePage = getTravelocityHomePage();
		
		assertTrue(travelocityHomePage.clickVacationPackages(), "The bot can not click flight hotels button ");
		assertTrue(travelocityHomePage.verifyPageOpen(), "The page is not in Vacation Package / Flight Hotel");
		travelocityHomePage.seleccionarFromAndTo(travelocityHomePage.buscarElemento("Flying from FH"),"LAS");
		travelocityHomePage.seleccionarFromAndTo(travelocityHomePage.buscarElemento("Flying to FH"),"LAX");
		travelocityHomePage.seleccionarDepartureDate("Departure4a");		
		travelocityHomePage.seleccionarDepartureDate("Returning4a");
		travelocityHomePage.selectRooms();
		travelocityHomePage.selectQuantityAdults();
		travelocityHomePage.selectCheckBox_E();
		travelocityHomePage.seleccionarDepartureDate("Departure4b");		
		travelocityHomePage.seleccionarDepartureDate("Returning4b");
		
		travelocityHomePage.clickBtnSearch("FH");
		travelocityHomePage.verifyErrorDisplay_E();

	}

}


