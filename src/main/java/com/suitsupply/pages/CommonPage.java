package com.suitsupply.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.java.base.JavaBase;
import com.selenium.base.SeleniumBaseImpl;
import com.selenium.design.Locators;
import com.suitsupply.constants.ApplicationPageTitles;
import com.testng.base.SuitSupplySpecificMethods;

public class CommonPage extends SuitSupplySpecificMethods {

	public CommonPage(WebDriver driver) {
		this.driver = driver;
	}

	private String hamburgerMenuButton = "span[data-testid='menu-btn']";
	private String menuLevelsSideKick = "div[slot='replaceText']";
	private String productMainCategoryToChoose = "li[data-category='men-nav-replaceText'] button";
	private String productSubCategoryToChoose = "li[automation-key-category-id='replaceText']";

	private String hamburgerNavigation = "button[aria-label='Toggle navigation']";
	private String navSection = "nav.header-nav";
	private String productMainCategoryToChoose2 = "button[data-category='men-nav-replaceText']"; // clothing
	private String productSubMenu = "div.nav__submenu";
	private String productSubCategoryMenu = "//div[@automation-key-category-id='replaceText']/..//div[contains(@class,'nav__subcategory')]";
	private String productSubCategoryToChoose2 = "div[automation-key-category-id='replaceText'] button"; // jackets-classic
	private String actualProductToChoose = "div[automation-key-category-id='replaceText'] a";
	// Locators to use - xPath
	private String filterAndSortButton = "//button[normalize-space(text())='Filter & Sort']";

	// Locators to use - cssSelector
	private String filterAndSortSideKick = "filters-wrapper[panel-title='Filter & Sort']";

	private String stickyButtonSection = "ss-side-sticky.sticky-buttons";
	private String applyButtonRoot = "ss-button.automation-key-apply-button";

	private String filterAndSortOptionElement = "//ss-accordion-item[@label='replaceText']"; // Sort by

	private String sortByAccordionLabelRoot = "ss-link[part='accordion-label']";

	private String filterOrSortOptionButton = "button[automation-key-filter-id='replaceText']"; // Sort by

	private String sortPriceBy = "ss-input-wrap[label='replaceText']"; // Price High-Low
	private String colorGrid = "//ss-accordion-item[@label='Color']//ss-grid";
	private String colorGridOptions = "ss-card";

	public boolean verifyPageTitle(String title) {
		return verifyTitle(title);
	}

	public void openMenuSideKick() {
		click(locateElement(Locators.CSS, hamburgerMenuButton));
		waitUntilVisibilityOfElement(
				locateElement(Locators.CSS, JavaBase.dynamicLocatorGenerator(menuLevelsSideKick, "level1")));
	}

	public void openMenuSideKick2() {
		click(locateElement(Locators.CSS, hamburgerNavigation));
		waitUntilVisibilityOfElement(locateElement(Locators.CSS, navSection));
	}

	public void navigateToProductMainCategorySection(String mainCategoryProduct) {// shoes
		click(locateElement(Locators.CSS,
				JavaBase.dynamicLocatorGenerator(productMainCategoryToChoose, mainCategoryProduct)));
		waitUntilVisibilityOfElement(
				locateElement(Locators.CSS, JavaBase.dynamicLocatorGenerator(menuLevelsSideKick, "level2")));
	}

	public void navigateToProductMainCategorySection2(String mainCategoryProduct) {
		click(locateElement(Locators.CSS,
				JavaBase.dynamicLocatorGenerator(productMainCategoryToChoose2, mainCategoryProduct)));
		waitUntilVisibilityOfElement(locateElement(Locators.CSS, productSubMenu));
	}

	public void navigateToProductSubCategorySection(String subCategoryProduct) {// sneakers
		click(locateElement(Locators.CSS,
				JavaBase.dynamicLocatorGenerator(productSubCategoryToChoose, subCategoryProduct)));
		waitUntilTitleContains(ApplicationPageTitles.SNEAKERS_PAGE_TITLE.getTitle());
	}

