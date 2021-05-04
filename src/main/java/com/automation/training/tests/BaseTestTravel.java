package com.automation.training.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.automation.training.MyDriver;
import com.automation.training.pages.CheckOutPage;
import com.automation.training.pages.FlightInformationPage;
import com.automation.training.pages.FlightsSearchPage;
import com.automation.training.pages.SignInPage;
import com.automation.training.pages.TravelocityHomePage;

public abstract class BaseTestTravel {

	MyDriver myDriver;
	protected TravelocityHomePage travelocityHomePage;
	protected FlightsSearchPage flightsSearchPage;
	protected FlightInformationPage flightInformationPage;
	protected CheckOutPage checkOutPage;
	protected SignInPage signInPage;


	@BeforeClass(alwaysRun= true)
	@Parameters({"browser"})	
	public void beforeSuite(String browser) {
		myDriver = new MyDriver(browser);
		travelocityHomePage = new TravelocityHomePage(myDriver.getDriver());
	}

	@AfterClass(alwaysRun=true)
	public void afterSuite() {
		//travelocityHomePage.dispose();
	}
	
	public TravelocityHomePage getTravelocityHomePage() {
		return travelocityHomePage;
	}

	
	


}
