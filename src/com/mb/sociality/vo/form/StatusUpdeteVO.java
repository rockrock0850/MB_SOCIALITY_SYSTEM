package com.mb.sociality.vo.form;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.BBussinessCardStatus;

import lombok.Getter;
import lombok.Setter;

public class StatusUpdeteVO extends BBussinessCardStatus{
	@Getter 
	@Setter 
	@NotBlank(message = "請輸入名稱欄位")
	String name;
}
