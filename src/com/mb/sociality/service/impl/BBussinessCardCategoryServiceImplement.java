package com.mb.sociality.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.BBussinessCardCategoryMapper;
import com.mb.sociality.model.BBussinessCardCategory;
import com.mb.sociality.model.BBussinessCardCategoryExample;
import com.mb.sociality.model.BBussinessCardCategoryExample.Criteria;
import com.mb.sociality.service.BBussinessCardCategoryService;
import com.mb.sociality.vo.CardCategoryVO;

@Component
public class BBussinessCardCategoryServiceImplement implements BBussinessCardCategoryService{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BBussinessCardCategoryMapper mapper;
	
	@Override
	public List<?> select() {
		return mapper.selectByExample(new BBussinessCardCategoryExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public List<CardCategoryVO> selectByLimit(int page, int rows)  throws Exception{
		int offset = page * rows;
		List<BBussinessCardCategory> list = mapper.selectByLimit(offset, rows);

		List<CardCategoryVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				CardCategoryVO vo = new CardCategoryVO();
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
			BBussinessCardCategory category = (BBussinessCardCategory) record;
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
			BBussinessCardCategory category = (BBussinessCardCategory) record;
			
			BBussinessCardCategoryExample example = new BBussinessCardCategoryExample();
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
	public BBussinessCardCategory selectByName(String name) {
		try {
			BBussinessCardCategoryExample example = new BBussinessCardCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(name);
			List<BBussinessCardCategory> list = mapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public String deleteByGuid(String guid) {
		try {
			BBussinessCardCategoryExample example = new BBussinessCardCategoryExample();
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
	public BBussinessCardCategory selectByGuid(String guid) {
		try {
			BBussinessCardCategoryExample example = new BBussinessCardCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			List<BBussinessCardCategory> list = mapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public List<BBussinessCardCategory> selectByCorporationGuid(String guid) {
		try {
			BBussinessCardCategoryExample example = new BBussinessCardCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andBCorporationGuidEqualTo(guid);
			List<BBussinessCardCategory> list = mapper.selectByExample(example);
			return list;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
