package com.mb.sociality.service;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.User;

public interface UserService extends BaseService{
	public User selectByLoginInfo(User user);

	public User selectByAccount(String account);
	
	public int deleteByAccount(String account);
}
