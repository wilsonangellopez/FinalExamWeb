package com.automation.training.pages;

import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {

	public SignInPage(WebDriver pDriver) {
		super(pDriver);
	}

	public boolean verifySignInPage() {
		
		boolean isSignInPage= getPageTitle().equalsIgnoreCase("Sign In");
		return isSignInPage;
	}

}
