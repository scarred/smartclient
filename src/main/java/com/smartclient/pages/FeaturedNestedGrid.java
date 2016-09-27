package com.smartclient.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.smartclient.misc.Tools;

public class FeaturedNestedGrid extends PageObject {

	public FeaturedNestedGrid(WebDriver driver) {
		super(driver);
		this.driver = driver;

		driver.get(URL);

	}

	private final String URL = "http://www.smartclient.com/smartgwt/showcase/#featured_nested_grid";

	private final String subItem = ".//div[contains(., 'CATEGORY')][@role='presentation']/parent::td/preceding-sibling::td[3]";
	private final String collapseButtonInTable = "((.//tr/td/div[contains(., 'CATEGORY')])[1]/parent::td/preceding-sibling::td)//span";

	private final By bySelectedItem = By.xpath("(.//tr[@aria-expanded='false'][@aria-selected='true'][@role='listitem'])/td[2]/div");
	private final By byItemList = By.xpath("(.//tr[@aria-expanded='false'][@role='listitem'])[1]/td[2]");

	// TODO could be faster with batch reading of table contents
	public FeaturedNestedGrid processList(String item) {

		// place focus on the first element of the list
		new Actions(driver).click(driver.findElement(byItemList)).perform();

		while (true) {
			// TODO very slow
			String listItemText = driver.findElement(bySelectedItem).getText();
			//

			if (listItemText.contains(item)) {
				new Actions(driver).sendKeys(Keys.ARROW_RIGHT).perform();

				int i = driver.findElements(By.xpath(subItem.replace("CATEGORY", listItemText))).size();
				for (int j = 0; j < i; j++) {
					String s = Integer.toString(j + 1) + " " + Tools.randomString(10);
					new Actions(driver).click(driver.findElements(By.xpath(subItem.replace("CATEGORY", item))).get(j)).click().perform();
					Tools.sleepWait(100);
					new Actions(driver).click().sendKeys(Keys.chord(Keys.CONTROL, "a")).perform();
					Tools.sleepWait(100);
					new Actions(driver).sendKeys(s).sendKeys(Keys.ENTER).perform();
				}
				Tools.sleepWait(300);
				driver.findElement(By.xpath(collapseButtonInTable.replace("CATEGORY", listItemText))).click();
				Tools.sleepWait(100);
				new Actions(driver).sendKeys(Keys.ARROW_DOWN).perform();
				Tools.sleepWait(100);

			} else {
				new Actions(driver).sendKeys(Keys.ARROW_DOWN).perform();
			}

			String newlistItemText = driver.findElement(bySelectedItem).getText();
			if (listItemText.equals(newlistItemText))
				break;
		}
		return this;
	}

}