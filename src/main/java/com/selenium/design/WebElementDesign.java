package com.selenium.design;

import org.openqa.selenium.WebElement;


public interface WebElementDesign {

	/**
	 * Clicks on the specified web element (like a button or link) and takes a snapshot.
	 * 
	 * @param element The WebElement to be clicked.
	 * @throws StaleElementReferenceException if the element is no longer attached to the DOM.
	 */
	void click(WebElement element);

	/**
	 * Retrieves the visible text from the specified web element.
	 * 
	 * @param element The WebElement from which to get the text.
	 * @return The visible text of the element.
	 */
	String getElementText(WebElement element);

	/**
	 * Verifies that the exact text of the specified web element matches the expected text.
	 * 
	 * @param element The WebElement whose text will be checked.
	 * @param expectedText The text expected to be present in the element.
	 * @return True if the text matches exactly, false otherwise.
	 */
	boolean verifyExactText(WebElement element, String expectedText);
	
	/**
	 * This method will verify exact given attribute's value with actual value on the given element
	 * @param element   - The WebElement in which the attribute value to be need to be verified
	 * @param attribute  - The attribute to be checked (like value, href etc)
	 * @param value  - The value of the attribute
	 * @see locateElement method in Browser Class
	 * @return true if this String represents the same sequence of characters as the specified value, false otherwise
	 */
	 boolean verifyExactAttribute(WebElement element, String attribute, String value);

	/**
	 * Verifies that a partial attribute value of the specified web element matches the expected value.
	 * 
	 * @param element The WebElement whose attribute will be checked.
	 * @param attribute The attribute to check (like "value" or "href").
	 * @param value The expected value of the attribute.
	 * @return True if the attribute value contains the expected value, false otherwise.
	 */
	boolean verifyPartialAttribute(WebElement element, String attribute, String value);

	/**
	 * Verifies that the specified web element is visible on the web page.
	 * 
	 * @param element The WebElement to be checked.
	 * @return True if the element is visible, false otherwise.
	 */
	boolean verifyDisplayed(WebElement element);
}
