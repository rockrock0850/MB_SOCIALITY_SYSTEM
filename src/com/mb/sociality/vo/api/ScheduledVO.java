package com.mb.sociality.vo.api;

import com.mb.sociality.model.DScheduled;

public class ScheduledVO extends DScheduled{
	private String startTimeStr;
	
	private String endTimeStr;
	
	private String emailNotifyTimeStr;
	
	private String alarmTimeStr;
	
	private String scheduledProject;

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getEmailNotifyTimeStr() {
		return emailNotifyTimeStr;
	}

	public void setEmailNotifyTimeStr(String emailNotifyTimeStr) {
		this.emailNotifyTimeStr = emailNotifyTimeStr;
	}

	public String getAlarmTimeStr() {
		return alarmTimeStr;
	}

	public void setAlarmTimeStr(String alarmTimeStr) {
		this.alarmTimeStr = alarmTimeStr;
	}

	public String getScheduledProject() {
		return scheduledProject;
	}

	public void setScheduledProject(String scheduledProject) {
		this.scheduledProject = scheduledProject;
	}
}
