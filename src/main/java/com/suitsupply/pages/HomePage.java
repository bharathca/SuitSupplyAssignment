package com.suitsupply.pages;

import org.openqa.selenium.WebDriver;

import com.selenium.design.Locators;
import com.testng.base.SuitSupplySpecificMethods;

public class HomePage extends SuitSupplySpecificMethods {

	private String acceptCookie = "button#onetrust-accept-btn-handler";
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void acceptCookies() {
		click(locateElement(Locators.CSS, acceptCookie));
	}

}
