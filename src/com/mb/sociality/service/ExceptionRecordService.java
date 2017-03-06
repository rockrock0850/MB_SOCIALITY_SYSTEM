package com.mb.sociality.service;

import com.mb.sociality.base.BaseService;
import com.mb.sociality.model.ExceptionRecord;

public interface ExceptionRecordService extends BaseService{
	public ExceptionRecord selectByDate(ExceptionRecord er);
	
	public ExceptionRecord selectByTarget(ExceptionRecord er);
}
