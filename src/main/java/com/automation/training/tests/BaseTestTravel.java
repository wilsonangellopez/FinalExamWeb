package com.automation.training.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.automation.training.MyDriver;
import com.automation.training.pages.CheckOutPage;
import com.automation.training.pages.FlightInformationPage;
import com.automation.training.pages.FlightsSearchPage;
import com.automation.training.pages.SignInPage;
import com.automation.training.pages.TravelocityHomePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseTestTravel {

	MyDriver myDriver;
	private TravelocityHomePage travelocityHomePage;
	private FlightsSearchPage flightsSearchPage;
	private FlightInformationPage flightInformationPage;
	private CheckOutPage checkOutPage;
	private SignInPage signInPage;

//	@Before
	@BeforeClass(alwaysRun= true)
	@Parameters({"browser"})	
	public void beforeSuite(String browser) {
		myDriver = new MyDriver(browser);
		travelocityHomePage = new TravelocityHomePage(myDriver.getDriver());
	}

//	@After
	@AfterClass(alwaysRun=true)
	public void afterSuite() {
//		travelocityHomePage.dispose();
	}


	public TravelocityHomePage getTravelocityHomePage() {
		return travelocityHomePage;
	}

	public FlightsSearchPage getFlights() {
		flightsSearchPage = new FlightsSearchPage(myDriver.getDriver());
		return flightsSearchPage;
	}
	
	public FlightInformationPage getFlightsInformationPage() {
		 flightInformationPage = new FlightInformationPage(myDriver.getDriver());
		return flightInformationPage;
	}
	
	public CheckOutPage getCheckOutpage() {
		checkOutPage = new CheckOutPage(myDriver.getDriver());
		return checkOutPage;
	}
	
	public SignInPage getSignInPage() {
		signInPage = new SignInPage(myDriver.getDriver());
		return signInPage;
	}

//	public void setUp() {
//		// TODO Auto-generated method stub
//		
//	}

}
