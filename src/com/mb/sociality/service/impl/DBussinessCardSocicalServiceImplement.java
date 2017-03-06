package com.mb.sociality.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.DBussinessCardSocicalMapper;
import com.mb.sociality.model.DBussinessCardSocical;
import com.mb.sociality.model.DBussinessCardSocicalExample;
import com.mb.sociality.model.DBussinessCardSocicalExample.Criteria;
import com.mb.sociality.service.DBussinessCardSocicalService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class DBussinessCardSocicalServiceImplement implements DBussinessCardSocicalService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	DBussinessCardSocicalMapper mapper;
	
	@Override
	public List<?> select() {
		return mapper.selectByExample(new DBussinessCardSocicalExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public Object create(Object record) {
		try {
			DBussinessCardSocical category = (DBussinessCardSocical) record;
			category.setGuid(UUID.randomUUID().toString());
			int isSuccess = mapper.insert(category);
;			return isSuccess > 0 ? category : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public Object update(Object record) {
		try {
			DBussinessCardSocical vo = (DBussinessCardSocical) record;
			
			DBussinessCardSocicalExample example = new DBussinessCardSocicalExample();
			Criteria criteria = example.createCriteria();
			criteria.andDBussinessCardGuidEqualTo(vo.getdBussinessCardGuid());
			int isSuccess = mapper.updateByExampleSelective(vo, example);
			return isSuccess > 0 ? vo : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return 0;
	}

	@Override
	public List<DBussinessCardSocical> selectByCorporationGuid(String guid) {
		try {
			DBussinessCardSocicalExample example = new DBussinessCardSocicalExample();
			Criteria criteria = example.createCriteria();
			criteria.andBCorporationGuidEqualTo(guid);
			List<DBussinessCardSocical> list = mapper.selectByExample(example);
			return list;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public DBussinessCardSocical selectByGuid(String guid) {
		try {
			DBussinessCardSocicalExample example = new DBussinessCardSocicalExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			List<DBussinessCardSocical> list = mapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public int deleteByGuid(String guid) {
		try {
			DBussinessCardSocicalExample example = new DBussinessCardSocicalExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			int isSuccess = mapper.deleteByExample(example);
			return isSuccess > 0 ? isSuccess : 0;
		} catch (Exception e) {
			log.error(e, e);
		}
		return 0;
	}

	@Override
	public DBussinessCardSocical selectByName(String name) {
		return null;
	}

	@Override
	public List<DBussinessCardSocical> selectByCardGuid(String guid) {
		try {
			DBussinessCardSocicalExample example = new DBussinessCardSocicalExample();
			Criteria criteria = example.createCriteria();
			criteria.andDBussinessCardGuidEqualTo(guid);
			List<DBussinessCardSocical> list = mapper.selectByExample(example);
			return list;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public String deleteByBussinessCardGuid(String guid) {
		try {
			DBussinessCardSocicalExample example = new DBussinessCardSocicalExample();
			Criteria criteria = example.createCriteria();
			criteria.andDBussinessCardGuidEqualTo(guid);
			return mapper.deleteByExample(example) > 0 ? guid : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
