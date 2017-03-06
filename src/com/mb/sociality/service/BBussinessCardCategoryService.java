package com.mb.sociality.service;

import java.util.List;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.BBussinessCardCategory;
import com.mb.sociality.vo.CardCategoryVO;

public interface BBussinessCardCategoryService extends BaseService{
	
	List<BBussinessCardCategory> selectByCorporationGuid(String guid);
	BBussinessCardCategory selectByGuid(String guid);
	BBussinessCardCategory selectByName(String name);
	
	public String deleteByGuid(String guid);
	
	public List<CardCategoryVO> selectByLimit(int page, int rows) throws Exception;
}
