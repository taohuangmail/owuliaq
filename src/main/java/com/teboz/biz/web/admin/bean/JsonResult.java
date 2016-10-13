package com.teboz.biz.web.admin.bean;

import java.io.Serializable;


public class JsonResult implements Serializable{

	private static final long serialVersionUID = 2481424899113476683L;
	
	private Integer rc;//返回状态
	private String msg;//返回消息
	private Object data;//返回的数据
	
	public Integer getRc() {
		return rc;
	}

	public void setRc(Integer rc) {
		this.rc = rc;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public JsonResult(Integer rc, String msg) {
		super();
		this.rc = rc;
		this.msg = msg;
	}


	public JsonResult(Integer rc, String msg, Object data) {
		super();
		this.rc = rc;
		this.msg = msg;
		this.setData(data);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	

}
