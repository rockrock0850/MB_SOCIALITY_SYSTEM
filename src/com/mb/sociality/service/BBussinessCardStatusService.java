package com.mb.sociality.service;

import java.util.List;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.BBussinessCardStatus;
import com.mb.sociality.vo.CardStatusVO;

public interface BBussinessCardStatusService extends BaseService{
	List<BBussinessCardStatus> selectByCorporationGuid(String guid);
	BBussinessCardStatus selectByGuid(String guid);
	BBussinessCardStatus selectByName(String name);
	
	public String deleteByGuid(String guid);
	
	public List<CardStatusVO> selectByLimit(int page, int rows);
}
