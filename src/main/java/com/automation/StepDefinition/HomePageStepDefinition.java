package com.automation.StepDefinition;


import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.testng.annotations.Parameters;

import com.automation.training.pages.*;
import com.automation.training.tests.BaseTestTravel;
import com.automation.training.utils.Logger;

public class HomePageStepDefinition extends BaseTestTravel {
	
	private TravelocityHomePage travelocityHomepage;
	

	@Given("Open travelocity")
	public void OpenTravelocity(String a) {
		Logger.printInfo("In step definition" + a);
	}
	
	@When("^hola$")
	public void hola() throws Throwable {
		Logger.printInfo("Hi");
	}

	@Then("^fin del escenario$")
	public void fin_del_escenario() throws Throwable {
		Logger.printInfo("End scenario");
	}

}
