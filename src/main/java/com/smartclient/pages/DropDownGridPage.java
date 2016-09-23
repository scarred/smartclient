package com.smartclient.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DropDownGridPage extends PageObject {

	public DropDownGridPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		driver.get(URL);
	}

	private boolean itemFound = false;
	private String URL = "http://www.smartclient.com/smartgwt/showcase/#featured_dropdown_grid_category";
	private String focusedRow = ".//*[@id[contains(., 'isc_PickListMenu')]][@aria-selected='true']";

	private final By byDropDownExpand = By.id("isc_23");

	public DropDownGridPage expand() {
		driver.findElement(byDropDownExpand).click();
		return this;
	}

	public DropDownGridPage setItem(String item, String unit, double cost) {
		new Actions(driver).sendKeys(Keys.ARROW_DOWN).perform();

		// focus is now on the first record now

		By byItem = By.xpath("(" + focusedRow + "/td)[1]");
		By byUnits = By.xpath("(" + focusedRow + "/td)[2]");
		By byUnitCost = By.xpath("(" + focusedRow + "/td)[3]");
		String itemString = "";
		String unitString = "";
		double unitCost = 0.0;

		while (true) {
			itemString = driver.findElement(byItem).getText();
			unitString = driver.findElement(byUnits).getText();
			unitCost = Double.parseDouble(driver.findElement(byUnitCost).getText());

			if (itemString.contains(item) && unitString.equals(unit) && (unitCost > cost)) {
				new Actions(driver).sendKeys(Keys.ENTER).perform();
				itemFound = true;
				break;
			} else {
				new Actions(driver).sendKeys(Keys.ARROW_DOWN).perform();

				// if values don't change, we've reached the end
				String newItemString = driver.findElement(byItem).getText();
				String newUnitString = driver.findElement(byUnits).getText();
				double newUnitCost = Double.parseDouble(driver.findElement(byUnitCost).getText());
				if (newItemString.equals(itemString) && newUnitString.equals(unitString) && (newUnitCost == unitCost))
					break;
			}
		}

		return this;
	}

	public boolean isItemFound() {
		return itemFound;
	}
}