package com.mb.sociality.vo.api;

import com.mb.sociality.model.DBussinessCard;

public class BussinessCardVO extends DBussinessCard{
	private Object businessCardSocials;
	
	private String firstMeetTimeStr;

	public Object getBusinessCardSocials() {
		return businessCardSocials;
	}

	public void setBusinessCardSocials(Object businessCardSocials) {
		this.businessCardSocials = businessCardSocials;
	}

	public String getFirstMeetTimeStr() {
		return firstMeetTimeStr;
	}

	public void setFirstMeetTimeStr(String firstMeetTimeStr) {
		this.firstMeetTimeStr = firstMeetTimeStr;
	}
}
