package com.test.AlertService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AlertService extends AbstractAlertService {

	Queue<Alert> alertQueue = new PriorityQueue<Alert>(this.getMaxSize(), new AlertComparator());
	boolean doprint;
	public AlertService(int maxSize, int resendIntervalInSeconds) {
		super(maxSize, resendIntervalInSeconds);
		init();
	}

	/**
	 * Adds alert to a store. Trims the store if maxSize is exceeded. Prints the
	 * alert if the alert has not been raised for past `resendInterval` seconds.
	 */
	public void sendAlert(Alert newAlert) {

		List<Alert> subsetAlertList = new ArrayList<Alert>();
		LocalDateTime thresholdTimeStamp = newAlert.getTimeStamp().minusSeconds(this.getResendIntervalInSeconds());
		for (Alert alert : alertQueue) {
			if (alert.getTimeStamp().isAfter(thresholdTimeStamp)) {
				subsetAlertList.add(alert);
			}
		}

			if (alertQueue.size() == this.getMaxSize()) {
				System.out.println("\nAlert list has reached max size hence removing oldest alert from list\n");
				Alert oldestAlert = alertQueue.poll();
				System.out.println("\nBelow alert removed from Alert List\n");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				System.out.println(oldestAlert);
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");

			}
			alertQueue.add(newAlert);
			System.out.println("\nNew alert is added in Alert's List\n");

		
		doprint = true;
		for (Alert alert : subsetAlertList) {
			if (newAlert.getRootCause().equalsIgnoreCase(alert.getRootCause()))
				doprint = false;
		}
		if (doprint) {
			System.out
					.println("\nThe newly added alert qualifies the resendIntervalInSeconds criteria hence printing\n");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
			System.out.println(newAlert);
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
		} else {
			System.out.println(
					"\nThe newly added alert doesnt qualify the resendIntervalInSeconds criteria hence can not be printed\n");
		}
	}

	/**
	 * Prints all alerts in the order they were added.
	 */
	public void printAllAlerts() {

		System.out.println("\nThe Current Alert List is :\n");
		for (Alert alert : alertQueue) {
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
			System.out.println(alert);
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------------------");
	}

	/**
	 * Prints the current size.
	 */
	public void getCurrentSize() {
		System.out.println("\nThe Current Alert List size is : " + alertQueue.size() + "\n");
	}

	public void init() {
		Alert alert1 = new Alert("DB1_DOWN", "database 1 is down", "serviceA", LocalDateTime.now());
		Alert alert2 = new Alert("DB2_DOWN", "database 2 is down", "serviceB", LocalDateTime.now());
		Alert alert3 = new Alert("DB1_DOWN", "database 1 is down", "serviceC", LocalDateTime.now());
		alertQueue.add(alert1);
		alertQueue.add(alert2);
		alertQueue.add(alert3);

	}

	public Queue<Alert> getAlertQueue() {
		return alertQueue;
	}

	public void setAlertQueue(Queue<Alert> alertQueue) {
		this.alertQueue = alertQueue;
	}

	public boolean isDoprint() {
		return doprint;
	}

	public void setDoprint(boolean doprint) {
		this.doprint = doprint;
	}
	
}
