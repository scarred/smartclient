package com.smartclient;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public abstract class TestCase {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

	protected WebDriver driver;

	public abstract void testMain() throws Exception;

	@Test
	public void test() {
		try {
			testMain();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// TODO timeout can be in separate parameter file
	// TODO non-standard installation of Firefox - need to adjust for other
	// target machine
	@BeforeTest
	public void setUp() {

		try {
			System.setProperty("webdriver.firefox.bin", "D:\\APPS\\FF\\firefox.exe");

			driver = new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterTest
	public void tearDown() {
		try {
			if (driver != null)
				driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
