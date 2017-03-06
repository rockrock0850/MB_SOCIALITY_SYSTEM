package com.mb.sociality.base;

import java.util.List;

public interface BaseService {
	public List<?> select();
	
	public Object selectByPrimaryKey(int id);

	public Object create(Object record);
	
	public Object update(Object record);

	public int deleteByPrimaryKey(String id);
}
