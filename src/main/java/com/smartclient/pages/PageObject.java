package com.smartclient.pages;

import org.openqa.selenium.WebDriver;

public class PageObject {
	protected WebDriver driver;

	public PageObject(WebDriver driver) {
		this.driver = driver;
	}

	// TODO managed click method - currently no waits, no timeouts

}
