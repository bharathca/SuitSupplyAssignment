package com.suitsupply.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.java.base.JavaBase;
import com.selenium.design.Locators;
import com.testng.base.SuitSupplySpecificMethods;

public class ProductsPage extends SuitSupplySpecificMethods {

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	//xpath
	private String listOfProducts = "//div[@data-automation-product-type='regular']";
	// xpath
	private String firstProductElement = "//div[@class='product-tile']//a";
	// css

	private String selectSizes = "div.add-to-cart-button-section button";
	//css	
	private String productSizeSideKick = "div.product-sizes";
	// xpath
	private String addToBag = "//button[normalize-space(text())='Add to bag']";

	private String productNameElement = "h1.product-name";

	private String availableSizes = "div[data-automation-key-size-enabled='true']";

	private String miniCartSnackBarContainer = "div[class='panel-notification js-panel-notification is-active']";
	private String viewCart = "a.panel-notification__link";
	private String sizeSection = "div.size-passport sp-customer-sizes";
	private String sizeSectionContent = "sp-customer-sizes-content";
	private String sizePicker = "sp-size-picker";
	private String sizeSelector = "ss-size-selector";
	private String availableJacketSize = "div.btn-container:not(:has(.btn-marker)) ss-button[class]:not([class='disabled'])";

	private Map<String, String> productDetails;

	public int getNumberOfProducts() {
		return getElementSize(locateElements(Locators.XPATH, listOfProducts));
	}

	public void openFirstProductListed() {
		click(locateElements(Locators.XPATH, listOfProducts).get(0).findElement(By.xpath(firstProductElement)));
	}

	public void selectSizesButton() {
		click(locateElement(Locators.CSS, selectSizes));
		JavaBase.sleep(500);
//		waitUntilVisibilityOfElement(locateElement(Locators.CSS, productSizeSideKick));
	}
	
	public String openFirstProductAndClickOnSelectSize() {
	openFirstProductListed();
	String productName = getProductName();
	selectSizesButton();
	return productName;
	}

	public boolean isAddToBagButtonDisabled() {
		return isAttributePresentInElement(locateElement(Locators.XPATH, addToBag), "disabled");
	}

	public String getProductName() {
		return getElementText(locateElement(Locators.CSS, productNameElement));
	}

	public String selectRandomSize() {
		List<WebElement> allAvailableSizes = locateElements(Locators.CSS, availableSizes);
		int randomNumber = JavaBase.randomNumberGenerator(allAvailableSizes.size());

		WebElement randomShoeSizeElement = allAvailableSizes.get(randomNumber).findElement(By.tagName("button"));

		String randomSizeChosen = getElementText(randomShoeSizeElement);

		waitUntilVisibilityOfElement(randomShoeSizeElement);
		waitUntilElementIsClickable(randomShoeSizeElement);
		randomShoeSizeElement.click();
		return randomSizeChosen;
	}

	public void addToBag() {
		waitUntilElementIsClickable(locateElement(Locators.XPATH, addToBag));
		click(locateElement(Locators.XPATH, addToBag));
	}
	
	public void navigateToCartPage() {
		waitUntilVisibilityOfElement(locateElement(Locators.CSS, miniCartSnackBarContainer));
		waitUntilElementIsClickable(locateElement(Locators.CSS, viewCart));
		click(locateElement(Locators.CSS, viewCart));
	}
	
	public void addToBagAndNavigateToCartPage() {
		addToBag();
		navigateToCartPage();
	}
	
	public void selectJacketSize() {
		SearchContext sizeSectionHost = getShadowRootFromWebElement(locateElement(Locators.CSS, sizeSection));

		SearchContext sizeSectionContentHost = getShadowRootFromAnotherSearchContext(sizeSectionHost, sizeSectionContent);
				
		SearchContext sizePickerHost = getShadowRootFromAnotherSearchContext(sizeSectionContentHost, sizePicker);
				
		SearchContext sizeSelectorHost = getShadowRootFromAnotherSearchContext(sizePickerHost, sizeSelector);

		
		List<WebElement> jacketSizeList = locateElements(sizeSelectorHost, availableJacketSize) ;
		int randomJacketNumber = JavaBase.randomNumberGenerator(jacketSizeList.size());

		 String sizeJacket = jacketSizeList.get(randomJacketNumber).getAttribute("aria-label");
		 click(jacketSizeList.get(randomJacketNumber));
	}
	
	

}
