package com.mb.sociality.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.BBussinessCardAttributeMapper;
import com.mb.sociality.model.BBussinessCardAttribute;
import com.mb.sociality.model.BBussinessCardAttributeExample;
import com.mb.sociality.model.BBussinessCardAttributeExample.Criteria;
import com.mb.sociality.service.BBussinessCardAttributeService;
import com.mb.sociality.vo.CardAttributeVO;

@Component
public class BBussinessCardAttributeImplement implements BBussinessCardAttributeService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	BBussinessCardAttributeMapper mapper;
	
	@Override
	public List<?> select() {
		return mapper.selectByExample(new BBussinessCardAttributeExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public List<CardAttributeVO> selectByLimit(int page, int rows) {
		int offset = page * rows;
		List<BBussinessCardAttribute> list = mapper.selectByLimit(offset, rows);

		List<CardAttributeVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				CardAttributeVO vo = new CardAttributeVO();
				try {
					BeanUtils.copyProperties(vo, parent);
					vo.setPk(parent.getGuid());
				} catch (Exception e) {
					log.error(e, e);
				}
				voList.add(vo);
			});
		}

		return voList.size() > 0 ? voList : null;
	}

	@Override
	public Object create(Object record) {
		try {
			BBussinessCardAttribute category = (BBussinessCardAttribute) record;
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
			BBussinessCardAttribute category = (BBussinessCardAttribute) record;
			
			BBussinessCardAttributeExample example = new BBussinessCardAttributeExample();
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
	public BBussinessCardAttribute selectByName(String name) {
		try {
			BBussinessCardAttributeExample example = new BBussinessCardAttributeExample();
			Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(name);
			List<BBussinessCardAttribute> list = mapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public String deleteByGuid(String guid) {
		try {
			BBussinessCardAttributeExample example = new BBussinessCardAttributeExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			int isSuccess = mapper.deleteByExample(example);
			return isSuccess > 0 ? guid : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public BBussinessCardAttribute selectByGuid(String guid) {
		try {
			BBussinessCardAttributeExample example = new BBussinessCardAttributeExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			List<BBussinessCardAttribute> list = mapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public List<BBussinessCardAttribute> selectByCorporationGuid(String guid) {
		try {
			BBussinessCardAttributeExample example = new BBussinessCardAttributeExample();
			Criteria criteria = example.createCriteria();
			criteria.andBCorporationGuidEqualTo(guid);
			List<BBussinessCardAttribute> list = mapper.selectByExample(example);
			return list;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
