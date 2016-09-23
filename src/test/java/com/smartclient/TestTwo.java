package com.smartclient;

import org.testng.AssertJUnit;

import com.smartclient.pages.DropDownGridPage;


public class TestTwo extends TestCase{
	
	
	public void testMain() throws Exception {
		DropDownGridPage ddg = new DropDownGridPage(driver);
		ddg = ddg.expand();
		ddg = ddg.setItem("Exercise", "Ea", 1.1);
		
		AssertJUnit.assertTrue("No matching elements found", ddg.isItemFound());
		
	}

	
}
