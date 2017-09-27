package com.feifang.oms.model;
/**
 * 
 */
public enum FileUploadType {
	
	
	HOTEL_BANNER("酒店banner",1),
	HOTEL_ROOM("酒店房间图片",2);
	
	
	
	
	private String code;
	private Integer value;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	private FileUploadType(String code, Integer value) {
		this.code = code;
		this.value = value;
	}
	
	
	
}
