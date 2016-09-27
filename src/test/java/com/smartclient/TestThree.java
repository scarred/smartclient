package com.smartclient;

import com.smartclient.pages.FeaturedNestedGrid;

public class TestThree extends TestCase {

	public void testMain() {
		FeaturedNestedGrid fng = new FeaturedNestedGrid(driver);
		fng = fng.processList("Correction");
	}

}
