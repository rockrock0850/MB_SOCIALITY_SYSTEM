package com.mb.sociality.service;

import java.util.List;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.BEnterpriseEmployee;

public interface BEnterpriseEmployeeService extends BaseService{
	
	List<BEnterpriseEmployee> selectByEnterpriseGuid (String guid);
	BEnterpriseEmployee selectByGuid (String guid);
}
