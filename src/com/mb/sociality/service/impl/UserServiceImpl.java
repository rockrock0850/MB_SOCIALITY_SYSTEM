package com.mb.sociality.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.UserMapper;
import com.mb.sociality.model.User;
import com.mb.sociality.model.UserExample;
import com.mb.sociality.model.UserExample.Criteria;
import com.mb.sociality.service.UserService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class UserServiceImpl implements UserService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> select() {
		return userMapper.selectByExample(new UserExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		try {
			User user = userMapper.selectByPrimaryKey(id);
			return user != null ? user : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public User selectByAccount(String account) {
		try {
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andAccountEqualTo(account);
			List<User> list = userMapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public User selectByLoginInfo(User user) {
		try {
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria
				.andAccountEqualTo(user.getAccount())
				.andPasswordEqualTo(user.getPassword());
			List<User> list = userMapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public Object create(Object record) {
		try {
			User user = (User) record;
			
			int isSuccess = userMapper.insert(user);
			return isSuccess > 0 ? user : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public Object update(Object record) {
		try {
			User user = (User) record;
			
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andAccountEqualTo(user.getAccount());
			int isSuccess = userMapper.updateByExampleSelective(user, example);
			return isSuccess > 0 ? user : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public int deleteByAccount(String account) {
		try {
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andAccountEqualTo(account);
			int isSuccess = userMapper.deleteByExample(example);
			return isSuccess > 0 ? isSuccess : 0;
		} catch (Exception e) {
			log.error(e, e);
		}
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return 0;
	}
}
