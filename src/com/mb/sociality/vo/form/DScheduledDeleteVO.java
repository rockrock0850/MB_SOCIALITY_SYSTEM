package com.mb.sociality.vo.form;

import java.util.List;

import com.mb.sociality.model.DScheduled;

public class DScheduledDeleteVO extends DScheduled{
    List<String> guidArray;

	public List<String> getGuidArray() {
		return guidArray;
	}

	public void setGuidArray(List<String> guidArray) {
		this.guidArray = guidArray;
	}
}
