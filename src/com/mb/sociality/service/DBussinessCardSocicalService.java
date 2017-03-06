package com.mb.sociality.service;

import java.util.List;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.DBussinessCardSocical;

public interface DBussinessCardSocicalService extends BaseService{
	List<DBussinessCardSocical> selectByCardGuid(String guid);
	List<DBussinessCardSocical> selectByCorporationGuid(String guid);
	DBussinessCardSocical selectByGuid(String guid);
	DBussinessCardSocical selectByName(String name);
	int deleteByGuid(String guid);
	
	public String deleteByBussinessCardGuid(String guid);
}
