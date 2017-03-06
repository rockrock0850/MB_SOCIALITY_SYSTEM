package com.mb.sociality.vo.form;

import org.hibernate.validator.constraints.NotBlank;

import com.mb.sociality.model.User;

public class UserCreateVO extends User{
    @NotBlank(message = "請輸入確認密碼欄位")
    private String replyPassword;

    /*
     * 
     */
    
	public String getReplyPassword() {
		return replyPassword;
	}

	public void setReplyPassword(String replyPassword) {
		this.replyPassword = replyPassword;
	}
}