package com.mb.sociality.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.DBussinessCardMapper;
import com.mb.sociality.model.DBussinessCard;
import com.mb.sociality.model.DBussinessCardExample;
import com.mb.sociality.model.DBussinessCardExample.Criteria;
import com.mb.sociality.service.DBussinessCardService;
import com.mb.sociality.vo.api.BussinessCardVO;

@Component
public class DBussinessCardServiceImplement implements DBussinessCardService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	DBussinessCardMapper mapper;
	
	@Override
	public List<?> select() {
		return mapper.selectByExample(new DBussinessCardExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public Object create(Object record) {
		try {
			DBussinessCard vo = (DBussinessCard) record;
			vo.setGuid(vo.getGuid() == null ? UUID.randomUUID().toString() : vo.getGuid());
			int isSuccess = mapper.insert(vo);
;			return isSuccess > 0 ? vo : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public Object update(Object record) {
		try {
			DBussinessCard category = (DBussinessCard) record;
			
			DBussinessCardExample example = new DBussinessCardExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(category.getGuid());
			int isSuccess = mapper.updateByExampleSelective(category, example);
			return isSuccess > 0 ? category : null;
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
	public DBussinessCard selectByName(String name) {
		try {
			DBussinessCardExample example = new DBussinessCardExample();
			Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(name);
			List<DBussinessCard> card = mapper.selectByExample(example);
			return card.size() > 0 ? card.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public String deleteByGuid(String guid) {
		try {
			DBussinessCardExample example = new DBussinessCardExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			return mapper.deleteByExample(example) > 0 ? guid : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public BussinessCardVO selectByGuid(String guid) {
		try {
			DBussinessCardExample example = new DBussinessCardExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			List<DBussinessCard> list = mapper.selectByExample(example);

			List<BussinessCardVO> voList = new ArrayList<>();
			if(list != null){
				list.forEach(parent -> {
					BussinessCardVO vo = new BussinessCardVO();
					try {
						BeanUtils.copyProperties(vo, parent);
					} catch (Exception e) {
						log.error(e, e);
					}
					voList.add(vo);
				});
			}
			
			return voList.size() > 0 ? voList.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
	
	@Override
	public List<DBussinessCard> selectByCorporationGuid(String guid) {
		try {
			DBussinessCardExample example = new DBussinessCardExample();
			Criteria criteria = example.createCriteria();
			criteria.andBCorporationGuidEqualTo(guid);
			List<DBussinessCard> card = mapper.selectByExample(example);
			return card;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public List<DBussinessCard> selectByFKGuid(String guid) {
		try {
			DBussinessCardExample example = new DBussinessCardExample();
			Criteria criteria = example.createCriteria();
			criteria.andBBussinessCardCategoryGuidEqualTo(guid);
			List<DBussinessCard> list = mapper.selectByExample(example);
			if (list.size() > 0) {
				return list;
			}
			criteria = example.createCriteria();
			criteria.andBBussinessCardAttributeGuidEqualTo(guid);
			list = mapper.selectByExample(example);
			if (list.size() > 0) {
				return list;
			}
			criteria = example.createCriteria();
			criteria.andBBussinessCardStatusGuidEqualTo(guid);
			list = mapper.selectByExample(example);
			if (list.size() > 0) {
				return list;
			}
			
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public List<DBussinessCard> selectByLimit(int page, int rows) {
		int offset = page * rows;
		List<DBussinessCard> list = mapper.selectByLimit(offset, rows);
		return list.size() > 0 ? list : null;
	}

	@Override
	public List<DBussinessCard> selectByLike(int page, int rows, String categoryGuid, String attributeGuid, String statusGuid, 
			String text) {
		int offset = page * rows;
		List<DBussinessCard> list = mapper.selectByLike(offset, rows, categoryGuid, attributeGuid, statusGuid, text);
		return list.size() > 0 ? list : null;
	}
}
