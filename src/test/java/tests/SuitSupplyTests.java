package tests;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuitSupplyTests {

	public static void main(String[] args) {
		int randomNumber;
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://suitsupply.com/en-nl/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Accept Cookies
		driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();

		// Click on the menu to open the side kick
		WebElement hamburgerMenu = driver.findElement(By.cssSelector("span[data-testid='menu-btn']"));
		hamburgerMenu.click();

		// wait for the menu side kick level 1 to open
		WebElement menuLevel1SideKick = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[slot='level1']"))));

		// click on the shoes link
		WebElement shoesLink = driver.findElement(By.cssSelector("li[data-category='men-nav-shoes'] button"));
		shoesLink.click();

		// wait for the menu side kick level 2 to open
		WebElement menuLevel2SideKick = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[slot='level2']"))));

		// click on the sneakers link
		WebElement sneakersLink = driver.findElement(By.cssSelector("li[automation-key-category-id='sneakers']"));
		sneakersLink.click();

		// Wait for the sneakers page to load
		wait.until(ExpectedConditions.titleContains("Men's Sneakers - Leather, Suede, High Tops, Low Sneakers"));
		// extra validation on the title
		if (driver.getTitle().trim()
				.equals("Men's Sneakers - Leather, Suede, High Tops, Low Sneakers | SUITSUPPLY The Netherlands")) {
			System.out.println("In the Sneakers Page");
		} else {
			System.out.println("Navigation to Sneakers Page is failed");
		}

		WebElement filterAndSortButton = wait.until(ExpectedConditions.refreshed(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//button[normalize-space(text())='Filter & Sort']")))));
		filterAndSortButton.click();

		WebElement filterSideKick = wait.until(ExpectedConditions.refreshed(ExpectedConditions
				.visibilityOf(driver.findElement(By.cssSelector("filters-wrapper[panel-title='Filter & Sort']")))));
		SearchContext filterSideKickRoot = filterSideKick.getShadowRoot();

		SearchContext stickyButtonSectionRoot = filterSideKickRoot
				.findElement(By.cssSelector("ss-side-sticky.sticky-buttons")).getShadowRoot();
		WebElement applyButton = stickyButtonSectionRoot
				.findElement(By.cssSelector("ss-button.automation-key-apply-button"));
		SearchContext applyButtonRoot = applyButton.getShadowRoot();

		if (applyButton.getAttribute("disabled").equals("true")) {
			System.out.println("Apply Button is disabled before choosing the filter");
		} else {
			System.out.println("Apply Button is enabled before choosing the filter - Should not be the case");
		}

		WebElement sortByAccordion = driver.findElement(By.xpath("//ss-accordion-item[@label='Sort by']"));

		SearchContext sortByAccordionRoot = sortByAccordion.getShadowRoot();

		SearchContext sortByAccordionLabelRoot = sortByAccordionRoot
				.findElement(By.cssSelector("ss-link[part='accordion-label']")).getShadowRoot();
		WebElement sortByAccordionButton = sortByAccordionLabelRoot
				.findElement(By.cssSelector("button[automation-key-filter-id='Sort by']"));

		sortByAccordionButton.click();

		WebElement sortByPriceHighToLow = driver.findElement(By.cssSelector("ss-input-wrap[label='Price High-Low']"));
		sortByPriceHighToLow.click();

		applyButtonRoot.findElement(By.cssSelector("div.button")).click();

		if (driver.getCurrentUrl().contains("price-high-low")) {
			System.out.println("Filter is applied");
		} else {
			System.out.println("Filter is not applied");
		}

		List<WebElement> listOfShoes = driver.findElements(By.xpath("//div[@data-automation-product-type='regular']"));
		WebElement firstSneaker = listOfShoes.get(0).findElement(By.xpath("//div[@class='product-tile']//a"));
		wait.until(ExpectedConditions.elementToBeClickable(firstSneaker)).click();

		WebElement productNameElement = driver.findElement(By.tagName("h1"));
		String productName = productNameElement.getText();

		driver.findElement(By.cssSelector("div.add-to-cart-button-section button")).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".product-sizes"))));
		WebElement addToBag = driver.findElement(By.xpath("//button[normalize-space(text())='Add to bag']"));

		if (isAttributePresentInElement(addToBag, "disabled")) {
			System.out.println("Add to bag button is disabed before choosing the size");
		} else {
			System.out.println("Add to bag button is enabled before choosing the size - Should not be the case ");
		}
