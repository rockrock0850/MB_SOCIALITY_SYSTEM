package com.mb.sociality.vo.form;

import com.mb.sociality.model.BBussinessCardCategory;

import lombok.Getter;
import lombok.Setter;

public class CategoryDeleteVO extends BBussinessCardCategory{

	String pk;

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}
}
