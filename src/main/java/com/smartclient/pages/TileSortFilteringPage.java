package com.smartclient.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TileSortFilteringPage extends PageObject {

	public TileSortFilteringPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		driver.get(URL);
	}

	private String URL = "http://www.smartclient.com/smartgwt/showcase/#featured_tile_filtering";

	private final By byAnimalTextBox = By.xpath(".//label[contains(text(), 'Animal')]/parent::td/following-sibling::td/input");
	private final By byMinSliderValue = By.xpath("(.//*[@class='sliderRange'])[1]");
	private final By byMaxSliderValue = By.xpath("(.//*[@class='sliderRange'])[2]");
	private final By byLifeSpanSliderTrack = By.id("isc_28");
	private final By bySortingSelect = By.id("isc_34");
	private final By bySortAscending = By.id("isc_3E");
	private final By byButtonFilter = By.xpath(".//*[@id='isc_3I']//*[@class[contains(., 'button')]][contains(., 'Filter')]");
	private final By byResult = By.xpath("//div[@id[contains(., 'isc')]][@class='simpleTile'][@style[not(contains(., 'visibility: hidden'))]]");

	/**
	 * 
	 * @param filterText
	 *            - 'Animal' text box
	 * @param lifeSpan
	 *            - lifespan slider value
	 * @param sortBy
	 *            - sort order
	 * @param ascending
	 *            - checkbox 'Ascending'
	 */
	public TileSortFilteringPage setFilters(String filterText, int lifeSpan, String sortBy, boolean ascending) {
		driver.findElement(byAnimalTextBox).clear();
		driver.findElement(byAnimalTextBox).sendKeys(filterText);
		moveSliderToValue(lifeSpan);
		setDivSelect(driver.findElement(bySortingSelect), sortBy);

		boolean ascendingChecked = driver.findElement(bySortAscending).getAttribute("class").equals("checkboxTrue");
		if (ascending) {
			if (!ascendingChecked)
				driver.findElement(bySortAscending).click();
		} else {
			if (ascendingChecked)
				driver.findElement(bySortAscending).click();
		}

		driver.findElement(byButtonFilter).click();

		return this;
	}

	// TODO final slider position dependent on resolution - needs improvements
	private void moveSliderToValue(int value) {
		int min = Integer.parseInt(driver.findElement(byMinSliderValue).getText());
		int max = Integer.parseInt(driver.findElement(byMaxSliderValue).getText());

		WebElement slider = driver.findElement(byLifeSpanSliderTrack);
		int width = slider.getSize().width;

		int offset = ((width * (value - min)) / max) - 5;
		new Actions(driver).moveToElement(slider, 0, 0).clickAndHold().moveByOffset(offset, 0).release().perform();
	}

	public int getNumberOfFoundItems() {
		int res = driver.findElements(byResult).size();
		return res;
	}

	public void setDivSelect(WebElement select, String value) {
		select.click();
		WebElement elem = driver.findElement(By.xpath(".//td/div[contains(., '" + value + "')]"));

		elem.click();

	}

}
