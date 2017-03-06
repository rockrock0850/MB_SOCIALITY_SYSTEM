package com.mb.sociality.vo.form;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.BBussinessCardAttribute;

import lombok.Getter;
import lombok.Setter;

public class AttributeUpdeteVO extends BBussinessCardAttribute{
	@Getter 
	@Setter 
	@NotBlank(message = "請輸入名稱欄位")
	String name;
}
