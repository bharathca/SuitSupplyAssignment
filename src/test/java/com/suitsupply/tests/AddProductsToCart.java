package com.suitsupply.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.java.base.JavaBase;
import com.suitsupply.constants.ApplicationPageTitles;

public class AddProductsToCart extends BaseTest {

	@Test
	public void testAddProductToCart() {
		homePage.acceptCookies();
		Assert.assertTrue(commonPage.verifyPageTitle(ApplicationPageTitles.HOME_PAGE_TITLE.getTitle()),
				"Home Page title is incorrect");
		commonPage.openTheProductPage("shoes", "sneakers");
		commonPage.clickOnFilterAndSort();
		Assert.assertTrue(commonPage.isApplyButtonDisabled(),
				"Apply button is enabled even before choosing the filter or sorting of products");
		commonPage.sortByPrice("Sort by", "Price High-Low");
		Assert.assertFalse(commonPage.isApplyButtonDisabled(),
				"Apply button is not enabled even after choosing the filter or sorting of products");
		commonPage.applyFilterAndSortOptions();
		String productName = sneakersPage.openFirstProductAndClickOnSelectSize();
		Assert.assertTrue(sneakersPage.isAddToBagButtonDisabled(),
				"Add to bag button is enabled even before choosing the size of products");
		sneakersPage.selectRandomSize();
		JavaBase.sleep(500);
		Assert.assertFalse(sneakersPage.isAddToBagButtonDisabled(),
				"Add to bag button is not enabled even after choosing the size of products");
		sneakersPage.addToBagAndNavigateToCartPage();
		int currentCartQuantity = cartPage.getCartQuantity();
		Assert.assertEquals(currentCartQuantity,1,"Product is not added to the cart. Or mismatch with the count of the products");
		
		
		commonPage.openTheProductPage2("clothing", "jackets", "jackets-classic");
		commonPage.clickOnFilterAndSort();
		Assert.assertTrue(commonPage.isApplyButtonDisabled(),
				"Apply button is enabled even before choosing the filter or sorting of products");
		commonPage.sortByColor("Color");
		Assert.assertFalse(commonPage.isApplyButtonDisabled(),
				"Apply button is not enabled even after choosing the filter or sorting of products");
		int colorCount = Integer.valueOf(commonPage.getAvailableColorCount().replaceAll("[^\\d.]", ""));
		commonPage.applyFilterAndSortOptions();
		
		String productName2 = sneakersPage.openFirstProductAndClickOnSelectSize();
		Assert.assertTrue(sneakersPage.isAddToBagButtonDisabled(),
				"Add to bag button is enabled even before choosing the size of products");
		sneakersPage.selectJacketSize();
		Assert.assertFalse(sneakersPage.isAddToBagButtonDisabled(),
				"Add to bag button is not enabled even after choosing the size of products");
		sneakersPage.addToBagAndNavigateToCartPage();
		currentCartQuantity = cartPage.getCartQuantity();
		
		Assert.assertEquals(currentCartQuantity,2,"Product is not added to the cart. Or mismatch with the count of the products");
		Assert.assertEquals(cartPage.getCardInCart(),2,"Product is not added to the cart. Or mismatch with the count of the products");
		
		cartPage.removeProductsFromCart();
		Assert.assertTrue(cartPage.isEmptyCartDisplayed(), "Products are not removed");
		
	}

}
