package com.suitsupply.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.selenium.design.Locators;
import com.testng.base.SuitSupplySpecificMethods;

public class CartPage extends SuitSupplySpecificMethods{

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	private String getCartQuanity = "span.minicart-quantity";
	private String cardsInCart = "div.cart__products-list div.product-card";
	private String crossIcon = "div.cart__products-list div.product-card span.product-card__remove-icon";
	private String removeInfoSnackbar = "div[class='notification js-notification is-active']";
	private String removeInfoMessageSnackbar = "span.notification__text-content";
	private String emptyCartInfo = "h1.cart__empty-title";
	
	
	
	public int getCartQuantity() {
		return Integer.valueOf(getElementText(locateElement(Locators.CSS, getCartQuanity)));
	}
	
	public int getCardInCart() {
		return locateElements(Locators.CSS, cardsInCart).size();
	}
	
	public void removeProductsFromCart() {
		List<WebElement> numberOfProductsToRemoveElement = locateElements(Locators.CSS, crossIcon);
		for (WebElement removeProduct : numberOfProductsToRemoveElement) {
			removeProduct.click();
			waitUntilVisibilityOfElement(locateElement(Locators.CSS, removeInfoSnackbar));
			getWait().until(ExpectedConditions.textToBePresentInElement(locateElement(Locators.CSS, removeInfoMessageSnackbar), "Removed from bag"));
		}
	}
	
	public boolean isEmptyCartDisplayed() {
		return verifyDisplayed(locateElement(Locators.CSS, emptyCartInfo));
	}
}
