package com.automation.training;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

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
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
			
		default:
			break;
		}

		driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
