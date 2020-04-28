package com.automation.training.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.automation.training.pages.CheckOutPage;
import com.automation.training.pages.FlightInformationPage;
import com.automation.training.pages.FlightsSearchPage;
import com.automation.training.pages.TravelocityHomePage;

public class TravelocityTestsTwo extends BaseTestTravel {
	
	TravelocityHomePage travelocityHomePage;

	@Test
	public void testTravelocity() {	

		travelocityHomePage = getTravelocityHomePage();
		
		assertTrue(travelocityHomePage.clickVacationPackages(), "The bot can not click flight hotels button ");
		assertTrue(travelocityHomePage.verifyPageOpen(), "The page is not in Vacation Package / Flight Hotel");
		travelocityHomePage.seleccionarFromAndTo(travelocityHomePage.buscarElemento("Flying from FH"),"LAS");
		travelocityHomePage.seleccionarFromAndTo(travelocityHomePage.buscarElemento("Flying to FH"),"LAX");
		travelocityHomePage.seleccionarDepartureDate("Departure2");		
		travelocityHomePage.seleccionarDepartureDate("Returning2");
		travelocityHomePage.selectRooms();
		travelocityHomePage.selectQuantityAdults();
		travelocityHomePage.clickBtnSearch("FH");

		FlightsSearchPage flightsSearchPage = getFlights();
		
		assertTrue(flightsSearchPage.verifyLoaderVisible(), "The loader is visible");
		assertTrue(flightsSearchPage.verifySortOptionsFH(), "The Result flight page dont have sort options");
		assertTrue(flightsSearchPage.verifyInfoFlight(), "The flight information have some differences");
		assertTrue(flightsSearchPage.verifyTitle(),"The subTitle is diferent");
		assertTrue(flightsSearchPage.verifyResults(), "The page doesn't show Flight and Hotel results");
		assertTrue(flightsSearchPage.verifyResultSorteByPrice(), "The price is not sorted well");
		assertTrue(flightsSearchPage.selectFirstResultWith3Starts(), "The bot can not select a result at least 3 starts");

		FlightInformationPage flightInformationPage = getFlightsInformationPage();

		flightInformationPage.cambiarDePestaña("Book");	
//		assertTrue(flightInformationPage.verifyTripTotalPrice(), "The page dont contain total Price");
		assertTrue(flightInformationPage.verifyHotelSelected(), "The hotel founded is not the same");
		assertTrue(flightInformationPage.selectTheFirstRoomOption(), "The system doesn't display rooms");
		
		FlightsSearchPage flightsSearchPage2 = getFlights();
		
		flightsSearchPage2.loaderFlightSearchPage();
//		flightsSearchPage2.clickSelectButtonToLosAngeles();
		assertTrue(flightsSearchPage2.clickSelectButtonToLosAngeles(), "The system doesn't display tickets to Los Angeles");
		assertTrue(flightsSearchPage2.selectLasVegasTicket(), "The system doesn't display third or more tickets to Las Vegas");
		
		FlightInformationPage flightInformationPage2 = getFlightsInformationPage();
		flightInformationPage2.waitFlightInformation();
		assertTrue(flightInformationPage2.verifyDateDepartureReturnFlight_FH(), "Date Flight information has diferences");
		assertTrue(flightInformationPage2.verifyTimeDepartureReturnFlightFrom_FH(), "Departure Time flight information has diferences");
		assertTrue(flightInformationPage2.verifyTimeDepRetFlightTo_FH(), "Returning Time flight information has diferences");
		assertTrue(flightInformationPage2.verifyFlightFromTo_FH(), "The Origin and Destination has diferences");
		assertTrue(flightInformationPage2.verifyHotelName_FH(), "The hotel name has diferences");
		assertTrue(flightInformationPage2.verifyHotelCheckInDate_FH(), "The checkin date has diferences");
		assertTrue(flightInformationPage2.verifyTotalFlightDurationFROM_FH(), "The total time flight duration departure has diferences");
		assertTrue(flightInformationPage2.verifyTotalDurationFlightTO_FH(), "The total time flight duration return has diferences");
		flightInformationPage2.obtainTotalTripValue_FH();
		flightInformationPage2.clickBtnNextFinalDetails();
		
		CheckOutPage checkOutPage = getCheckOutpage();
		checkOutPage.waitCheckOutPage();
		assertTrue(checkOutPage.verifyHotelName_FH(), "The Hotel name has some diferences");
		assertTrue(checkOutPage.verifyDatesHotel_FH(),"The checkin/ checkOut dates has some diferences");
		assertTrue(checkOutPage.verifyDatesFlights_FH(), "The departure / return flight dates has some diferences");
		assertTrue(checkOutPage.verifyFromToFlight_FH(), "The origin and destination has some diferences");
		assertTrue(checkOutPage.verifyPrecioFinal_FH(),"The final price has some diferences");
		
		assertTrue(checkOutPage.verifyTitle(), "The page is not in checkOut page");
		assertTrue(checkOutPage.verifySubTitle_FH(), "The page has diferens in the subtitle");
		assertTrue(checkOutPage.verifyFirstName(), "The first name can not be filled");
		assertTrue(checkOutPage.verifyMiddleName(), "The middle name has problems");
		assertTrue(checkOutPage.verifyLastName(), "The last name has problems");
		assertTrue(checkOutPage.verifyPhoneNumber(), "The phoneNumber has problems");
		assertTrue(checkOutPage.verifyTickets_FH(), "The quantity of tickets is not the same");

	}

}


