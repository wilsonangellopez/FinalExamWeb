package com.automation.training.tests;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TravelocityTestsTwo extends BaseTestTravel {
	

	@Test
	@Parameters({"from", "to", "name", "middleName", "lastName", "phoneNumber", "firstResult","thirdResult"})
	public void testTravelocity(String from, 
			String to, 
			String name,
			String middleName,
			String lastName,
			String phoneNumber,
			String firstResult,
			String thirdResult) {

		travelocityHomePage = getTravelocityHomePage();
		
		assertTrue(travelocityHomePage.clickVacationPackages(), "The bot can not click flight hotels button ");
		assertTrue(travelocityHomePage.verifyPageOpen(), "The page is not in Vacation Package / Flight Hotel");
		travelocityHomePage.selectFromAndTo(travelocityHomePage.findElement("Flying from FH"),from);
		travelocityHomePage.selectFromAndTo(travelocityHomePage.findElement("Flying to FH"),to);
		travelocityHomePage.selectDayForFlightHotel();
		assertFalse(travelocityHomePage.verifyLongerTrip(), "Your length of stay cannot be longer than n nights.");
		travelocityHomePage.selectRoomsFlightHotel();
		travelocityHomePage.selectQuantityAdults("2");
		flightsSearchPage= travelocityHomePage.clickBtnSearch(false);
		
		assertTrue(flightsSearchPage.verifyLoaderVisible(), "The loader is visible");
		assertTrue(flightsSearchPage.verifySortOptionsFlightHotel(), "The Result flight page dont have sort options");
		assertTrue(flightsSearchPage.verifyInfoFlight(), "The flight information have some differences");
		assertTrue(flightsSearchPage.verifyTitleFlightHotel(),"The subTitle is diferent");
		assertTrue(flightsSearchPage.verifyResultsFlightHotel(), "The page doesn't show Flight and Hotel results");
		
		assertTrue(flightsSearchPage.verifyResultSorteByPrice(), "The price is not sorted well");
		
		int position = flightsSearchPage.obtainPositionOf3StarsHotel();
		flightsSearchPage.obtain3StartsHotelInfo(position);	
		flightInformationPage = flightsSearchPage.selectFirstResultWith3Starts(position);
		flightInformationPage.changeTab("Getting");
		flightInformationPage.changeTab("Book");
		

		assertTrue(flightInformationPage.verifyHotelNameSelected(), "The hotel name founded is not the same by the user choice");
		assertTrue(flightInformationPage.verifyHotelStarsSelected(), "The hotel by stars founded is not the same by the user choice");
		assertTrue(flightInformationPage.verifyHotelValueSelected(), "The hotel value founded is not the same by the user choice");
		
		flightsSearchPage = flightInformationPage.selectTheFirstRoomOption();
		
		flightsSearchPage.loaderFlightSearchPage();
		assertTrue(flightsSearchPage.verifyTicketExistGeneral(firstResult, true),"The ticket is not Displayed");
		flightsSearchPage.getInfoFlight(firstResult, true);
		assertTrue(flightsSearchPage.selectTicketGeneral(firstResult),"The button select is not available");
		flightsSearchPage.selectFaresBtnGeneral(firstResult, true);
		
		assertTrue(flightsSearchPage.verifyTicketExistGeneral(thirdResult, false),"The ticket is not Displayed");
		flightsSearchPage.getInfoFlight(thirdResult, false);
		assertTrue(flightsSearchPage.selectTicketGeneral(thirdResult),"The button select is not available");
		flightInformationPage = flightsSearchPage.selectFaresBtnGeneral(thirdResult, false);
	
		flightInformationPage.waitFlightInformation();
		assertTrue(flightInformationPage.verifyDateDepartureArrivalFH(), "Date Flight information has diferences");
		assertTrue(flightInformationPage.verifyTimeDepartureArrivalFromFH(), "Departure Time flight information has diferences");
		assertTrue(flightInformationPage.verifyTimeDepartureArrivalFlightTo(), "Returning Time flight information has diferences");
		assertTrue(flightInformationPage.verifyPlaceFlightFromTo(), "The Origin and Destination has diferences");
		assertTrue(flightInformationPage.verifyHotelNameFH(), "The hotel name has diferences");
		assertTrue(flightInformationPage.verifyHotelCheckInOutDate(), "The checkin date has diferences");
		assertTrue(flightInformationPage.verifyTotalFlightDurationFrom(), "The total time flight duration departure has diferences");
		assertTrue(flightInformationPage.verifyTotalDurationFlightTo(), "The total time flight duration return has diferences");
		flightInformationPage.obtainTotalTripValueFH();
		
		checkOutPage = flightInformationPage.clickBtnNextFinalDetails();
		
		checkOutPage.waitCheckOutPage();
		assertTrue(checkOutPage.verifyHotelName(), "The Hotel name has some diferences");
		assertTrue(checkOutPage.verifyDatesHotel(),"The checkin/ checkOut dates has some diferences");
		assertTrue(checkOutPage.verifyDatesFlights(), "The departure / return flight dates has some diferences");
		assertTrue(checkOutPage.verifyFromToFlight(), "The origin and destination has some diferences");
		assertTrue(checkOutPage.verifyFinalPriceFH(),"The final price has some diferences");
		
		assertThat(checkOutPage.getPageTitleCheckOutPage(), CoreMatchers.is("Travelocity: Payment"));
		assertTrue(checkOutPage.verifySubTitleFlightHotel(), "The page has diferens in the subtitle");
		assertThat(checkOutPage.getFirstName(name), CoreMatchers.is(name));
		assertThat(checkOutPage.getMiddleName(middleName), CoreMatchers.is(middleName));
		assertThat(checkOutPage.getLastName(lastName), CoreMatchers.is(lastName));
		assertThat(checkOutPage.getPhoneNumber(phoneNumber), CoreMatchers.is(phoneNumber));

	}

}


