package com.automation.StepDefinition;


import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.testng.annotations.Parameters;

import com.automation.training.pages.*;
import com.automation.training.tests.BaseTestTravel;

//
//import cucumber.api.java.en.Given;
//
public class HomePageStepDefinition extends BaseTestTravel {
	
	private TravelocityHomePage travelocityHomepage;
	

//	@Given("^Open travelocity$")
	@Given("Open travelocity")
	public void OpenTravelocity(String a) {
		System.out.println("llego al step definition" + a);
	}
	
	@When("^hola$")
	public void hola() throws Throwable {
		System.out.println("en hola");
	}

	@Then("^fin del escenario$")
	public void fin_del_escenario() throws Throwable {
		System.out.println("fin del escenario");
	}

}
