package com.test.AlertService;

import java.time.LocalDateTime;

public class Alert {

	private String rootCause;
	private String alertMessage;
	private String serviceName;

	private LocalDateTime timeStamp;

	public Alert(final String rootCause, final String alertMessage, final String serviceName,
			final LocalDateTime timeStamp) {
		this.rootCause = rootCause;
		this.alertMessage = alertMessage;
		this.serviceName = serviceName;
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Alert{");
		sb.append("rootCause='").append(rootCause).append('\'');
		sb.append(", alertMessage='").append(alertMessage).append('\'');
		sb.append(", serviceName='").append(serviceName).append('\'');
		sb.append(", timeStamp=").append(timeStamp);
		sb.append('}');
		return sb.toString();
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
