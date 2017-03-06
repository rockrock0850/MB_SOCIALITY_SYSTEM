package com.mb.sociality.vo;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class SocialityVO {
	
	List<Map<String, String>> rows;
	int total;
	JSONObject json;
	String selectionGuid;
	public List<Map<String, String>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public JSONObject getJson() {
		return json;
	}
	public void setJson(JSONObject json) {
		this.json = json;
	}
	public String getSelectionGuid() {
		return selectionGuid;
	}
	public void setSelectionGuid(String selectionGuid) {
		this.selectionGuid = selectionGuid;
	}
	
}
