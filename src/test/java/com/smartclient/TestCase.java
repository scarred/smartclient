package com.smartclient;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.smartclient.misc.Log;
import com.smartclient.misc.Values;

public abstract class TestCase {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

	protected WebDriver driver;

	public abstract void testMain() throws Exception;

	@Test
	public void test() {
		try {
			testMain();
		} catch (Exception e) {
			Log.fatal(e.getMessage());
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void setUp() {

		try {
			// non-standard installation of Firefox - can be removed for default
			// install location
			System.setProperty("webdriver.firefox.bin", Values.FIREFOX_LOCATION);

			driver = new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(Values.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Values.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			Log.info("setup done");
		} catch (Exception e) {
			Log.fatal(e.getMessage());
			Assert.fail(e.getMessage());
		}

	}

	@AfterClass
	public void tearDown() {
		try {
			if (driver != null)
				driver.quit();
			Log.info("teardown done");
		} catch (Exception e) {
			Log.fatal(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

}
