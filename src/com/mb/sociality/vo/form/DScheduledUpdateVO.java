package com.mb.sociality.vo.form;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.DScheduled;
import com.mb.sociality.utils.ShareTool;

public class DScheduledUpdateVO extends DScheduled{

    @NotBlank(message = "姓名欄位不能為空")
    String name;
	Integer status = 1;
	@NotBlank(message = "請選擇預定項目")
     String bScheduledProjectGuid;
	String optradio;
	String updateStrartDate;
	String updateStrartTime;
	String updateEndDate;
	String updateEndTime;
	String updateMailDate;
	String updateMailTime;
	String updateAlarmDate;
	String updateAlarmTime;
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
	public String getUpdateStrartDate() {
		return updateStrartDate;
	}
	public void setUpdateStrartDate(String updateStrartDate) {
		this.updateStrartDate = updateStrartDate;
	}
	public String getUpdateStrartTime() {
		return updateStrartTime;
	}
	public void setUpdateStrartTime(String updateStrartTime) {
		this.updateStrartTime = updateStrartTime;
	}
	public String getUpdateEndDate() {
		return updateEndDate;
	}
	public void setUpdateEndDate(String updateEndDate) {
		this.updateEndDate = updateEndDate;
	}
	public String getUpdateEndTime() {
		return updateEndTime;
	}
	public void setUpdateEndTime(String updateEndTime) {
		this.updateEndTime = updateEndTime;
	}
	public String getUpdateMailDate() {
		return updateMailDate;
	}
	public void setUpdateMailDate(String updateMailDate) {
		this.updateMailDate = updateMailDate;
	}
	public String getUpdateMailTime() {
		return updateMailTime;
	}
	public void setUpdateMailTime(String updateMailTime) {
		this.updateMailTime = updateMailTime;
	}
	public String getUpdateAlarmDate() {
		return updateAlarmDate;
	}
	public void setUpdateAlarmDate(String updateAlarmDate) {
		this.updateAlarmDate = updateAlarmDate;
	}
	public String getUpdateAlarmTime() {
		return updateAlarmTime;
	}
	public void setUpdateAlarmTime(String updateAlarmTime) {
		this.updateAlarmTime = updateAlarmTime;
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
