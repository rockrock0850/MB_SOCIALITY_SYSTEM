package com.mb.sociality.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.DScheduledMapper;
import com.mb.sociality.model.DScheduled;
import com.mb.sociality.model.DScheduledExample;
import com.mb.sociality.model.DScheduledExample.Criteria;
import com.mb.sociality.service.DScheduledService;
import com.mb.sociality.vo.api.ScheduledVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class DScheduledServiceImplement implements DScheduledService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	DScheduledMapper dScheduledMapper;
	
	@Override
	public List<?> select() {
		List<DScheduled> list = dScheduledMapper.selectByExample(new DScheduledExample());
		
		List<ScheduledVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				ScheduledVO vo = new ScheduledVO();
				try {
					BeanUtils.copyProperties(vo, parent);
				} catch (Exception e) {
					log.error(e, e);
				}
				voList.add(vo);
			});
		}

		return voList.size() > 0 ? voList : null;
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public List<ScheduledVO> selectByLimit(int page, int rows) {
		int offset = page * rows;
		List<DScheduled> list = dScheduledMapper.selectByLimit(offset, rows);

		List<ScheduledVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				ScheduledVO vo = new ScheduledVO();
				try {
					BeanUtils.copyProperties(vo, parent);
				} catch (Exception e) {
					log.error(e, e);
				}
				voList.add(vo);
			});
		}

		return voList.size() > 0 ? voList : null;
	}

	@Override
	public List<ScheduledVO> selectByLike(int page, int rows, String status, String start, String end, String text) {
		int offset = page * rows;
		List<DScheduled> list = dScheduledMapper.selectByLike(offset, rows, status, start, end, text);

		List<ScheduledVO> voList = new ArrayList<>();
		if(list != null){
			list.forEach(parent -> {
				ScheduledVO vo = new ScheduledVO();
				try {
					BeanUtils.copyProperties(vo, parent);
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
			DScheduled category = (DScheduled) record;
			category.setGuid(UUID.randomUUID().toString());
			int isSuccess = dScheduledMapper.insert(category);
;			return isSuccess > 0 ? category : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public Object update(Object record) {
		try {
			DScheduled category = (DScheduled) record;
			
			DScheduledExample example = new DScheduledExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(category.getGuid());
			int isSuccess = dScheduledMapper.updateByExampleSelective(category, example);
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
	public DScheduled selectByName(String name) {
		try {
			DScheduledExample example = new DScheduledExample();
			Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(name);
			List<DScheduled> list = dScheduledMapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public String deleteByGuid(String guid) {
		try {
			DScheduledExample example = new DScheduledExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			return dScheduledMapper.deleteByExample(example) > 0 ? guid : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public DScheduled selectByGuid(String guid) {
		try {
			DScheduledExample example = new DScheduledExample();
			Criteria criteria = example.createCriteria();
			criteria.andGuidEqualTo(guid);
			List<DScheduled> list = dScheduledMapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
	
	@Override
	public List<DScheduled> selectByCorporationGuidWithDateType(String guid,String dateType) {
		try {
			DScheduledExample example = new DScheduledExample();
			Criteria criteria = example.createCriteria();
			criteria.andBCorporationGuidEqualTo(guid);
			if (StringUtils.equals(dateType, "future")) {
				criteria.andStartTimeGreaterThan(new Date());
				example.or().andEndTimeGreaterThan(new Date());
			}else if (StringUtils.equals(dateType, "history")) {
				criteria.andEndTimeLessThan(new Date());
			}
			List<DScheduled> list = dScheduledMapper.selectByExample(example);
			return list;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public List<DScheduled> selectByDate() {
		DScheduledExample example = new DScheduledExample();
		Criteria criteria = example.createCriteria();
		criteria.andStartTimeGreaterThan(new Date());
		example.or().andEndTimeGreaterThan(new Date());
		List<DScheduled> list = dScheduledMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<DScheduled> selectByHistory() {
		DScheduledExample example = new DScheduledExample();
		Criteria criteria = example.createCriteria();
		criteria.andEndTimeLessThan(new Date());
		List<DScheduled> list = dScheduledMapper.selectByExample(example);
		return list;
	}

	@Override
	public DScheduled selectByTitle(String title) {
		try {
			DScheduledExample example = new DScheduledExample();
			Criteria criteria = example.createCriteria();
			criteria.andTitleEqualTo(title);
			List<DScheduled> list = dScheduledMapper.selectByExample(example);
			return list.size() > 0 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
