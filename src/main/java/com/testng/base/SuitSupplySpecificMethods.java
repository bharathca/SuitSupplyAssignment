package com.testng.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;

import com.selenium.base.SeleniumBaseImpl;
import com.selenium.design.Browsers;

public class SuitSupplySpecificMethods extends SeleniumBaseImpl {

	protected WebDriver driver;

	@BeforeMethod
	public void preCondition(@Optional("CHROME") String browser) {
		startApplication(Browsers.valueOf((browser)), false, "https://suitsupply.com/en-nl/");
		this.driver = getWebDriver();
	}

	@AfterMethod
	public void postCondition() {
		closeDriver();
	}
}
