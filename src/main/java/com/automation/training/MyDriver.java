package com.automation.training;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyDriver {
	
	private WebDriver driver;

	public MyDriver(String browser) {
		
		switch (browser) {
		
		case "remoteFireFox":
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wb/hub"), DesiredCapabilities.firefox());
				
			} 
			catch (MalformedURLException mal) {
				mal.getMessage();
				mal.printStackTrace();
			}
			
			break;
		case "firefox":
			driver= new FirefoxDriver();
			break;
			
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver80.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			break;
			

		default:
			break;
		}
	
	}
	
	public WebDriver getDriver() {
		
		return this.driver;
		
	}

}
