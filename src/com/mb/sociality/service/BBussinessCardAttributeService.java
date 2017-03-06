package com.mb.sociality.service;

import java.util.List;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.BBussinessCardAttribute;
import com.mb.sociality.vo.CardAttributeVO;

public interface BBussinessCardAttributeService extends BaseService{
	
	List<BBussinessCardAttribute> selectByCorporationGuid(String guid);
	BBussinessCardAttribute selectByGuid(String guid);
	BBussinessCardAttribute selectByName(String name);
	
	public String deleteByGuid(String guid);
	
	public List<CardAttributeVO> selectByLimit(int page, int rows);
}
