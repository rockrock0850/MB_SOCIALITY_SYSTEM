package com.mb.sociality.service;

import java.util.List;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.DScheduled;
import com.mb.sociality.vo.api.ScheduledVO;

public interface DScheduledService extends BaseService{
	List<DScheduled> selectByCorporationGuidWithDateType(String guid,String dateType);
	List<DScheduled> selectByDate();
	List<DScheduled> selectByHistory();
	DScheduled selectByGuid(String guid);
	DScheduled selectByName(String name);
	
	public DScheduled selectByTitle(String title);
	
	public String deleteByGuid(String guid);

	public List<ScheduledVO> selectByLimit(int page, int rows);

	public List<ScheduledVO> selectByLike(int page, int rows, String status, String start, String end, String text);
}
