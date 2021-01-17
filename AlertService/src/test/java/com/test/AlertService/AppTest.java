package com.test.AlertService;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AppTest {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestScenarios.class);
		if (result.getFailureCount() > 0) {
			System.out.println("Failures : ");
			for (Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
		} else {
			System.out.println("Total Test Cases Executed : " + result.getRunCount());
			System.out.println("Total Test Cases Failed : " + result.getFailureCount());
			
		}
	}
}
