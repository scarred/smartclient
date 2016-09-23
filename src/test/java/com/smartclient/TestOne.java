package com.smartclient;

import org.testng.AssertJUnit;
import com.smartclient.pages.TileSortFilteringPage;

public class TestOne extends TestCase {

	public void testMain() throws Exception {
		TileSortFilteringPage tsp = new TileSortFilteringPage(driver);
		tsp = tsp.setFilters("a", 40, "Life Span", true);

		AssertJUnit.assertTrue("Number of search results different than expected: " + tsp.getNumberOfFoundItems(), tsp.getNumberOfFoundItems() > 12);
	}

}
