package com.test.AlertService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class GenerateAlert {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter maxsize for Alert's List : ");  
		int maxsize = sc.nextInt(); 
		
		System.out.println("Enter resend Interval In Seconds : ");  
		int resendIntervalInSeconds = sc.nextInt(); 

		AlertService alertService = new AlertService(maxsize, resendIntervalInSeconds);
		
		Boolean loop = true;
		while (loop) {
			
			System.out.println("Enter information for new Alert - ");

			System.out.println("Enter rootCause - ");
			String rootCause = sc.nextLine();

			System.out.println("Enter alertMessage - ");
			String alertMessage = sc.nextLine();

			System.out.println("Enter serviceName - ");
			String serviceName = sc.nextLine();

			Alert alert = new Alert(rootCause, alertMessage, serviceName, LocalDateTime.now());
			alertService.sendAlert(alert);
			alertService.printAllAlerts();
			alertService.getCurrentSize();
			
			System.out.println("Do you want to add one more alert?(Y/N)");
			String str= sc.nextLine();    
			if(str.equalsIgnoreCase("N"))
			{
				loop = false;
			}
		}
	}

}
