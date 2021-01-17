package com.test.AlertService;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class TestScenarios {

	/* TEST CASE 1 : 
	 * 
	 * Test Data set up :
	 * 1. Alert List already contains below 3 Alerts. 
	 * Alert alert1	  = new Alert("DB1_DOWN", "database 1 is down", "serviceA",	  LocalDateTime.now()); 
	 * Alert alert2 = new Alert("DB2_DOWN",	  "database 2 is down", "serviceB", LocalDateTime.now()); 
	 * Alert alert3 = new	 Alert("DB1_DOWN", "database 1 is down", "serviceC", LocalDateTime.now()); 
	 * 2.	n=6, x=600
	 * 
	 * Test case description: Scenario 1: Add one more alert to alert's list Alert
	 * alert4 = new Alert("TWS Down", "TWS is down", "MAP", LocalDateTime.now());
	 * Result - 1. alert should be successfully added. 2. Alert should be printed as
	 * this alert is not printed in last 600 seconds
	 * 
	 */

	@Test
	public void testScenario1() {

		AlertService alertService = new AlertService(6, 600);
		Alert alert4 = new Alert("TWS Down", "TWS is down", "MAP", LocalDateTime.now());
		alertService.sendAlert(alert4);
		alertService.getCurrentSize();
		assertEquals(4, alertService.getAlertQueue().size());
		assertEquals(true, alertService.isDoprint());
	}
	
	/* TEST CASE 2 : 
	 * 
	 * Test Data set up :
	 * 1. Alert List already contains below 3 Alerts. Alert alert1
	 * = new Alert("DB1_DOWN", "database 1 is down", "serviceA",
	 * LocalDateTime.now()); Alert alert2 = new Alert("DB2_DOWN",
	 * "database 2 is down", "serviceB", LocalDateTime.now()); Alert alert3 = new
	 * Alert("DB1_DOWN", "database 1 is down", "serviceC", LocalDateTime.now());
	 * 
	 * 2. n=6, x=600
	 * 
	 * Test case description: Add new alert to alerts list. Alert alert4 = new
	 * Alert("TWS Down", "TWS is down", "MAP", LocalDateTime.now()); Then add within
	 * few seconds add another alert with same root cause. Alert alert5 = new
	 * Alert("TWS Down", "TWS is down", "MAP", LocalDateTime.now());
	 * 
	 * Result -
	 * 
	 * Both new alerts should be successfully added to the list however Alert 5
	 * should not be printed as alert 4 having the same root cause has been printed
	 * in last 600 seconds.
	 */
	
	@Test
	public void testScenario2() {

		AlertService alertService = new AlertService(6, 600);
		Alert alert4 = new Alert("TWS Down", "TWS is down", "MAP", LocalDateTime.now());
		alertService.sendAlert(alert4);
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert5 = new Alert("TWS Down", "TWS is down", "MAP", LocalDateTime.now());
		alertService.sendAlert(alert5);

		alertService.getCurrentSize();
		assertEquals(5, alertService.getAlertQueue().size());
		assertEquals(false, alertService.isDoprint());
	}
	
	//TEST CASE 3 : 
	
	@Test
	public void testGetCurrentSize()
	{
		AlertService alertService = new AlertService(4, 600);
		Alert alert = new Alert("Kafka Down", "Kafka is Down", "MAP", LocalDateTime.now());
		alertService.sendAlert(alert);
		alertService.getCurrentSize();
		assertEquals(4, alertService.getAlertQueue().size());
		
	}
}
