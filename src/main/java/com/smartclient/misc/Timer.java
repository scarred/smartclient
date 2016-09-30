package com.smartclient.misc;

import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

public class Timer implements ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
	}

	@Override
	public void onFinish(ISuite suite) {
		long totalTime = 0;
		for (ISuiteResult eachResult : suite.getResults().values()) {
			ITestContext ctx = eachResult.getTestContext();
			Date start = ctx.getStartDate();
			Date end = ctx.getEndDate();
			long ms = end.getTime() - start.getTime();
			totalTime += ms;
		}
		Log.info("RUNTIME: " + totalTime + " ms");

	}
}