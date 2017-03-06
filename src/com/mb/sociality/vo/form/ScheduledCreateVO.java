package com.mb.sociality.vo.form;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.BScheduledProject;

public class ScheduledCreateVO extends BScheduledProject{
	@NotBlank(message = "請輸入預定項目名稱欄位")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
