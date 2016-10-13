package com.teboz.biz.web.admin.bean;

import com.teboz.biz.page.QueryBean;

public class SysUserQueryBean extends QueryBean{

	private String orderByClause; 
	
	private String username;
	
	private String realName;

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
