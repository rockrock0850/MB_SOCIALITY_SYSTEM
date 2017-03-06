package com.mb.sociality.vo.form;

import java.util.List;

import com.mb.sociality.model.DBussinessCardSocical;

import lombok.Getter;
import lombok.Setter;

public class ContactCreateVO extends DBussinessCardSocical{
	List<String> guidList;

	public List<String> getGuidList() {
		return guidList;
	}

	public void setGuidList(List<String> guidList) {
		this.guidList = guidList;
	}
}