//here
		List<WebElement> getSizesElements = driver
				.findElements(By.cssSelector("div[data-automation-key-size-enabled='true']"));

		randomNumber = randomNumberGenerator(getSizesElements.size());

		WebElement randomShoeSizeElement = getSizesElements.get(randomNumber).findElement(By.tagName("button"));

		String randomShoeSize = randomShoeSizeElement.getText();
		wait.until(ExpectedConditions.visibilityOf(randomShoeSizeElement));
		wait.until(ExpectedConditions.elementToBeClickable(randomShoeSizeElement));
		randomShoeSizeElement.click();

		wait.until(ExpectedConditions.elementToBeClickable(addToBag));

		if (isAttributePresentInElement(addToBag, "disabled")) {
			System.out.println("Add to bag button is disabled after choosing the size - should not be the case");
		} else {
			System.out.println("Add to bag button is enabled after choosing the size");
		}

		addToBag.click();
		WebElement miniCartContainer = driver.findElement(By.cssSelector("div.minicart"));
		wait.until(ExpectedConditions.visibilityOf(miniCartContainer));
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.cssSelector("div[class='panel-notification js-panel-notification is-active']"))));

		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.cssSelector("a[class='panel-notification__link js-panel-notification-link']"))));
		driver.findElement(By.cssSelector("a[class='panel-notification__link js-panel-notification-link']")).click();
		WebElement cartElement = driver.findElement(By.cssSelector("span.minicart-quantity"));

		if (cartElement.getText().equals("1")) {
			System.out.println("Product added in the cart");
		} else {
			System.out.println("Product not added in the cart - mini cart");
		}

		driver.findElement(By.cssSelector("button[aria-label='Toggle navigation']")).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("nav.header-nav"))));

		// click on the shoes link
		WebElement clothingLink = driver.findElement(By.cssSelector("button[data-category='men-nav-clothing']"));
		clothingLink.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.nav__submenu"))));

		driver.findElement(By.cssSelector("div[automation-key-category-id='jackets'] button")).click();
		WebElement subCategoryOfJackets = driver.findElement(
				By.xpath("//div[@automation-key-category-id='jackets']/..//div[contains(@class,'nav__subcategory')]"));
		wait.until(ExpectedConditions.visibilityOf(subCategoryOfJackets));

		driver.findElement(By.cssSelector("div[automation-key-category-id='jackets-classic'] a")).click();

		// Wait for the sneakers page to load
		wait.until(ExpectedConditions.titleContains("Men's Classic Jackets & Blazers - Blue Blazers & Grey Jackets"));
		// extra validation on the title
		if (driver.getTitle().trim()
				.equals("Men's Classic Jackets & Blazers - Blue Blazers & Grey Jackets | SUITSUPPLY The Netherlands")) {
			System.out.println("In the Jackets Page");
		} else {
			System.out.println("Navigation to Jackets Page is failed");
		}

		WebElement filterAndSortButton1 = wait.until(ExpectedConditions.refreshed(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//button[normalize-space(text())='Filter & Sort']")))));
		filterAndSortButton1.click();

		WebElement filterSideKick1 = wait.until(ExpectedConditions.refreshed(ExpectedConditions
				.visibilityOf(driver.findElement(By.cssSelector("filters-wrapper[panel-title='Filter & Sort']")))));
		SearchContext filterSideKickRoot1 = filterSideKick1.getShadowRoot();

		WebElement colorAccordion = driver.findElement(By.xpath("//ss-accordion-item[@label='Color']"));

		SearchContext colorAccordionRoot = colorAccordion.getShadowRoot();

		SearchContext colorAccordionLabelRoot = colorAccordionRoot
				.findElement(By.cssSelector("ss-link[part='accordion-label']")).getShadowRoot();
		WebElement colorAccordionButton = colorAccordionLabelRoot
				.findElement(By.cssSelector("button[automation-key-filter-id='Color']"));

		colorAccordionButton.click();
		WebElement colorGridElement = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//ss-accordion-item[@label='Color']//ss-grid"))));

//		  SearchContext colorGridElementRoot = colorGridElement.getShadowRoot();
		List<WebElement> listOfColorsElements = colorGridElement.findElements(By.cssSelector("ss-card"));
		randomNumber = randomNumberGenerator(listOfColorsElements.size());

		String filterByColor = listOfColorsElements.get(randomNumber).getAttribute("filter-value-id");
wait.until(ExpectedConditions.elementToBeClickable(listOfColorsElements.get(randomNumber)));
		listOfColorsElements.get(randomNumber).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.loader"))));

		SearchContext stickyButtonSectionRoot1 = filterSideKickRoot1
				.findElement(By.cssSelector("ss-side-sticky.sticky-buttons")).getShadowRoot();
		WebElement applyButton1 = stickyButtonSectionRoot1
				.findElement(By.cssSelector("ss-button.automation-key-apply-button"));
		SearchContext applyButtonRoot1 = applyButton1.getShadowRoot();

		if (isAttributePresentInElement(applyButton, "disabled")) {
			System.out.println("Apply button is disabled after choosing the size - should not be the case");
		} else {
			System.out.println("Apply button is enabled after choosing the size");
		}

		String availableCount = applyButton1.getAttribute("aria-label").replaceAll("[^\\d.]", "");

		applyButton1.click();

		System.out.println(availableCount);

		List<WebElement> listOfJackets = driver
				.findElements(By.xpath("//div[@data-automation-product-type='regular']"));
		if (listOfJackets.size() == Integer.valueOf(availableCount)) {
			System.out.println("Count from the filter section is equal with the actual products count ");
		} else {
			System.out.println("Count mismatch");
		}

		WebElement firstJacket = listOfJackets.get(0).findElement(By.xpath("//div[@class='product-tile']//a"));
		wait.until(ExpectedConditions.elementToBeClickable(firstJacket)).click();

		WebElement productNameElement1 = driver.findElement(By.tagName("h1"));
		String productName1 = productNameElement1.getText();

		driver.findElement(By.cssSelector("div.add-to-cart-button-section button")).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("sp-customer-sizes"))));
		WebElement addToBag1 = driver.findElement(By.xpath("//button[normalize-space(text())='Add to bag']"));

		if (isAttributePresentInElement(addToBag1, "disabled")) {
			System.out.println("Add to bag button is disabed before choosing the size");
		} else {
			System.out.println("Add to bag button is enabled before choosing the size - Should not be the case ");
		}

		SearchContext sizeSectionHost = driver.findElement(By.cssSelector("div.size-passport sp-customer-sizes"))
				.getShadowRoot();
		SearchContext sizeSectionContentHost = sizeSectionHost.findElement(By.cssSelector("sp-customer-sizes-content"))
				.getShadowRoot();
		SearchContext sizePickerHost = sizeSectionContentHost.findElement(By.cssSelector("sp-size-picker"))
				.getShadowRoot();
		SearchContext sizeSelectorHost = sizePickerHost.findElement(By.cssSelector("ss-size-selector")).getShadowRoot();

		List<WebElement> jacketSizeList = sizeSelectorHost
				.findElements(By.cssSelector("div.btn-container:not(:has(.btn-marker)) ss-button[class]:not([class='disabled'])"));
		randomNumber = randomNumberGenerator(jacketSizeList.size());

		String sizeJacket = jacketSizeList.get(randomNumber).getAttribute("aria-label");

		jacketSizeList.get(randomNumber).click();
		
		System.out.println(sizeJacket);

		if (isAttributePresentInElement(addToBag1, "disabled")) {
			System.out.println("Add to bag button is disabled after choosing the size - should not be the case");
		} else {
			System.out.println("Add to bag button is enabled after choosing the size");
		}
		wait.until(ExpectedConditions.elementToBeClickable(addToBag1));
		addToBag1.click();
		
		
		WebElement miniCartContainer1 = driver.findElement(By.cssSelector("div.minicart"));
		wait.until(ExpectedConditions.visibilityOf(miniCartContainer1));
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.cssSelector("div[class='panel-notification js-panel-notification is-active']"))));

		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.cssSelector("a[class='panel-notification__link js-panel-notification-link']"))));
		driver.findElement(By.cssSelector("a[class='panel-notification__link js-panel-notification-link']")).click();
		WebElement cartElement1 = driver.findElement(By.cssSelector("span.minicart-quantity"));

		if (cartElement1.getText().equals("2")) {
			System.out.println("Two Products are added in the cart");
		} else {
			System.out.println("Product not added in the cart - mini cart");
		}
		
		
		if(driver.findElements(By.cssSelector("div.cart__products-list div.product-card")).size()==2) {
			System.out.println("Two cards in the cart");
		} else {
			System.out.println("No two cards ");
		}
		
	List<WebElement> crossIcons = driver.findElements(By.cssSelector("div.cart__products-list div.product-card span.product-card__remove-icon"));
	for (WebElement webElement : crossIcons) {
		webElement.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='notification js-notification is-active']"))));
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("span.notification__text-content")), "Removed from bag"));
		//notification js-notification is-active
		//class="notification__text-content js-notification-text" "Removed from bag"
		
		
	}
	
	System.out.println(driver.findElement(By.cssSelector("h1.cart__empty-title")).isDisplayed());

	}

	private static boolean isAttributePresentInElement(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	private static int randomNumberGenerator(int range) {
		System.out.println("range" + range);
		Random random = new Random();
		int randomN = random.nextInt(range - 1) + 1;
		System.out.println(randomN);
		return randomN;
	}

}