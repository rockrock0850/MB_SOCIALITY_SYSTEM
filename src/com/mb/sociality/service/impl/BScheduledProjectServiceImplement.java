package com.mb.sociality.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.BScheduledProjectMapper;
import com.mb.sociality.model.BScheduledProject;
import com.mb.sociality.model.BScheduledProjectExample;
import com.mb.sociality.model.BScheduledProjectExample.Criteria;
import com.mb.sociality.service.BScheduledProjectService;
import com.mb.sociality.vo.api.ScheduledProjectVO;

@Component
public class BScheduledProjectServiceImplement implements BScheduledProjectService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	BScheduledProjectMapper mapper;
	
	@Override
	public List<?> select() {
		return mapper.selectByExample(new BScheduledProjectExample());
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public List<ScheduledProjectVO> selectByLimit(int page, int rows) {
		int offset = page * rows;
		List<BScheduledProject> list = mapper.selectByLimit(offset, rows);

		List<ScheduledProjectVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				ScheduledProjectVO vo = new ScheduledProjectVO();
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
			BScheduledProject category = (BScheduledProject) record;
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
			BScheduledProject category = (BScheduledProject) record;
			
			BScheduledProjectExample example = new BScheduledProjectExample();
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
	public BScheduledProject selectByName(String name) {
		try {
			BScheduledProjectExample example = new BScheduledProjectExample();
			Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(name);
			List<BScheduledProject> list = mapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public String deleteByGuid(String guid) {
		try {
			BScheduledProjectExample example = new BScheduledProjectExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			return mapper.deleteByExample(example) > 0 ? guid : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public BScheduledProject selectByGuid(String guid) {
		try {
			BScheduledProjectExample example = new BScheduledProjectExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			List<BScheduledProject> list = mapper.selectByExample(example);
			return list.size() > 0 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
	
	@Override
	public List<BScheduledProject> selectByCorporationGuid(String guid) {
		try {
			BScheduledProjectExample example = new BScheduledProjectExample();
			Criteria criteria = example.createCriteria();
			criteria.andBCorporationGuidEqualTo(guid);
			List<BScheduledProject> list = mapper.selectByExample(example);
			return list;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
