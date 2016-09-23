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

	private final By byItemList = By.xpath("(.//tr[@aria-expanded='false'][@role='listitem'])[1]/td[2]");
	private final String subItem = ".//div[contains(., 'CATEGORY')][@role='presentation']/parent::td/preceding-sibling::td[3]";
	private final By bySave = By.xpath(".//div[contains(text(), 'Save')]");
	private final By bySelectedItem = By.xpath("(.//tr[@aria-expanded='false'][@aria-selected='true'][@role='listitem'])/td[2]/div");

	// TODO very unstable and not all cases working - doesn't collapse sub-lists
	// correctly
	public FeaturedNestedGrid processList(String item) throws InterruptedException {
		new Actions(driver).click(driver.findElement(byItemList)).perform();

		while (true) {
			String listItemText = driver.findElement(bySelectedItem).getText();
			if (listItemText.contains(item)) {
				new Actions(driver).sendKeys(Keys.ARROW_RIGHT).perform();

				int i = driver.findElements(By.xpath(subItem.replace("CATEGORY", item))).size();
				for (int j = 0; j < i; j++) {
					String s = Integer.toString(j + 1) + " " + Tools.randomString(10);
					new Actions(driver).click(driver.findElements(By.xpath(subItem.replace("CATEGORY", item))).get(j)).click().perform();
					Thread.sleep(150);
					new Actions(driver).click().sendKeys(Keys.chord(Keys.CONTROL, "a")).perform();
					Thread.sleep(150);
					new Actions(driver).sendKeys(s).sendKeys(Keys.ENTER).perform();
					Thread.sleep(150);
					new Actions(driver).click(driver.findElement(bySave)).perform();
					Thread.sleep(150);
				}
				new Actions(driver).sendKeys(Keys.ARROW_LEFT).perform();
				Thread.sleep(150);
				new Actions(driver).sendKeys(Keys.ARROW_DOWN).perform();

			} else {
				new Actions(driver).sendKeys(Keys.ARROW_LEFT).perform();
				Thread.sleep(150);
				new Actions(driver).sendKeys(Keys.ARROW_DOWN).perform();
			}

			String newlistItemText = driver.findElement(bySelectedItem).getText();
			if (listItemText.equals(newlistItemText))
				break;
		}
		return this;
	}

}