	public void navigateToProductSubCategorySection2(String subCategoryProduct, String actualCategoryToChoose) {
		click(locateElement(Locators.CSS,
				JavaBase.dynamicLocatorGenerator(productSubCategoryToChoose2, subCategoryProduct)));
		waitUntilVisibilityOfElement(locateElement(Locators.XPATH,
				JavaBase.dynamicLocatorGenerator(productSubCategoryMenu, subCategoryProduct)));
		click(locateElement(Locators.CSS, JavaBase.dynamicLocatorGenerator(actualProductToChoose, actualCategoryToChoose)));
		waitUntilTitleContains(ApplicationPageTitles.JACKETS_PAGE_TITLE.getTitle());
	}

	public void openTheProductPage(String mainCategoryProduct, String subCategoryProduct) {
		openMenuSideKick();
		navigateToProductMainCategorySection(mainCategoryProduct);
		navigateToProductSubCategorySection(subCategoryProduct);
	}

	public void openTheProductPage2(String mainCategoryProduct, String subCategoryProduct, String actualProductCategory) {
		openMenuSideKick2();
		navigateToProductMainCategorySection2(mainCategoryProduct);
		navigateToProductSubCategorySection2(subCategoryProduct, actualProductCategory);
	}

	public void clickOnFilterAndSort() {
		click(locateElement(Locators.XPATH, filterAndSortButton));
		waitUntilVisibilityOfElement(locateElement(Locators.CSS, filterAndSortSideKick));
	}

	public WebElement getApplyFilterAndSortWebElement() {
		waitUntilVisibilityOfElement(locateElement(Locators.CSS, filterAndSortSideKick));
		SearchContext filterAndSortRoot = getShadowRootFromWebElement(
				locateElement(Locators.CSS, filterAndSortSideKick));
		SearchContext stickyButtonSectionRoot = getShadowRootFromAnotherSearchContext(filterAndSortRoot,
				stickyButtonSection);
		return locateElement(stickyButtonSectionRoot, applyButtonRoot);
	}

	public boolean isApplyButtonDisabled() {
		return isAttributePresentInElement(getApplyFilterAndSortWebElement(), "disabled");
	}

	public void expandFilterOrSortOption(String filterOrSortOptionValue) {
		SearchContext filterOrSortOptionRoot = getShadowRootFromWebElement(locateElement(Locators.XPATH,
				JavaBase.dynamicLocatorGenerator(filterAndSortOptionElement, filterOrSortOptionValue)));

		SearchContext filterOrSortLabelRoot = getShadowRootFromAnotherSearchContext(filterOrSortOptionRoot,
				sortByAccordionLabelRoot);

		click(locateElement(filterOrSortLabelRoot,
				JavaBase.dynamicLocatorGenerator(filterOrSortOptionButton, filterOrSortOptionValue)));
	}

	public void sortPriceBy(String priceByValue) {
		click(locateElement(Locators.CSS, JavaBase.dynamicLocatorGenerator(sortPriceBy, priceByValue)));
	}
	
	public String getAvailableColorCount() {
		return getApplyFilterAndSortWebElement().getAttribute("aria-label").replace("[^\\d.]", "");
	}

	public void applyFilterAndSortOptions() {
		click(getApplyFilterAndSortWebElement());
	}

	public void sortByPrice(String filterOrSortingOption, String filterOrSortBy) {
		expandFilterOrSortOption(filterOrSortingOption);
		sortPriceBy(filterOrSortBy);
		getWait().until(ExpectedConditions.invisibilityOf(locateElement(Locators.CSS,"div.loader")));
		waitUntilElementIsClickable(getApplyFilterAndSortWebElement());
	}

	public void sortByColor(String filterOrSortingOption) {
		expandFilterOrSortOption(filterOrSortingOption);
		sortColorBy();
		JavaBase.sleep(500);
	}

	public void sortColorBy() {
		WebElement colorGridElement = locateElement(Locators.XPATH, colorGrid);
		List<WebElement> listOfColorsElements = locateElements(colorGridElement, colorGridOptions);
		int randomColorNumber = JavaBase.randomNumberGenerator(listOfColorsElements.size());
		String filterByColor = listOfColorsElements.get(randomColorNumber).getAttribute("filter-value-id");
		logInfo(filterByColor + " color is chosen");
		waitUntilElementIsClickable(listOfColorsElements.get(randomColorNumber));
		click(listOfColorsElements.get(randomColorNumber));
		//getWait().until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.loader"))));
	}

}
