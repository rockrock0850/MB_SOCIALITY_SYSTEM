package com.mb.sociality.vo.form;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.DScheduled;
import com.mb.sociality.utils.ShareTool;

public class DScheduledCreateVO extends DScheduled{

    @NotBlank(message = "姓名欄位不能為空")
    String name;
	Integer status = 1;
	@NotBlank(message = "請選擇預定項目")
    String bScheduledProjectGuid;
	String optradio;
	String editCardBeginDate;
	String editCardBeginTime;
	String editCardEndDate;
	String editCardEndTime;
	String editCardMailDate;
	String editCardMailTime;
	String editCardAlarmDate;
	String editCardAlarmTime;
	String summary = "";
	String recordOfMeeting ="";
	String title ="";
	String companyName ="";
	String destination ="";
	String souvenir ="";
	
	Date startTime = ShareTool.stringToDate("1899/12/31 00:00:00");
    Date endTime = ShareTool.stringToDate("1899/12/31 00:00:00");
    Date emailNotifyTime = ShareTool.stringToDate("1899/12/31 00:00:00");
    Date alarmTime = ShareTool.stringToDate("1899/12/31 00:00:00");
	
	
	public String getbScheduledProjectGuid() {
		return bScheduledProjectGuid;
	}
	public void setbScheduledProjectGuid(String bScheduledProjectGuid) {
		this.bScheduledProjectGuid = bScheduledProjectGuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOptradio() {
		return optradio;
	}
	public void setOptradio(String optradio) {
		this.optradio = optradio;
	}
	public String getEditCardBeginDate() {
		return editCardBeginDate;
	}
	public void setEditCardBeginDate(String editCardBeginDate) {
		this.editCardBeginDate = editCardBeginDate;
	}
	public String getEditCardBeginTime() {
		return editCardBeginTime;
	}
	public void setEditCardBeginTime(String editCardBeginTime) {
		this.editCardBeginTime = editCardBeginTime;
	}
	public String getEditCardEndDate() {
		return editCardEndDate;
	}
	public void setEditCardEndDate(String editCardEndDate) {
		this.editCardEndDate = editCardEndDate;
	}
	public String getEditCardEndTime() {
		return editCardEndTime;
	}
	public void setEditCardEndTime(String editCardEndTime) {
		this.editCardEndTime = editCardEndTime;
	}
	public String getEditCardMailDate() {
		return editCardMailDate;
	}
	public void setEditCardMailDate(String editCardMailDate) {
		this.editCardMailDate = editCardMailDate;
	}
	public String getEditCardMailTime() {
		return editCardMailTime;
	}
	public void setEditCardMailTime(String editCardMailTime) {
		this.editCardMailTime = editCardMailTime;
	}
	public String getEditCardAlarmDate() {
		return editCardAlarmDate;
	}
	public void setEditCardAlarmDate(String editCardAlarmDate) {
		this.editCardAlarmDate = editCardAlarmDate;
	}
	public String getEditCardAlarmTime() {
		return editCardAlarmTime;
	}
	public void setEditCardAlarmTime(String editCardAlarmTime) {
		this.editCardAlarmTime = editCardAlarmTime;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getRecordOfMeeting() {
		return recordOfMeeting;
	}
	public void setRecordOfMeeting(String recordOfMeeting) {
		this.recordOfMeeting = recordOfMeeting;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getSouvenir() {
		return souvenir;
	}
	public void setSouvenir(String souvenir) {
		this.souvenir = souvenir;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getEmailNotifyTime() {
		return emailNotifyTime;
	}
	public void setEmailNotifyTime(Date emailNotifyTime) {
		this.emailNotifyTime = emailNotifyTime;
	}
	public Date getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}	
}
