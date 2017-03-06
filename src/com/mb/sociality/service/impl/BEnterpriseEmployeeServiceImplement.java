package com.mb.sociality.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.BEnterpriseEmployeeMapper;
import com.mb.sociality.model.BEnterpriseEmployee;
import com.mb.sociality.model.BEnterpriseEmployeeExample;
import com.mb.sociality.model.BEnterpriseEmployeeExample.Criteria;
import com.mb.sociality.service.BEnterpriseEmployeeService;

@Component
public class BEnterpriseEmployeeServiceImplement implements BEnterpriseEmployeeService{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	BEnterpriseEmployeeMapper mapper;
	
	@Override
	public List<?> select() {
		return mapper.selectByExample(new BEnterpriseEmployeeExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public Object create(Object record) {
		return null;
	}

	@Override
	public Object update(Object record) {
		return null;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return 0;
	}

	@Override
	public List<BEnterpriseEmployee> selectByEnterpriseGuid(String guid) {
		try {
			BEnterpriseEmployeeExample example = new BEnterpriseEmployeeExample();
			Criteria criteria = example.createCriteria();
			criteria.andBEnterpriseGuidEqualTo(guid);
			List<BEnterpriseEmployee> list = mapper.selectByExample(example);
			return list;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public BEnterpriseEmployee selectByGuid(String guid) {
		try {
			BEnterpriseEmployeeExample example = new BEnterpriseEmployeeExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			List<BEnterpriseEmployee> list = mapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

}
