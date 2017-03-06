package com.mb.sociality.service;

import java.util.List;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.DBussinessCard;
import com.mb.sociality.vo.api.BussinessCardVO;

public interface DBussinessCardService extends BaseService{

	List<DBussinessCard> selectByFKGuid(String guid);
	List<DBussinessCard> selectByCorporationGuid(String guid);
	DBussinessCard selectByName(String name);
	
	public BussinessCardVO selectByGuid(String guid);
	
	public String deleteByGuid(String guid);
	
	public List<DBussinessCard> selectByLike(int page, int rows, String categoryGuid, String attributeGuid, String statusGuid, String text);
	
	public List<DBussinessCard> selectByLimit(int page, int rows);
}
