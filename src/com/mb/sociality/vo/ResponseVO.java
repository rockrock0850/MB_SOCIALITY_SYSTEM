package com.mb.sociality.vo;

public class ResponseVO {
	/**
	 * 
	 */
	private String status;
	
	/**
	 * 
	 */
	private Object message;
	
	/**
	 * 
	 */
    private Object result;
    
    /*
     * 
     */

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
}
