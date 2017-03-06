package com.mb.sociality.vo.form;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.BBussinessCardStatus;

public class StatusDeleteVO extends BBussinessCardStatus{

	String pk;
	
	@NotBlank(message = "請輸入名稱欄位")
	String name;

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
