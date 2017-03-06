package com.mb.sociality.vo.api;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.DBussinessCard;

public class UpdateBusinessCardVO extends DBussinessCard{
	@NotBlank(message = "請選擇類別")
	private String category;

	@NotBlank(message = "請選擇狀態")
	private String status;

	@NotBlank(message = "請選擇屬性")
	private String attribute;
	
	@NotBlank(message = "請選擇承辦業務")
	private String undertaker;

	@NotBlank(message = "請輸入名稱")
	private String name;
    
    private String socialContacts;
	
	private String birthday;
	
	private String firstMeetTimeStr;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getUndertaker() {
		return undertaker;
	}

	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSocialContacts() {
		return socialContacts;
	}

	public void setSocialContacts(String socialContacts) {
		this.socialContacts = socialContacts;
	}

	public String getFirstMeetTimeStr() {
		return firstMeetTimeStr;
	}

	public void setFirstMeetTimeStr(String firstMeetTimeStr) {
		this.firstMeetTimeStr = firstMeetTimeStr;
	}
}
