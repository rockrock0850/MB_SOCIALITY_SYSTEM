package com.mb.sociality.vo.api;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.BBussinessCardStatus;

public class UpdateCardStatusVO extends BBussinessCardStatus{
	@NotBlank(message = "請輸入屬性名稱")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
