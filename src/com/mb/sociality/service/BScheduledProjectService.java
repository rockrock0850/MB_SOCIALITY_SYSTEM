package com.mb.sociality.service;

import java.util.List;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.BScheduledProject;
import com.mb.sociality.vo.api.ScheduledProjectVO;

public interface BScheduledProjectService extends BaseService{
	
	List<BScheduledProject> selectByCorporationGuid(String guid);
	BScheduledProject selectByGuid(String guid);
	BScheduledProject selectByName(String name);
	
	public String deleteByGuid(String guid);
	
	public List<ScheduledProjectVO> selectByLimit(int page, int rows);
}
