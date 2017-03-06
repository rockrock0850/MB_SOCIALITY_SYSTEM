package com.mb.sociality.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.sociality.mapper.ExceptionRecordMapper;
import com.mb.sociality.model.ExceptionRecord;
import com.mb.sociality.model.ExceptionRecordExample;
import com.mb.sociality.model.ExceptionRecordExample.Criteria;
import com.mb.sociality.service.ExceptionRecordService;
import com.mb.sociality.utils.Constant.Status;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
//@Transactional("transactionManager")
public class ExceptionRecordServiceImpl implements ExceptionRecordService{
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ExceptionRecordMapper exceptionRecordMapper;

	@Override
	public List<ExceptionRecord> select() {
		return exceptionRecordMapper.selectByExample(new ExceptionRecordExample());
	}

	@Override
	public ExceptionRecord selectByDate(ExceptionRecord er) {
		try {
			ExceptionRecordExample example = new ExceptionRecordExample();
			Criteria criteria = example.createCriteria();
			criteria.andDateEqualTo(er.getDate());
			List<ExceptionRecord> list = exceptionRecordMapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public ExceptionRecord selectByTarget(ExceptionRecord er) {
		try {
			ExceptionRecordExample example = new ExceptionRecordExample();
			Criteria criteria = example.createCriteria();
			criteria.andCauseEqualTo(er.getCause());
			List<ExceptionRecord> list = exceptionRecordMapper.selectByExample(example);
			return list.size() == 1 ? list.get(0) : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public Object create(Object record) {
		try {
			ExceptionRecord er = (ExceptionRecord) record;
			
			int isSuccess = exceptionRecordMapper.insert(er);
			return isSuccess > 0 ? er : null;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	@Override
	public Status update(Object record) {
		return null;
	}

	@Override
	public Object selectByPrimaryKey(int id) {
		return null;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return 0;
	}
}
