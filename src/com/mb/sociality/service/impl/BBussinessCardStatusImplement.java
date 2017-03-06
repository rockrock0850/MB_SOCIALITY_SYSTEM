package com.mb.sociality.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.BBussinessCardStatusMapper;
import com.mb.sociality.model.BBussinessCardStatus;
import com.mb.sociality.model.BBussinessCardStatusExample;
import com.mb.sociality.model.BBussinessCardStatusExample.Criteria;
import com.mb.sociality.service.BBussinessCardStatusService;
import com.mb.sociality.vo.CardStatusVO;

@Component
public class BBussinessCardStatusImplement implements BBussinessCardStatusService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	BBussinessCardStatusMapper mapper;
	
	@Override
	public List<?> select() {
		return mapper.selectByExample(new BBussinessCardStatusExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public List<CardStatusVO> selectByLimit(int page, int rows) {
		int offset = page * rows;
		List<BBussinessCardStatus> list = mapper.selectByLimit(offset, rows);

		List<CardStatusVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				CardStatusVO vo = new CardStatusVO();
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
			BBussinessCardStatus category = (BBussinessCardStatus) record;
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
			BBussinessCardStatus category = (BBussinessCardStatus) record;
			
			BBussinessCardStatusExample example = new BBussinessCardStatusExample();
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
	public BBussinessCardStatus selectByName(String name) {
		try {
			BBussinessCardStatusExample example = new BBussinessCardStatusExample();
			Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(name);
			List<BBussinessCardStatus> list = mapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public String deleteByGuid(String guid) {
		try {
			BBussinessCardStatusExample example = new BBussinessCardStatusExample();
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
	public BBussinessCardStatus selectByGuid(String guid) {
		try {
			BBussinessCardStatusExample example = new BBussinessCardStatusExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			List<BBussinessCardStatus> list = mapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
	
	@Override
	public List<BBussinessCardStatus> selectByCorporationGuid(String guid) {
		try {
			BBussinessCardStatusExample example = new BBussinessCardStatusExample();
			Criteria criteria = example.createCriteria();
			criteria.andBCorporationGuidEqualTo(guid);
			List<BBussinessCardStatus> list = mapper.selectByExample(example);
			return list;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
