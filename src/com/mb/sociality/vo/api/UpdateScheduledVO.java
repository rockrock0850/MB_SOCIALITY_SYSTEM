package com.mb.sociality.vo.api;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.DScheduled;

public class UpdateScheduledVO extends DScheduled{
	@NotBlank(message = "請輸入行程標題")
	private String title;
	
	@NotNull(message = "請選擇開始時間")
	private String startDateTime;

	@NotNull(message = "請選擇結束時間")
	private String endDateTime;
	
	private String scheduledProject;
	
	private String businessCard;

	private String emailNotifyTimeStr;
	
	private String alarmTimeStr;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getScheduledProject() {
		return scheduledProject;
	}

	public void setScheduledProject(String scheduledProject) {
		this.scheduledProject = scheduledProject;
	}

	public String getBusinessCard() {
		return businessCard;
	}

	public void setBusinessCard(String businessCard) {
		this.businessCard = businessCard;
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
}
