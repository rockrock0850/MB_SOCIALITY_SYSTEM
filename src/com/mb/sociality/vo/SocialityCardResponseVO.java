package com.mb.sociality.vo;

import java.util.List;

import com.mb.sociality.model.BBussinessCardAttribute;
import com.mb.sociality.model.BBussinessCardCategory;
import com.mb.sociality.model.BBussinessCardStatus;
import com.mb.sociality.model.DBussinessCard;

public class SocialityCardResponseVO extends DBussinessCard{
	List<BBussinessCardStatus> status;
	List<BBussinessCardCategory> category;
	List<BBussinessCardAttribute> attribute;
	private String employeeName;
	private String bBussinessCardStatusGuid;
	private String firstMeetTimeString;
	String result;
	String birthday;
	
	public List<BBussinessCardStatus> getStatus() {
		return status;
	}
	public void setStatus(List<BBussinessCardStatus> status) {
		this.status = status;
	}
	public List<BBussinessCardCategory> getCategory() {
		return category;
	}
	public void setCategory(List<BBussinessCardCategory> category) {
		this.category = category;
	}
	public List<BBussinessCardAttribute> getAttribute() {
		return attribute;
	}
	public void setAttribute(List<BBussinessCardAttribute> attribute) {
		this.attribute = attribute;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getbBussinessCardStatusGuid() {
		return bBussinessCardStatusGuid;
	}
	public void setbBussinessCardStatusGuid(String bBussinessCardStatusGuid) {
		this.bBussinessCardStatusGuid = bBussinessCardStatusGuid;
	}
	public String getFirstMeetTimeString() {
		return firstMeetTimeString;
	}
	public void setFirstMeetTimeString(String firstMeetTimeString) {
		this.firstMeetTimeString = firstMeetTimeString;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
